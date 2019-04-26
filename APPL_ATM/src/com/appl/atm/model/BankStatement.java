/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

import com.appl.atm.view.Screen;
import java.util.ArrayList;

/**
 *
 * @author C70
 */
public class BankStatement extends Transaction {

    public BankStatement(int userAccountNumber, Screen atmScreen,
	    BankDatabase atmBankDatabase) {
	
	super(userAccountNumber, atmScreen, atmBankDatabase);
    }

    @Override
    public int execute() {
	return 0;
    }
    
    public ArrayList<Transaction> getBankStatements() {
	return getBankDatabase().getBankStatement(getAccountNumber());
    }

    @Override
    public String toString() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
