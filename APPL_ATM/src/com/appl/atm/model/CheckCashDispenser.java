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
public class CheckCashDispenser extends Transaction{
    
    private CashDispenser cashDispenser; // reference to cash dispenser

    public CheckCashDispenser(int userAccountNumber, Screen atmScreen,
	    BankDatabase atmBankDatabase, CashDispenser atmCashDispenser) {
        
	super(userAccountNumber, atmScreen, atmBankDatabase);
        cashDispenser = atmCashDispenser;
    }
    
    public int execute(){
        return cashDispenser.getCountCash();
    }

    @Override
    public String toString() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}




