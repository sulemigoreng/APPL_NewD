/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.Account;
import com.appl.atm.model.BalanceInquiry;
import com.appl.atm.model.BankDatabase;
import com.appl.atm.model.Transaction;
import com.appl.atm.view.Screen;

/**
 *
 * @author C70
 */
public class BalanceInquiryController extends TransactionController {

    private BalanceInquiry transaction;

    public BalanceInquiryController(Transaction theTransaction) {
	super(theTransaction);
	transaction = (BalanceInquiry) getTransaction();
    }

    @Override
    public int run() {
	Account account = getBankDatabase().getAccount(getAccountNumber());
	Screen screen = getScreen();

	double availableBalance = account.getAvailableBalance();
	double totalBalance = account.getTotalBalance();

	screen.displayMessage("\nBalance Information:\n");
	screen.displayMessage(" - Available balance\t: ");
	screen.displayDollarAmount(availableBalance);
	screen.displayMessage("\n - Total balance\t: ");
	screen.displayDollarAmount(totalBalance);
	screen.displayMessage("\n");
	
	return 0;
    }
    
    private int getAccountNumber()
    {
	return transaction.getAccountNumber();
    }
    
    private Screen getScreen()
    {
	return transaction.getScreen();
    }
    
    private BankDatabase getBankDatabase()
    {
	return transaction.getBankDatabase();
    }
    
}
