/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.BankDatabase;
import com.appl.atm.model.CashDispenser;
import static com.appl.atm.model.Constants.*;
import com.appl.atm.model.Transaction;
import com.appl.atm.model.Withdrawal;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;

/**
 *
 * @author C70
 */
public class WithdrawalController extends TransactionController {

    private Withdrawal transaction;

    public WithdrawalController(Transaction theTransaction) {
	super(theTransaction);
	transaction = (Withdrawal) getTransaction();
    }

    @Override
    public int run() {
	int amount = displayMenuOfAmounts();

	if (amount != 0) {
	    transaction.setAmount(amount);
	    int res = transaction.execute();

	    if (res == WITHDRAWAL_SUCCESS) {
		getScreen().displayMessageLine("Your cash has been dispensed. Please take your cash now.");
	    } else if (res == ACCOUNT_BALANCE_NOT_SUFFICIENT) {
		getScreen().displayMessageLine("Your balance isn't enough for this withdrawal.");
	    } else if (res == CASH_DISPENSER_NOT_SUFFICIENT) {
		getScreen().displayMessageLine("Cash dispenser doesn't have sufficient amount of cash.");
	    } else if (res == WITHDRAW_LIMIT_EXCEED) {
		getScreen().displayMessageLine("Your withdraw limit is exceeded or not enough.");
	    }
	}

	return 0;
    }

    // display a menu of withdrawal amounts and the option to cancel;
    // return the chosen amount or 0 if the user chooses to cancel
    private int displayMenuOfAmounts() {
	int userChoice = -1; // local variable to store return value

	Screen screen = getScreen(); // get screen reference

	// array of amounts to correspond to menu numbers
	int[] amounts = {0, 20, 40, 60, 100, 200};

	// loop while no valid choice has been made
	while (userChoice == -1) {
	    // display the withdrawal menu
	    screen.displayMessageLine("\nWithdrawal Menu:");
	    for (int i = 0; i < amounts.length - 1; i++) {
		screen.displayMessageLine((i + 1) + " - $" + amounts[i + 1]);
	    }
	    screen.displayMessageLine(amounts.length + " - Cancel transaction");
	    screen.displayMessage("\nChoose a withdrawal amount: ");

	    int input = getKeypad().getInputInt(); // get user input through keypad

	    // determine how to proceed based on the input value
	    switch (input) {
		case 1: // if the user chose a withdrawal amount 
		case 2: // (i.e., chose option 1, 2, 3, 4 or 5), return the
		case 3: // corresponding amount from amounts array
		case 4:
		case 5:
		    userChoice = amounts[input]; // save user's choice
		    break;
		case WITHDRAWAL_CANCELED: // the user chose to cancel
		    userChoice = 0; // save user's choice
		    screen.displayMessageLine("Canceling transaction...");
		    break;
		default: // the user did not enter a value from 1-6
		    screen.displayMessageLine(
			    "Invalid selection. Try again.");
	    }
	}

	return userChoice; // return withdrawal amount or CANCELED
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
    
    private CashDispenser getCashDispenser() {
	return transaction.getCashDispenser();
    }
}
