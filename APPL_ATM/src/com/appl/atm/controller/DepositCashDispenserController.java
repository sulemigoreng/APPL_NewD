/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.appl.atm.controller;

import com.appl.atm.model.DepositCashDispenser;
import com.appl.atm.model.CheckCashDispenser;
import com.appl.atm.model.Transaction;

/**
 *
 * @author C70
 */
public class DepositCashDispenserController  extends TransactionController{

    private DepositCashDispenser transaction;
    private final static int CANCELED = 0;
    
    public DepositCashDispenserController(Transaction theTransaction) {
	super(theTransaction);
	transaction = (DepositCashDispenser) getTransaction();
    }
    
    @Override
    public int run() {
        int res = transaction.execute();
       if(res != CANCELED){
           System.out.println("Cash has been deposited to cash dispenser.");
       } else {
           System.out.println("Canceled.");           
       }
        return 0;
    }

}
