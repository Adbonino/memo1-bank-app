package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAttributeSourceAdvisor;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository txRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Collection<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Collection<Transaction> getTransactions() {
        return txRepository.findAll();
    }

    public Collection<Transaction> getTransactionsByCbu(Long cbu){
        return txRepository.findByCbu(cbu);
    }
     
    public Optional<Account> findById(Long cbu) {
        return accountRepository.findById(cbu);
    }

    public Optional<Transaction> findTxById(Long txid) {
        return txRepository.findById(txid);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void deleteById(Long cbu) {
        accountRepository.deleteById(cbu);
    }

    public void deleteTxById(Long txid) {
        txRepository.deleteById(txid);
    }
      
    @Transactional
    public Account withdraw(Long cbu, Double sum) {
        Account account = accountRepository.findAccountByCbu(cbu);

        if (account.getBalance() < sum) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        account.setBalance(account.getBalance() - sum);
        accountRepository.save(account);
        
        Transaction tx = new Transaction(cbu, 'W', sum);
        txRepository.save(tx); 

        
        return account;
    }


    @Transactional
    public Account deposit(Long cbu, Double sum) {

        if (sum <= 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }

        Account account = accountRepository.findAccountByCbu(cbu);

        if (sum >= 2000) {
            if  (0.1 * sum > 500){
                sum = sum + 500;
            }else{
                sum = sum * 1.1;
            }
        } 

        Transaction tx = new Transaction(cbu, 'D', sum);
        txRepository.save(tx); 
        
        
        account.setBalance(account.getBalance() + sum);
        accountRepository.save(account);

        return account;
    }

}
