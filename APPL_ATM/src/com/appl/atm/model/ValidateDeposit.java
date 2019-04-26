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
public class ValidateDeposit extends Transaction {

    private Keypad keypad;
    private int validateTarget;
    
    public ValidateDeposit(int userAccountNumber, Screen atmScreen,
	    BankDatabase atmBankDatabase, Keypad atmKeypad) {
	
	super(userAccountNumber, atmScreen, atmBankDatabase);
	keypad = atmKeypad;
    }

    @Override
    public int execute() {
	Account account = getBankDatabase().getAccount(getValidateTarget());
	if (account != null) {
	    account.setAvailableBalance(account.getTotalBalance());
	    return DEPOSIT_VALIDATE_SUCCESS;
	} else {
	    return USER_NOT_FOUND;
	}
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
     * @return the validateTarget
     */
    public int getValidateTarget() {
	return validateTarget;
    }

    /**
     * @param validateTarget the validateTarget to set
     */
    public void setValidateTarget(int validateTarget) {
	this.validateTarget = validateTarget;
    }

    @Override
    public String toString() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
