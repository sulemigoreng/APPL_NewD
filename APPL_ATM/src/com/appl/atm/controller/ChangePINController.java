/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.Account;
import com.appl.atm.model.BankDatabase;
import com.appl.atm.model.ChangePIN;
import com.appl.atm.model.Transaction;
import static com.appl.atm.model.Constants.*;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;

/**
 *
 * @author C70
 */
public class ChangePINController extends TransactionController {

    private ChangePIN changePIN;

    public ChangePINController(Transaction theTransaction) {
        super(theTransaction);
        changePIN = (ChangePIN) getTransaction();
    }

    @Override
    public int run() {
        int newPIN = promptForNewPIN();
        int res = -1;
        if (newPIN == 0) {
            res = changePIN.execute(newPIN);
        }
        promptMessage(res);
        return 0;
    }

    private int promptForNewPIN() {
        Screen screen = getScreen();
        BankDatabase bankDatabase = getBankDatabase();
        int currPIN, newPIN = 0;
        Account currAcc = getBankDatabase().getAccount(changePIN.getAccountNumber());
        int tempAccPIN = currAcc.getPin();

        screen.displayMessage("Please input your current PIN : ");
        currPIN = getKeypad().getInputInt();
        if (currAcc.getPin() == currPIN) {
//            do {
                screen.displayMessage("Please input your new PIN : ");
                newPIN = getKeypad().getInputInt();
                if (currPIN == newPIN) {
                    screen.displayMessageLine("New PIN cannot be the same as the old PIN.");
                    return SAME_PIN_AS_BEFORE;
                } else {
                    int currAccPIN = tempAccPIN;
                    int currNewPIN = newPIN;
                    int listPIN[] = new int[6];
                    int listNewPIN[] = new int[6];
                    if (currAccPIN / 1000000 != 0) {
                        listPIN[5] = currAccPIN / 1000000;
                    }
                    currAccPIN = currAccPIN % 100000;
                    if (currAccPIN / 100000 != 0) {
                        listPIN[4] = currAccPIN / 100000;
                    }
                    currAccPIN = currAccPIN % 10000;
                    if (currAccPIN / 10000 != 0) {
                        listPIN[3] = currAccPIN / 10000;
                    }
                    currAccPIN = currAccPIN % 1000;
                    if (currAccPIN / 1000 != 0) {
                        listPIN[2] = currAccPIN / 1000;
                    }
                    currAccPIN = currAccPIN % 100;
                    if (currAccPIN / 100 != 0) {
                        listPIN[1] = currAccPIN / 100;
                    }
                    currAccPIN = currAccPIN % 10;
                    if (currAccPIN / 10 != 0) {
                        listPIN[0] = currAccPIN / 10;
                    }
                    if (currNewPIN / 1000000 != 0) {
                        listNewPIN[5] = currNewPIN / 1000000;
                    }
                    currAccPIN = currAccPIN % 100000;
                    if (currNewPIN / 100000 != 0) {
                        listNewPIN[4] = currNewPIN / 100000;
                    }
                    currAccPIN = currAccPIN % 10000;
                    if (currNewPIN / 10000 != 0) {
                        listNewPIN[3] = currNewPIN / 10000;
                    }
                    currAccPIN = currAccPIN % 1000;
                    if (currNewPIN / 1000 != 0) {
                        listNewPIN[2] = currNewPIN / 1000;
                    }
                    currAccPIN = currAccPIN % 100;
                    if (currNewPIN / 100 != 0) {
                        listNewPIN[1] = currNewPIN / 100;
                    }
                    currAccPIN = currAccPIN % 10;
                    if (currNewPIN / 10 != 0) {
                        listNewPIN[0] = currNewPIN / 10;
                    }
                    if (listPIN[5] == listNewPIN[5]
                            && listPIN[4] == listNewPIN[4]
                            && listPIN[3] == listNewPIN[3]
                            && listPIN[2] == listNewPIN[2]
                            && listPIN[1] == listNewPIN[1]
                            && listPIN[0] == listNewPIN[0]) {
                        return SAME_PIN_AS_BEFORE;
                    } else if (listNewPIN[5] == listNewPIN[4]
                            && listNewPIN[5] == listNewPIN[3]
                            && listNewPIN[5] == listNewPIN[2]
                            && listNewPIN[5] == listNewPIN[1]
                            && listNewPIN[5] == listNewPIN[0] ||
                            listNewPIN[4] == listNewPIN[3]
                            && listNewPIN[4] == listNewPIN[2]
                            && listNewPIN[4] == listNewPIN[1]
                            && listNewPIN[4] == listNewPIN[0] ||
                            listNewPIN[3] == listNewPIN[2]
                            && listNewPIN[3] == listNewPIN[1]
                            && listNewPIN[3] == listNewPIN[0] ||
                            listNewPIN[2] == listNewPIN[1]
                            && listNewPIN[2] == listNewPIN[0] ||
                            listNewPIN[1] == listNewPIN[0]) {
                        return SAME_PIN_AS_BEFORE;
                    } else {
                        currAcc.setPin(newPIN);
//                        return PIN_CHANGED_SUCCESSFULLY;
                    }
                }
//            } while (currPIN == newPIN);
        }
        return 0;
    }

    private void promptMessage(int res) {
        Screen screen = getScreen();
        if (res == 0) {
            screen.displayMessageLine("Change PIN Success!");
        } else {
            screen.displayMessageLine("Change PIN Failed!");
        }
    }

    private Screen getScreen() {
        return changePIN.getScreen();
    }

    private Keypad getKeypad() {
        return changePIN.getKeypad();
    }

    private BankDatabase getBankDatabase() {
        return changePIN.getBankDatabase();
    }

}
