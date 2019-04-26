/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.BankDatabase;
import static com.appl.atm.model.Constants.*;
import com.appl.atm.model.SystemDate;
import com.appl.atm.model.Transaction;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author C70
 */
public class SystemDateController extends TransactionController {

    private SystemDate transaction;

    public SystemDateController(Transaction theTransaction) {
	super(theTransaction);
	transaction = (SystemDate) getTransaction();
    }

    @Override
    public int run() {
	getScreen().displayMessageLine("\nCurrent Date\t: " + getCurrDate());
	getScreen().displayMessage("\nInput Day\t: ");
	int day = getKeypad().getInputInt();
	getScreen().displayMessage("Input Month\t: ");
	int month = getKeypad().getInputInt();
	getScreen().displayMessage("Input Year\t: ");
	int year = getKeypad().getInputInt();

//	Calendar calendar 
//	calendar.set(year, month - 1, day, 0, 0);
	Date newDate = new GregorianCalendar(year, month - 1, day).getTime();	
	transaction.setCurrDate(newDate);
	int res = transaction.execute();
	getScreen().displayMessageLine("\nCurrent Date\t: " + getCurrDate());	

	switch(res) {
	    case DATE_CHANGED_SUCCESSFULLY :
		getScreen().displayMessage("Date successfully changed.\n");
		getScreen().displayMessage("Daily withdraw limit has been reset.\n");
		break;
	    case TIME_REWIND:
		getScreen().displayMessage("You cant rewind the time.\n");
		break;
	    case TIME_DOESNT_CHANGED:
		getScreen().displayMessage("You entered the same date as before.\n");
		break;
	    case ADMIN_TAX_PAID:
		getScreen().displayMessage("Date successfully changed.\n");
		getScreen().displayMessage("Time to pay administration tax.\n");
		getScreen().displayMessage("Daily withdraw limit has been reset.\n");
		break;
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

    private Date getPrevDate() {
	return transaction.getPrevDate();
    }

    private Date getCurrDate() {
	return transaction.getCurrDate();
    }
}
