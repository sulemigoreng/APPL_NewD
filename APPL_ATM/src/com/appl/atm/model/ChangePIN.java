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
public class ChangePIN extends Transaction {

    private Keypad keypad;

    public ChangePIN(int userAccountNumber, Screen atmScreen,
	    BankDatabase atmBankDatabase, Keypad atmKeypad) {

	super(userAccountNumber, atmScreen, atmBankDatabase);
	keypad = atmKeypad;
    }

    public int execute(int newPIN) {
	Account currAcc = getBankDatabase().getAccount(getAccountNumber());

	if (currAcc != null) {
            currAcc.setPin(newPIN);
            return PIN_CHANGED_SUCCESSFULLY;
	} else {
	    return SAME_PIN_AS_BEFORE;
	}
    }

    public Keypad getKeypad() {
	return keypad;
    }

    @Override
    public int execute() {
	return 0;
    }

    @Override
    public String toString() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
