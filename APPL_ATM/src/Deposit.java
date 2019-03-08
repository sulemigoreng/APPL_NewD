
public class Deposit extends Transaction {

    private double amount; // amount to deposit
    private Keypad keypad; // reference to keypad
    private DepositSlot depositSlot; // reference to deposit slot
    private final static int CANCELED = 0; // constant for cancel option
    private boolean sValid;

    // Deposit constructor
    public Deposit(int userAccountNumber, Screen atmScreen,
            BankDatabase atmBankDatabase, Keypad atmKeypad,
            DepositSlot atmDepositSlot) {

        // initialize superclass variables
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        depositSlot = atmDepositSlot;
        sValid = false;
    }

    public boolean isValid() {
        return sValid;
    }

    public void validate(double amount) {
        BankDatabase bankDatabase = getBankDatabase();
        sValid = depositSlot.isEnvelopeReceived();
        bankDatabase.validate(super.getAccountNumber(), amount);
    }

    // perform transaction
    @Override
    public void execute() {
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen(); // get reference to screen
        amount = promptForDepositAmount();
        screen.displayMessageLine("Please insert a deposit envelope containing $" + amount + ".");
        if (depositSlot.isEnvelopeReceived()) {
            bankDatabase.debit(super.getAccountNumber(), amount, this);
            screen.displayMessageLine("Your envelope has been received.");
            screen.displayMessageLine("NOTE: The money just deposited will not be available until we verify the amount of any enclosed cash and your checks clear.");
        } else {
            screen.displayMessageLine("Envelope is not received!");
        }
    }

    // prompt user to enter a deposit amount in cents 
    private double promptForDepositAmount() {
        Screen screen = getScreen(); // get reference to screen

        // display the prompt
        screen.displayMessage("\nPlease enter a deposit amount in "
                + "CENTS (or 0 to cancel): ");
        int input = keypad.getInput(); // receive input of deposit amount

        // check whether the user canceled or entered a valid amount
        if (input == CANCELED) {
            return CANCELED;
        } else {
            return (double) input / 100; // return dollar amount
        }
    }
}
