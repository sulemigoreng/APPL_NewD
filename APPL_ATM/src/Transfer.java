/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author C70
 */
// Transfer.java
// Represents a transfer ATM transaction
public class Transfer extends Transaction {

    private Account userAccountDest;
    private double amount;
    private Keypad keypad;
    private final static int CANCELED = 0;
    private final static double BIAYA_TRANSFER_MASADEPAN = 5.0;

    public Transfer(int userAccountNumberSource, Screen atmScreen,
            BankDatabase atmBankDatabase, Keypad atmKeypad) {
        super(userAccountNumberSource, atmScreen, atmBankDatabase);
        this.keypad = atmKeypad;
    }

    @Override
    public void execute() {
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();
        Account account = getBankDatabase().getAccount(getAccountNumber());

        this.userAccountDest = promptUserAccountDest();
        this.amount = promptForTransferAmount();
        double tempAvailableBalance = bankDatabase.getAvailableBalance(getAccountNumber())-amount;
        if (userAccountDest.getAccountNumber() == getAccountNumber()) {
            screen.displayMessage("Account Destination is not Available");
        } else {
            if (userAccountDest != null) {
                if (tempAvailableBalance < 0) {
                    screen.displayMessage("Balance Insufficient");
                } else {
                    bankDatabase.transferToAccount(getAccountNumber(),
                            userAccountDest.getAccountNumber(), amount);
                }
            } else {
                screen.displayMessage("Account Destination is not Available");
            }
        }
    }

    private double promptForTransferAmount() {
        Screen screen = getScreen();

        screen.displayMessage("\nPlease enter a transfer amount in "
                + "CENTS (or 0 to cancel): ");
        int input = keypad.getInput(); // receive input of deposit amount

        // check whether the user canceled or entered a valid amount
        if (input == CANCELED) {
            return CANCELED;
        } else {
            return (double) input / 100; // return dollar amount
        }
    }

    private Account promptUserAccountDest() {
        int userAccountNumberDest;
        Screen screen = getScreen();

        screen.displayMessageLine("\nPlease enter the destination account number "
                + "(or 0 to cancel): ");
        userAccountNumberDest = keypad.getInput();
        if (userAccountNumberDest == CANCELED) {
            return null;
        } else {
            return getBankDatabase().checkAccountDest(userAccountNumberDest);
        }
    }
}
