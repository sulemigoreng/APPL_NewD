/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

import com.appl.atm.view.Screen;

/**
 *
 * @author C70
 */
public abstract class Transaction {
    private int accountNumber; // indicates account involved
    private Screen screen; // ATM screen
    private BankDatabase bankDatabase; // account database
    
    public Transaction(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase) {
        accountNumber = userAccountNumber;
        screen = atmScreen;
        bankDatabase = atmBankDatabase;
    }
    
    public abstract int execute();
    public abstract String toString();

    /**
     * @return the accountNumber
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return the screen
     */
    public Screen getScreen() {
        return screen;
    }

    /**
     * @param screen the screen to set
     */
    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    /**
     * @return the bankDatabase
     */
    public BankDatabase getBankDatabase() {
        return bankDatabase;
    }

    /**
     * @param bankDatabase the bankDatabase to set
     */
    public void setBankDatabase(BankDatabase bankDatabase) {
        this.bankDatabase = bankDatabase;
    }
}
