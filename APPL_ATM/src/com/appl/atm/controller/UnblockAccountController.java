/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.appl.atm.controller;

import com.appl.atm.model.BankDatabase;
import static com.appl.atm.model.Constants.*;
import com.appl.atm.model.Transaction;
import com.appl.atm.model.UnblockAccount;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;

/**
 *
 * @author C70
 */
public class UnblockAccountController extends TransactionController {
    private UnblockAccount transaction;

    public UnblockAccountController(Transaction theTransaction) {
	super(theTransaction);
	transaction = (UnblockAccount) getTransaction();
    }
    
    @Override
    public int run() {
        System.out.print("/nInsert Account Number : ");
        int target = getKeypad().getInputInt();
	transaction.setUnblockTarget(target);
        int res = transaction.execute();
	
        if(res == ACCOUNT_SUCCESSFULLY_UNBLOCKED){
            System.out.println("Account successfully unblocked.");
        } else if(res == USER_NOT_FOUND){
            System.out.println("Failed to unblock account, there`s no account with that account number.");            
        } else if(res == ACCOUNT_NOT_BLOCKED) {
	    System.out.println("Account is not blocked.");
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

    private Keypad getKeypad() {
	return transaction.getKeypad();
    }

}
