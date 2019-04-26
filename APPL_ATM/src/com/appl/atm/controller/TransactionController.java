/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.Transaction;

/**
 *
 * @author C70
 */
public abstract class TransactionController {
    private Transaction transaction;
    
    public TransactionController(Transaction theTransaction)
    {
	transaction = theTransaction;
    }
    
    public abstract int run();

    /**
     * @return the transaction
     */
    public Transaction getTransaction() {
	return transaction;
    }

    /**
     * @param transaction the transaction to set
     */
    public void setTransaction(Transaction transaction) {
	this.transaction = transaction;
    }
    
}
