package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long txid;
    
    private Long cbu;

    private Double amount;

    private char type;

    public Transaction(Long cbu, char type, Double amount) {
        this.amount = amount;
        this.cbu = cbu;
        this.type = type;
    }

}
