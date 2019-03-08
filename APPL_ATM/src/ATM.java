
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ATM {

    private boolean userAuthenticated; // whether user is authenticated
    private int currentAccountNumber; // current user's account number
    private Screen screen; // ATM's screen
    private Keypad keypad; // ATM's keypad
    private CashDispenser cashDispenser; // ATM's cash dispenser
    private BankDatabase bankDatabase; // account information database
    private DepositSlot ATMDepositSlot;
    private boolean admin;
    private Account acc;
    private Date tanggal;

    // constants corresponding to main menu options
    private static final int BALANCE_INQUIRY = 1;
    private static final int WITHDRAWAL = 2;
    private static final int DEPOSIT = 3;

    private static final int TRANSFER = 5;
    private static final int EXIT = 7;

    private static final int HISTORY = 6;
    private static final int CHANGEPIN = 4;
    private static final int DISPLAY_DISPENSER = 3;
    private static final int ADD_DISPENSER = 4;
    private static final int ATUR_TANGGAL = 6;

    // no-argument ATM constructor initializes instance variables
    public ATM() {
        userAuthenticated = false; // user is not authenticated to start
        currentAccountNumber = 0; // no current account number to start
        screen = new Screen(); // create screen
        keypad = new Keypad(); // create keypad 
        cashDispenser = new CashDispenser(screen); // create cash dispenser
        bankDatabase = new BankDatabase(); // create acct info database
        ATMDepositSlot = new DepositSlot();
        admin = false;
        tanggal = new Date();
    }

    // start ATM 
    public void run() {
        // welcome and authenticate user; perform transactions
        while (true) {
            // loop while user is not yet authenticated
            while (!userAuthenticated) {
                screen.displayMessageLine("\nWelcome!");
                authenticateUser(); // authenticate user
            }

            if (!acc.isBlocked()) {
                performTransactions(); // user is now authenticated
                userAuthenticated = false; // reset before next ATM session
                currentAccountNumber = 0; // reset before next ATM session
                screen.displayMessageLine("\nThank you! Goodbye!");
            }

            authenticateUser();

        }
    }

    // attempts to authenticate user against database
    private void authenticateUser() {

        int i = 1;
        screen.displayMessage("\nPlease enter your account number: ");
        int accountNumber = keypad.getInput(); // input account number
        screen.displayMessage("Enter your PIN: "); // prompt for PIN
        int pin = keypad.getInput(); // input PIN
        i++;

        acc = bankDatabase.getAccount(accountNumber);

        // check whether authentication succeeded
        if (acc != null) {
            userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);

            while (!userAuthenticated && i <= 3 && !acc.isBlocked()) {

                //while(i <= 3){
//                screen.displayMessageLine("You input the wrong account number or PIN");
//                screen.displayMessage("\nPlease enter your account number: ");
//                accountNumber = keypad.getInput(); // input account number
                screen.displayMessage("Enter your PIN: "); // prompt for PIN
                pin = keypad.getInput(); // input PIN

                userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);
                i++;
                //}

            }

            if (i > 3) {
                //Account acc = bankDatabase.getAccount(accountNumber);
                screen.displayMessageLine("Sorry, your account has been blocked..");
                acc.setBlocked(true);
            }

            // check whether authentication succeeded
            if (userAuthenticated) {
                currentAccountNumber = accountNumber; // save user's account #
                admin = bankDatabase.isAdmin(accountNumber);
            } else if (acc == null) {
                screen.displayMessageLine(
                        "Invalid account number or PIN. Please try again.");
            }
        }

    }

    private void unblockUser() {
        Account accountUserBlocked;

        screen.displayMessage("\nPlease enter account number that want to Unblock: ");
        int accountNumberBlocked = keypad.getInput(); // input account number

        // set userAuthenticated to boolean value returned by database
        accountUserBlocked
                = bankDatabase.getAccountUser(accountNumberBlocked);

        // check whether authentication succeeded
        if (accountUserBlocked != null) {
            accountUserBlocked.setBlocked(false);
            screen.displayMessageLine("That account has been Unblocked");
        } else {
            screen.displayMessageLine(
                    "Invalid account number. Please try again.");
        }
    }

    private void addUser() {
        int newAccountNumber;
        do {
            screen.displayMessage("\nPlease Insert New Account Number: ");
            newAccountNumber = keypad.getInput();
            if (bankDatabase.isExists(newAccountNumber)) {
                screen.displayMessageLine("The Account Already Exist");
            }
        } while (bankDatabase.isExists(newAccountNumber));

        screen.displayMessage("\nPlease Insert New Account PIN: ");
        int newAccountPIN = keypad.getInput();
        bankDatabase.addAccount(newAccountNumber, newAccountPIN);

    }

    // display the main menu and perform transactions
    private void performTransactions() {
        // local variable to store transaction currently being processed
        Transaction currentTransaction = null;

        boolean userExited = false; // user has not chosen to exit

        // loop while user has not chosen option to exit system
        while (!userExited) {
            // show main menu and get user selection
            int mainMenuSelection = displayMainMenu();
            if (admin) {
                // decide how to proceed based on user's menu selection
                switch (mainMenuSelection) {
                    case 5:
                        screen.displayMessage("Input Account Number = ");
                        int acc = keypad.getInput();
                        validateDeposit(acc);
                        break;
                    case 1: //Unblock Account
                        unblockUser();
                        break;
                    case 2: //Add Account
                        // initialize as new object of chosen type
                        addUser();
                        break;
                    case ATUR_TANGGAL:
                        try {
                            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            String tanggalBaru;
                            screen.displayMessageLine("Tanggal Sekarang: " + format.format(tanggal));
                            screen.displayMessage("Masukan Tanggal Baru(mm/DD/"
                                    + "yyyy): ");
                            keypad.getDateInput();//makan Enter
                            tanggalBaru = keypad.getDateInput();
                            

                            tanggal = format.parse(tanggalBaru);
                        } catch (ParseException ex) {
                            Logger.getLogger(ATM.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        break;
                    case DISPLAY_DISPENSER:
                        cashDispenser.displayDispenser();
                        break;
                    case ADD_DISPENSER:
                        screen.displayMessage("Please insert a number to cash dispenser : ");
                        double tambah = keypad.getInput();
                        cashDispenser.addCashDispenser(tambah);
                        break;
                    case EXIT: // user chose to terminate session
                        screen.displayMessageLine("\nExiting the system...");
                        userExited = true; // this ATM session should end
                        break;
                    default: // 
                        screen.displayMessageLine(
                                "\nYou did not enter a valid selection. Try again.");
                        break;
                }
            } else {
                // decide how to proceed based on user's menu selection
                switch (mainMenuSelection) {
                    // user chose to perform one of three transaction types
                    case BALANCE_INQUIRY:
                        // initialize as new object of chosen type
                        currentTransaction
                                = createTransaction(mainMenuSelection);
                        currentTransaction.execute(); // execute transaction
                        break;
                    case WITHDRAWAL:
                        // initialize as new object of chosen type
                        currentTransaction
                                = createTransaction(mainMenuSelection);
                        currentTransaction.execute();

                        break;
                    case DEPOSIT:
                        currentTransaction
                                = createTransaction(mainMenuSelection);
                        currentTransaction.execute();
                        break;
                    case HISTORY:
                        ArrayList<History> histories = bankDatabase.getHistories(currentAccountNumber);
                        if (histories != null) {
                            for (History history : histories) {
                                screen.displayMessage(history.getKeterangan() + " ");
                                screen.displayDollarAmount(history.getAmount());
                                screen.displayMessageLine("");
                            }
                        } else {
                            screen.displayMessageLine("You don't have any previous transaction..");
                        }
                        break;
                    case CHANGEPIN:
                        currentTransaction = createTransaction(mainMenuSelection);
                        currentTransaction.execute();
                        break;
                    case TRANSFER:
                        currentTransaction
                                = createTransaction(mainMenuSelection);
                        currentTransaction.execute();
                        break;
                    case EXIT: // user chose to terminate session
                        screen.displayMessageLine("\nExiting the system...");
                        userExited = true; // this ATM session should end
                        break;
                    default: // 
                        screen.displayMessageLine(
                                "\nYou did not enter a valid selection. Try again.");
                        break;
                }
            }
        }
    }

    private void validateDeposit(int acc) {
        int a = 1;
        ArrayList<History> histories = bankDatabase.getHistories(acc);
        if (histories != null) {
            for (History history : histories) {
                screen.displayMessage("Number " + a + " (");
                screen.displayMessage("" + acc + " - ");
                screen.displayMessage("" + history.getDeposit().isValid() + " - ");
                screen.displayDollarAmount(history.getAmount());
                screen.displayMessageLine(")");
                a++;
            }
            screen.displayMessage("Input the number beside the deposit history that you would like to validate = ");
            int key = keypad.getInput();
            History h_validate = histories.get(key - 1);
            h_validate.getDeposit().validate(h_validate.getAmount());
        } else {
            screen.displayMessageLine("You don't have any previous transaction..");
        }
    }

    // display the main menu and return an input selection
    private int displayMainMenu() {
        if (!admin) {
            screen.displayMessageLine("\nMain menu:");
            screen.displayMessageLine("1 - View my balance");
            screen.displayMessageLine("2 - Withdraw cash");
            screen.displayMessageLine("3 - Deposit funds");
            screen.displayMessageLine("4 - Change PIN");
            screen.displayMessageLine("5 - Transfer");
            screen.displayMessageLine("6 - History\n");
            screen.displayMessageLine("7 - Exit\n");

            screen.displayMessage("Enter a choice: ");
        } else {
            screen.displayMessageLine("\nMain menu:");
            screen.displayMessageLine("1 - Unblock Nasabah");
            screen.displayMessageLine("2 - Tambah Nasabah");
            screen.displayMessageLine("3 - Lihat Uang Dispenser");
            screen.displayMessageLine("4 - Tambah Uang Dispenser");
            screen.displayMessageLine("5 - Validasi Deposit");
            screen.displayMessageLine("6 - Atur Tanggal");
            screen.displayMessageLine("7 - Exit\n");
            screen.displayMessage("Enter a choice: ");
        }
        return keypad.getInput(); // return user's selection
    }

    private Transaction createTransaction(int type) {
        Transaction temp = null;
        switch (type) {
            case BALANCE_INQUIRY:
                temp = new BalanceInquiry(currentAccountNumber, screen, bankDatabase);
                break;
            case WITHDRAWAL:
                temp = new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
                break;
            case DEPOSIT:
                temp = new Deposit(currentAccountNumber, screen, bankDatabase, keypad, ATMDepositSlot);
                break;
            case TRANSFER:
                temp = new Transfer(currentAccountNumber, screen, bankDatabase, keypad);
                break;
            case CHANGEPIN:
                temp = new UbahPIN(currentAccountNumber, screen, bankDatabase, keypad);
                break;
        }

        return temp;
    }
}
