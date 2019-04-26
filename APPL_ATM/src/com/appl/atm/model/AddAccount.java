/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;

/**
 *
 * @author C70
 */
public class AddAccount extends Transaction {

    private Keypad keypad;
    private int newAccountNumber;
    private int newPIN;
    private int newType;
    private double newBalance;

    public AddAccount(int userAccountNumber, Screen atmScreen,
	    BankDatabase atmBankDatabase, Keypad atmKeypad) {

	super(userAccountNumber, atmScreen, atmBankDatabase);
	keypad = atmKeypad;
    }

    @Override
    public int execute() {
	BankDatabase bankDatabase = getBankDatabase();
	return bankDatabase.addAccount(newAccountNumber, newPIN, newBalance, newType);
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
     * @return the newAccountNumber
     */
    public int getNewAccountNumber() {
	return newAccountNumber;
    }

    /**
     * @param newAccountNumber the newAccountNumber to set
     */
    public void setNewAccountNumber(int newAccountNumber) {
	this.newAccountNumber = newAccountNumber;
    }

    /**
     * @return the newPIN
     */
    public int getNewPIN() {
	return newPIN;
    }

    /**
     * @param newPIN the newPIN to set
     */
    public void setNewPIN(int newPIN) {
	this.newPIN = newPIN;
    }

    /**
     * @return the newBalance
     */
    public double getNewBalance() {
	return newBalance;
    }

    /**
     * @param newBalance the newBalance to set
     */
    public void setNewBalance(double newBalance) {
	this.newBalance = newBalance;
    }

    /**
     * @return the newType
     */
    public int getNewType() {
	return newType;
    }

    /**
     * @param newType the newType to set
     */
    public void setNewType(int newType) {
	this.newType = newType;
    }

    @Override
    public String toString() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
