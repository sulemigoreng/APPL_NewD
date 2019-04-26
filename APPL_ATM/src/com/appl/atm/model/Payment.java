/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

import com.appl.atm.view.Screen;
import static com.appl.atm.model.Constants.USER_PAYMENT_CANCELED;
import static com.appl.atm.model.Constants.USER_PAYMENT_SUCCESS;
import com.appl.atm.view.Keypad;
import java.util.ArrayList;

/**
 *
 * @author C70
 */
public class Payment extends Transaction {

    private int choice = 0;
    private Integer accountNumber = 0;
    private Double dblPayment = 0.0;
    private Integer numCompanyPayment = 0;
    private ArrayList<Double> listPayment;
    private ArrayList<Integer> listCompanyPayment;
    private Keypad keypad;

    public Payment(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        this.listPayment = getBankDatabase().getAccount(userAccountNumber).getListPayment();
        this.listCompanyPayment = getBankDatabase().getAccount(userAccountNumber).getListCompanyPayment();
        this.keypad = atmKeypad;
    }

    public void setPayment() {
        Account accountPayment = getBankDatabase().getAccount(accountNumber);
        if (accountPayment.getSizePayment()!= 0) {
            accountPayment.getListPayment().add(dblPayment);
            accountPayment.getListCompanyPayment().add(numCompanyPayment);
        } else {
            this.listPayment = new ArrayList<Double>();
            this.listCompanyPayment = new ArrayList<Integer>();
            listPayment.add(dblPayment);
            listCompanyPayment.add(numCompanyPayment);
            accountPayment.setListPayment(listPayment);
            accountPayment.setListCompanyPayment(listCompanyPayment);
        }
    }

    /**
     * @return the choice
     */
    public int getChoice() {
        return choice;
    }

    /**
     * @param choice the choice to set
     */
    public void setChoice(int choice) {
        this.choice = choice;
    }

    @Override
    public int execute() {
        if (getBankDatabase().getAccount(getAccountNumber()).getAvailableBalance() < getListPayment().get(choice)) {
            return USER_PAYMENT_CANCELED;
        } else {
            if (getDblPayment() > getBankDatabase().getAccount(getAccountNumber()).getListPayment().get(choice)) {
                return USER_PAYMENT_CANCELED;   //Kelebihan total biaya yang dibayarkan
            } else {
                getBankDatabase().getAccount(getAccountNumber())
                        .setAvailableBalance(getBankDatabase().getAccount(getAccountNumber())
                                .getAvailableBalance() - getDblPayment());
                getBankDatabase().getAccount(getAccountNumber())
                        .setTotalBalance(getBankDatabase().getAccount(getAccountNumber())
                                .getTotalBalance() - getDblPayment());
                if (getDblPayment() < getBankDatabase().getAccount(getAccountNumber()).getListPayment().get(choice)) {
                    ArrayList<Double> tempListPayment = getBankDatabase().getAccount(getAccountNumber()).getListPayment();
                    tempListPayment.set(choice, getBankDatabase().getAccount(getAccountNumber()).getListPayment().get(choice) - dblPayment);
                    getBankDatabase().getAccount(getAccountNumber()).setListPayment(tempListPayment);
                } else if (getDblPayment() == getBankDatabase().getAccount(getAccountNumber()).getListPayment().get(choice)) {
                    getBankDatabase().getAccount(getAccountNumber()).getListPayment().remove(choice);
//                    getBankDatabase().getAccount(getAccountNumber()).getListCustomerPayment().remove(choice);
                    getBankDatabase().getAccount(getAccountNumber()).getListCompanyPayment().remove(choice);
                }
                int numCompany = getBankDatabase().getAccount(getAccountNumber()).getListCompanyPayment().get(choice);
                getBankDatabase().getAccount(numCompany)
                        .setAvailableBalance(getBankDatabase().getAccount(numCompany)
                                .getAvailableBalance() + getDblPayment());
                getBankDatabase().getAccount(numCompany)
                        .setTotalBalance(getBankDatabase().getAccount(numCompany)
                                .getTotalBalance() + getDblPayment());
            }
            return USER_PAYMENT_SUCCESS;
        }
    }

    @Override
    public String toString() {
        String res = null;
        Double dblPayment = 0.0;
        Integer numPeruPayment = 0;
        int ii = 0;
        for (int i = 0; i < listPayment.size(); i++) {
            dblPayment = listPayment.get(ii);
            numPeruPayment = listCompanyPayment.get(ii);
            res += ++ii + "Payment for rekening " + numPeruPayment + " |$ " + dblPayment + ".\n";
        }
        return res;
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
     * @return the userAccountNumber
     */
    public int getUserAccountNumber() {
        return accountNumber;
    }

    /**
     * @param userAccountNumber the userAccountNumber to set
     */
    public void setUserAccountNumber(int userAccountNumber) {
        this.setAccountNumber((Integer) userAccountNumber);
    }

    /**
     * @return the dblPayment
     */
    public Double getDblPayment() {
        return dblPayment;
    }

    /**
     * @param dblPayment the dblPayment to set
     */
    public void setDblPayment(Double dblPayment) {
        this.dblPayment = dblPayment;
    }

    /**
     * @return the numCompanyPayment
     */
    public Integer getNumCompanyPayment() {
        return numCompanyPayment;
    }

    /**
     * @param numCompanyPayment the numCompanyPayment to set
     */
    public void setNumCompanyPayment(Integer numCompanyPayment) {
        this.numCompanyPayment = numCompanyPayment;
    }

    /**
     * @return the listPayment
     */
    public ArrayList<Double> getListPayment() {
        return listPayment;
    }

    /**
     * @param listPayment the listPayment to set
     */
    public void setListPayment(ArrayList<Double> listPayment) {
        this.listPayment = listPayment;
    }

    /**
     * @return the listCompanyPayment
     */
    public ArrayList<Integer> getListCompanyPayment() {
        return listCompanyPayment;
    }

    /**
     * @param listCompanyPayment the listCompanyPayment to set
     */
    public void setListCompanyPayment(ArrayList<Integer> listCompanyPayment) {
        this.listCompanyPayment = listCompanyPayment;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

}
