/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.AddAccount;
import com.appl.atm.model.BankDatabase;
import static com.appl.atm.model.Constants.*;
import com.appl.atm.model.Transaction;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;

/**
 *
 * @author C70
 */
public class AddAccountController extends TransactionController {

    private AddAccount transaction;

    public AddAccountController(Transaction theTransaction) {
	super(theTransaction);
	transaction = (AddAccount) getTransaction();
    }

    @Override
    public int run() {
	getScreen().displayMessage("\nInsert new account number\t: ");
	int newAccountNumber = getKeypad().getInputInt();
	getScreen().displayMessage("Insert new PIN\t\t\t: ");
	int newPIN = getKeypad().getInputInt();
	getScreen().displayMessage("Insert initial balance\t\t: $");
	double newBalance = getKeypad().getInputInt();
	getScreen().displayMessage("\nAccount types :\n");
	getScreen().displayMessage("0. Admin\n");
	getScreen().displayMessage("1. Siswa\n");
	getScreen().displayMessage("2. Bisnis\n");
	getScreen().displayMessage("3. Masa depan\n");
	getScreen().displayMessage("Outside the numbers above will be 'Bisnis' type.\n");
	getScreen().displayMessage("\nInsert account type\t\t: ");
	int newType = getKeypad().getInputInt();
	
	if(newType < 0 || newType > 3) {
	    newType = 2;
	}
	
	transaction.setNewAccountNumber(newAccountNumber);
	transaction.setNewPIN(newPIN);
	transaction.setNewBalance(newBalance);
	transaction.setNewType(newType);
	int res = transaction.execute();
	
	if (res == ACCOUNT_SUCCESSFULLY_CREATED) {
	    System.out.println("Account successfully created.");
	} else if (res == ACCOUNT_EXIST) {
	    System.out.println("Failed to create account, there`s someone using that account number.");
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
