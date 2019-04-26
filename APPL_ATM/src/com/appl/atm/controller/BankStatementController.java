/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.BankDatabase;
import com.appl.atm.model.BankStatement;
import com.appl.atm.model.Transaction;
import com.appl.atm.view.Screen;
import java.util.ArrayList;

/**
 *
 * @author C70
 */
public class BankStatementController extends TransactionController {

    private BankStatement transaction;

    public BankStatementController(Transaction theTransaction) {
	super(theTransaction);
	transaction = (BankStatement) getTransaction();

    }

    @Override
    public int run() {
	ArrayList<Transaction> bankStatement = transaction.getBankStatements();
	
	if(bankStatement == null) {
	    getScreen().displayMessage("\nNo Bank Statement for This Account.\n");
	} else {
	    getScreen().displayMessageLine("Account number : " + getAccountNumber());
	    for (int i = 0; i < bankStatement.size(); i++) {
		getScreen().displayMessageLine("- " + bankStatement.get(i).toString()); //langsung toString biar gausah bedain withdraw / transfer dll
	    }
	}
	
	return 0;
    }

    private int getAccountNumber() {
	return transaction.getAccountNumber();
    }

    private Screen getScreen() {
	return transaction.getScreen();
    }

    private BankDatabase getBankDatabase() {
	return transaction.getBankDatabase();
    }

}
