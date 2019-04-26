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
public class Deposit extends Transaction {

    private double amount; // amount to deposit
    private Keypad keypad; // reference to keypad
    private DepositSlot depositSlot; // reference to deposit slot

    // Deposit constructor
    public Deposit(int userAccountNumber, Screen atmScreen,
	    BankDatabase atmBankDatabase, Keypad atmKeypad,
	    DepositSlot atmDepositSlot) {

	// initialize superclass variables
	super(userAccountNumber, atmScreen, atmBankDatabase);
	keypad = atmKeypad;
	depositSlot = atmDepositSlot;
    }

    @Override
    public int execute() {
	if (depositSlot.isEnvelopeReceived()) {
	    Account account = getBankDatabase().getAccount(getAccountNumber());
	    account.credit(amount);
	    getBankDatabase().addBankStatement(this);
	    return DEPOSIT_SUCCESS;
	} else {
	    return ENVELOPE_NOT_RECEIVED;
	}
    }

    /**
     * @return the amount
     */
    public double getAmount() {
	return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
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
     * @return the depositSlot
     */
    public DepositSlot getDepositSlot() {
	return depositSlot;
    }

    /**
     * @param depositSlot the depositSlot to set
     */
    public void setDepositSlot(DepositSlot depositSlot) {
	this.depositSlot = depositSlot;
    }

    @Override
    public String toString() {
	String res = "Deposit $" + amount + ".";
	return res;
    }
}
