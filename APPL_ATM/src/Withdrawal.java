// Withdrawal.java
// Represents a withdrawal ATM transaction

public class Withdrawal extends Transaction {

    private int amount; // amount to withdraw
    private Keypad keypad; // reference to keypad
    private CashDispenser cashDispenser; // reference to cash dispenser

    // constant corresponding to menu option to cancel
    private final static int CANCELED = 7;

    // Withdrawal constructor
    public Withdrawal(int userAccountNumber, Screen atmScreen,
            BankDatabase atmBankDatabase, Keypad atmKeypad,
            CashDispenser atmCashDispenser) {

        // initialize superclass variables
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        cashDispenser = atmCashDispenser;
    }

    // perform transaction
    @Override
    public void execute() {
        // get references to bank database and screen
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen(); // get screen reference
        Account account = getBankDatabase().getAccount(getAccountNumber());
        double limitCash = account.getLimitCash();

        // get the total balance for the account involved
        double totalBalance
                = bankDatabase.getTotalBalance(getAccountNumber());
        amount = displayMenuOfAmounts();

        if (amount == 6) {
            screen.displayMessageLine("Canceling Transaction...");
        } else {
            if (limitCash <= 0) {
                System.out.println("Cash limited");
            } else {
                if (limitCash < amount) {
                    System.out.println("Cash limited");
                } else {
                    limitCash -= amount;
                    account.setLimitCash(limitCash);
                    if (cashDispenser.isSufficientCashAvailable(amount)) {
                        if (totalBalance > 200) {
                            cashDispenser.dispenseCash(amount);
                            bankDatabase.credit(super.getAccountNumber(), amount);
                            screen.displayMessageLine("Your cash has been dispensed. Please take your cash now.");
                        } else {
                            screen.displayMessageLine("Your balance is not sufficient");
                        }
                    } else {
                        screen.displayMessageLine("Cash is not available at the moment");
                    }
                }
            }
        }
    }

   // display a menu of withdrawal amounts and the option to cancel;
    // return the chosen amount or 0 if the user chooses to cancel
    private int displayMenuOfAmounts() {
        int userChoice = 0; // local variable to store return value

        Screen screen = getScreen(); // get screen reference

        // array of amounts to correspond to menu numbers
        int[] amounts = {0, 20, 40, 60, 100, 200};

        // loop while no valid choice has been made
        while (userChoice == 0) {
            // display the withdrawal menu
            screen.displayMessageLine("\nWithdrawal Menu:");
            screen.displayMessageLine("1 - $20");
            screen.displayMessageLine("2 - $40");
            screen.displayMessageLine("3 - $60");
            screen.displayMessageLine("4 - $100");
            screen.displayMessageLine("5 - $200");
            screen.displayMessageLine("6 - Other transaction");
            screen.displayMessageLine("7 - Cancel transaction");
            screen.displayMessage("\nChoose a withdrawal amount: ");

            int input = keypad.getInput(); // get user input through keypad
            // determine how to proceed based on the input value
            switch (input) {
                case 1: // if the user chose a withdrawal amount 
                case 2: // (i.e., chose option 1, 2, 3, 4 or 5), return the
                case 3: // corresponding amount from amounts array
                case 4:
                case 5:

                    userChoice = amounts[input]; // save user's choice
                    break;
                case 6:
                    screen.displayMessageLine("\nInput amount: ");
                    
                    int Amount = keypad.getInput();
                    userChoice = Amount;
                    break;
                case CANCELED: // the user chose to cancel
                    userChoice = CANCELED; // save user's choice
                    break;
                default: // the user did not enter a value from 1-6
                    screen.displayMessageLine(
                            "\nInvalid selection. Try again.");
            }
        }

        return userChoice; // return withdrawal amount or CANCELED
    }
}
