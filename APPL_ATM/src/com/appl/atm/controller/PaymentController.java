/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import static com.appl.atm.model.Constants.USER_PAYMENT_SUCCESS;
import static com.appl.atm.model.Constants.USER_PAYMENT_CANCELED;
import static com.appl.atm.model.Constants.ADMIN_PAYMENT_SUCCESS;
import static com.appl.atm.model.Constants.ADMIN_PAYMENT_CANCELED;
import static com.appl.atm.model.Constants.ACCOUNT_NUMBER_ADMIN;
import com.appl.atm.model.Payment;
import com.appl.atm.model.Transaction;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;
import java.util.ArrayList;

/**
 *
 * @author C70
 */
public class PaymentController extends TransactionController {

    private Payment payment;

    public PaymentController(Transaction transaction) {
        super(transaction);
        this.payment = (Payment) getTransaction();
    }

    @Override
    public int run() {
//        ArrayList<String> listStringPayment = new ArrayList<String>();
//        ArrayList<Double> listDoublePayment = new ArrayList<Double>();
//        listStringPayment = payment.getListStringPayment();
//        listDoublePayment = payment.getListDoublePayment();
        if (payment.getAccountNumber() != ACCOUNT_NUMBER_ADMIN) {
            if (payment.getListPayment().size() != 0) {
                int ii = 0;
                for (int i = 0; i < payment.getListPayment().size(); i++) {
                    getScreen().displayMessageLine("Choose " + ++ii
                            + " for payment " + payment.getListPayment().get(i)
                            + " from rekening " + payment.getListCompanyPayment().get(i)
                            + " | $" + payment.getListPayment().get(i));
//                    ++ii + "Payment for " + numAccPayment + " | Rekening " + numPeruPayment + " |$ " + dblPayment + ".\n";
//                payment.toString();
                }
                ii += 1;
                getScreen().displayMessage("Choose " + ii + " Exit\n" + "Choose : ");
                int input = getKeypad().getInputInt();
                ii -= 1;
                input--;
                if (input != ii) {
                    payment.setChoice(input);
                    Double inputDbl;
                    do {
                        getScreen().displayMessage("Input amount for payment : ");
                        inputDbl = getKeypad().getInputDbl();
                        if (inputDbl <= 0) {
                            getScreen().displayMessageLine("Input invalid.");
                        } else {
                            payment.setDblPayment(inputDbl);
                        }
                    } while (inputDbl <= 0);
                    return payment.execute();
                } else {
                    return USER_PAYMENT_CANCELED;
                }
            } else {
                getScreen().displayMessageLine("No Found Payment");
                return USER_PAYMENT_CANCELED;
            }
        } else {
            int inputInt;
            do {
                getScreen().displayMessage("Input Account Number in Payment ("
                        + ACCOUNT_NUMBER_ADMIN + " for exit) : ");
                inputInt = getKeypad().getInputInt();
                if (payment.getBankDatabase().getAccount(inputInt) != null && inputInt != ACCOUNT_NUMBER_ADMIN) {
                    payment.setUserAccountNumber(inputInt);
                    Double inputDbl;
                    do {
                        getScreen().displayMessage("Insert Invoice : ");
                        inputDbl = getKeypad().getInputDbl();
                        if (inputDbl <= 0) {
                            getScreen().displayMessageLine("Input invalid.");
                        } else {
                            payment.setDblPayment(inputDbl);
                        }
                    } while (inputDbl <= 0);
                    do {
                        getScreen().displayMessage("Input Account Number Company : ");
                        inputInt = getKeypad().getInputInt();
                        if (payment.getBankDatabase().getAccount(inputInt) != null && inputInt != ACCOUNT_NUMBER_ADMIN) {
                            payment.setNumCompanyPayment(inputInt);
                            payment.setPayment();
                        } else {
                            getScreen().displayMessageLine("Account number not found.");
                        }
                    } while (payment.getBankDatabase().getAccount(inputInt) == null);
                } else {
                    getScreen().displayMessageLine("Account number not found.");
//                    return ADMIN_PAYMENT_CANCELED;
                }
            } while (payment.getBankDatabase().getAccount(inputInt) == null);
            return ADMIN_PAYMENT_SUCCESS;
        }
    }

    public Screen getScreen() {
        return payment.getScreen();
    }

    public Keypad getKeypad() {
        return payment.getKeypad();
    }

    /**
     * @return the payment
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * @param payment the payment to set
     */
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
