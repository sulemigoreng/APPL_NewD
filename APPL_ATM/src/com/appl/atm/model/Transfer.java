/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

import static com.appl.atm.model.Constants.*;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;

/**
 *
 * @author C70
 */
public class Transfer extends Transaction {

    private int amount;
    private Keypad keypad;
    private int targetAccount;

    public Transfer(int userAccountNumber, Screen atmScreen,
	    BankDatabase atmBankDatabase, Keypad atmKeyPad) {

	super(userAccountNumber, atmScreen, atmBankDatabase);
	keypad = atmKeyPad;
    }

    @Override
    public int execute() {
	Account account = getBankDatabase().getAccount(getAccountNumber());
	Account target = getBankDatabase().getAccount(targetAccount);
	double paymentTax = account.getTransferTax();
	if (target != null) {
	    if (amount < 0) {
		return NEGATIVE_AMOUNT;
	    } else if (account.getAvailableBalance() < amount + paymentTax) {
		return ACCOUNT_BALANCE_NOT_SUFFICIENT;
	    } else {
		account.debit(amount + paymentTax);
		target.debit(-1 * amount);
		getBankDatabase().addBankStatement(this);
		return TRANSFER_SUCCESS;
	    }
	} else {
	    return USER_NOT_FOUND;
	}

    }

    /**
     * @return the amount
     */
    public int getAmount() {
	return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
	this.amount = amount;
    }

    /**
     * @return the keypad
     */
    public Keypad getKeypad() {
	return keypad;
    }

    /**
     * @param keypad the keypad to set
     */
    public void setKeypad(Keypad keypad) {
	this.keypad = keypad;
    }

    /**
     * @return the targetAccount
     */
    public int getTargetAccount() {
	return targetAccount;
    }

    /**
     * @param targetAccount the targetAccount to set
     */
    public void setTargetAccount(int targetAccount) {
	this.targetAccount = targetAccount;
    }

    @Override
    public String toString() {
	String res = "Transfer $" + amount + " to " + targetAccount + ".";
	return res;
    }

}
