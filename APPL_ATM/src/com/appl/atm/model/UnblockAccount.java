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
public class UnblockAccount extends Transaction {
    private Keypad keypad;
    private int unblockTarget;
    
    public UnblockAccount(int userAccountNumber, Screen atmScreen,
	    BankDatabase atmBankDatabase, Keypad atmKeypad) {
	
        super(userAccountNumber, atmScreen, atmBankDatabase);
	keypad = atmKeypad;
    }
    
    @Override
    public int execute() {
	Account account = getBankDatabase().getAccount(unblockTarget);
	
	if (account != null) {
	    if (account.isBlocked()) {
		account.setBlocked(false);
		return ACCOUNT_SUCCESSFULLY_UNBLOCKED;
	    } else {
		return ACCOUNT_NOT_BLOCKED;
	    }
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
     * @return the unblockTarget
     */
    public int getUnblockTarget() {
	return unblockTarget;
    }

    /**
     * @param unblockTarget the unblockTarget to set
     */
    public void setUnblockTarget(int unblockTarget) {
	this.unblockTarget = unblockTarget;
    }

    @Override
    public String toString() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
