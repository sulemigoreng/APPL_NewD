/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

import static com.appl.atm.model.Constants.*;
import java.util.ArrayList;

/**
 *
 * @author C70
 */
public class BankDatabase {

    private ArrayList<Account> accounts; // array of Accounts
    private ArrayList<Transaction> bankStatements;

    public BankDatabase() {
        bankStatements = new ArrayList<Transaction>();
        accounts = new ArrayList<Account>();
        accounts.add(new Account(00000, 00000, 0.0, 0.0, ADMIN));
        accounts.add(new Account(1234, 1234, 1000.0, 1200.0, SISWA));
        accounts.add(new Account(5678, 5678, 200.0, 200.0, BISNIS));
        accounts.add(new Account(12345, 12345, 200.0, 200.0, MASA_DEPAN));
    }

    public Account getAccount(int accountNumber) {
        int i;
        for (i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber() == accountNumber) {
                return accounts.get(i);
            }
        }
        return null; // if no matching account was found, return null
    }

    public ArrayList<Transaction> getBankStatement(int accountNumber) {
        ArrayList<Transaction> result = new ArrayList<Transaction>();

        for (int i = 0; i < bankStatements.size(); i++) {
            if (bankStatements.get(i).getAccountNumber() == accountNumber) {
                result.add(bankStatements.get(i));
            }
        }

        return result.isEmpty() ? null : result;
    }

    public void addBankStatement(Transaction theTransaction) {
        bankStatements.add(theTransaction);
    }

    public int authenticateUser(int userAccountNumber, int userPIN) {
        Account userAccount = getAccount(userAccountNumber);

        if (userAccount != null) {
            int res = userAccount.validatePIN(userPIN);
            return res;
        } else {
            return USER_NOT_FOUND;
        }
    }

    public int addAccount(int newAccountNumber, int newPIN, double newBalance, int newType) {
        Account account = getAccount(newAccountNumber);
        if (account != null) {
            return ACCOUNT_EXIST;
        } else {
            account = new Account(newAccountNumber, newPIN, newBalance, newBalance, newType);
            accounts.add(account);
            return ACCOUNT_SUCCESSFULLY_CREATED;
        }
    }

    public void monthlyPayment() {
        for (int i = 0; i < accounts.size(); i++) {
            accounts.get(i).monthlyPayment();
        }
    }

    public void dailyWithdrawReset() {
        for (int i = 0; i < accounts.size(); i++) {
            accounts.get(i).dailyWithdrawReset();
        }
    }

}
