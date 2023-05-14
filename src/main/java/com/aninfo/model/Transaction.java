package com.aninfo.model;

import javax.persistence.*;

import org.apache.el.lang.ELArithmetic.DoubleDelegate;

@Entity
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long txid;
    
    private Long cbu;

    private Double amount;

    private char type;

    public Transaction(){

    }

    public Transaction(Long cbu, char type, Double amount) {
        this.amount = amount;
        this.cbu = cbu;
        this.type = type;
    }


    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    } 

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
     
    public Long getTxid() {
        return txid;
    }

    public void setTxid(Long txid) {
        this.txid = txid;
    }

    public Long getCbu() {
        return cbu;
    }

    public void setCbu(Long cbu) {
        this.cbu = cbu;
    }

}
