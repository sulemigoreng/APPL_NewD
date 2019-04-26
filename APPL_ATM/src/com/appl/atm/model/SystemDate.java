/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

import static com.appl.atm.model.Constants.*;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;
import java.util.Date;

/**
 *
 * @author C70
 */
public class SystemDate extends Transaction {

    private Date prevDate;
    private Date currDate;
    private Keypad keypad; // reference to keypad

    public SystemDate(int userAccountNumber, Screen atmScreen,
	    BankDatabase atmBankDatabase, Keypad atmKeypad) {

	super(userAccountNumber, atmScreen, atmBankDatabase);
	keypad = atmKeypad;
	prevDate = new Date(System.currentTimeMillis());
	currDate = new Date(System.currentTimeMillis());
    }

    @Override
    public int execute() {
	if (currDate.compareTo(prevDate) < 0) { //waktu yg di input < prevDate
	    currDate = prevDate;
	    return TIME_REWIND;
	} else if (currDate.compareTo(prevDate) > 0) { //waktu yg di input > prevDate
	    int res = this.dateCheck();
	    getBankDatabase().dailyWithdrawReset(); //reset withdraw tiap akun
	    prevDate = currDate;
	    return res == ADMIN_TAX_PAID ? res : DATE_CHANGED_SUCCESSFULLY; //penentuan input waktu sukses atau tidak
	} else { //waktu yang di input == prevDate
	    return TIME_DOESNT_CHANGED;
	}
    }

    /**
     * @return the prevDate
     */
    public Date getPrevDate() {
	return prevDate;
    }

    /**
     * @param prevDate the prevDate to set
     */
    public void setPrevDate(Date prevDate) {
	this.prevDate = prevDate;
    }

    /**
     * @return the currDate
     */
    public Date getCurrDate() {
	return currDate;
    }

    /**
     * @param currDate the currDate to set
     */
    public void setCurrDate(Date currDate) {
	this.currDate = currDate;
    }

    public int dateCheck() {
	if (prevDate.getDate() < 3 && currDate.getDate() > 2) { //bayar saat tanggal 3, hari ini tanggal 2, inputnya tanggal 3
	    getBankDatabase().monthlyPayment();
	    return ADMIN_TAX_PAID;
	} else if (prevDate.getMonth() < currDate.getMonth() && currDate.getDate() > 2) { //bulannya nambah dan tanggal >= 3
	    getBankDatabase().monthlyPayment();
	    return ADMIN_TAX_PAID;
	} else if (prevDate.getYear() < currDate.getYear() && currDate.getDate() > 2) { //tahunnya nambah dan tanggal >= 3
	    getBankDatabase().monthlyPayment();
	    return ADMIN_TAX_PAID;
	}

	return ADMIN_TAX_NOT_PAID;
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

    @Override
    public String toString() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
