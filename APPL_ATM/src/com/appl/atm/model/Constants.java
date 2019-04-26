package com.appl.atm.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author C70
 */
public final class Constants {

    
    
    // menu user
    public static final int EXIT_USER = 0;
    public static final int EXIT_ADMIN = 0;
    public static final int BALANCE_INQUIRY = 1;
    public static final int WITHDRAWAL = 2;
    public static final int DEPOSIT = 3;
    public static final int CHANGE_PIN = 4;
    public static final int TRANSFER = 5;
    public static final int BANK_STATEMENT = 6;
    public static final int CHANGE_DATE = 7;
    public static final int PAYMENT = 8;
    public static final int ADD_PAYMENT = 9;
    public static final int UNBLOCK_ACCOUNT = 10; // admin only
    public static final int CHECK_DISPENSER = 11; // admin only
    public static final int ADD_DISPENSER = 12; // admin only
    public static final int ADD_ACCOUNT = 13; // admin only
    public static final int VALIDATE_DEPOSIT = 14; // admin only
    
    // cancelled condition
    public static final int WITHDRAWAL_CANCELED = 6;
    public static final int DEPOSIT_CANCELED = 0;
    public static final int TRANSFER_CANCELED = 0;
    public static final int PAYMENT_CANCELED = -1;
    
    // account type
    public static final int ADMIN = 0;
    public static final int SISWA = 1;
    public static final int BISNIS = 2;
    public static final int MASA_DEPAN = 3;

    // authenticate user @bankdatabase and @account
    public static final int AUTHENTICATE_SUCCESS = 0;
    public static final int INVALID_PIN = 1;
    public static final int USER_BLOCKED = 2;
    public static final int USER_BE_BLOCKED = 3;
    public static final int USER_NOT_FOUND = 4;

    // balance check
    public static final int BALANCE_INQUIRY_SUCCESS = 0;

    // withdrawal
    public static final int WITHDRAWAL_SUCCESS = 0;
    public static final int ACCOUNT_BALANCE_NOT_SUFFICIENT = 1;
    public static final int CASH_DISPENSER_NOT_SUFFICIENT = 2;
    public static final int WITHDRAW_LIMIT_EXCEED = 3;

    // deposit
    public static final int DEPOSIT_SUCCESS = 0;
    public static final int ENVELOPE_NOT_RECEIVED = 1;
    
    // transfer
    public static final int TRANSFER_SUCCESS = 0;
    public static final int NEGATIVE_AMOUNT = 2;
    public static final int SAME_ACCOUNT_NUMBER = 3;
    
    // validate deposit
    public static final int DEPOSIT_VALIDATE_SUCCESS = 0;
    
    // unblock account
    public static final int ACCOUNT_SUCCESSFULLY_UNBLOCKED = 0;
    public static final int ACCOUNT_NOT_BLOCKED = 1;
    
    // add account
    public static final int ACCOUNT_SUCCESSFULLY_CREATED = 0;
    public static final int ACCOUNT_EXIST = 1;
    
    //change PIN
    public static final int PIN_CHANGED_SUCCESSFULLY = 0;
    public static final int SAME_PIN_AS_BEFORE = 1;
    
    //change date
    public static final int DATE_CHANGED_SUCCESSFULLY = 0;
    public static final int TIME_REWIND = 1;
    public static final int TIME_DOESNT_CHANGED = 2;
    public static final int ADMIN_TAX_PAID = 3;
    public static final int ADMIN_TAX_NOT_PAID = 4;
    
    //payment
    public static final int USER_PAYMENT_SUCCESS = 0;
    public static final int ADMIN_PAYMENT_SUCCESS = 0;
    public static final int USER_PAYMENT_CANCELED = 0;
    public static final int ADMIN_PAYMENT_CANCELED = 0;
    
    //admin
    public static final int ACCOUNT_NUMBER_ADMIN = 0000;
}
