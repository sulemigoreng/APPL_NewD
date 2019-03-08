
import java.util.ArrayList;

public class Account {

    private int accountNumber; // account number
    private int pin; // PIN for authentication
    private double availableBalance; // funds available for withdrawal
    private double totalBalance; // funds available & pending deposits
    private boolean blocked;
    private boolean admin;
    private double limitTransfer;
    private double limitCash;
    private ArrayList<History> histories;

    // Account constructor initializes attributes
    public Account(int theAccountNumber, int thePIN,
            double theAvailableBalance, double theTotalBalance, boolean blocked, boolean admin, ArrayList<History> histories) {
        accountNumber = theAccountNumber;
        pin = thePIN;
        availableBalance = theAvailableBalance;
        totalBalance = theTotalBalance;
        if (admin) {
            blocked = false;
        } else {
            blocked = true;
        }
        this.histories = histories;
        this.admin = admin;

        if (atatus.toUpperCase().equals("SISWA")) {
            limitCash = 20;
        } else if (status.toUpperCase().equals("MASA DEPAN")) {
            limitTransfer = 500;
            limitCash = 100;
        } else {
            limitTransfer = 10000;
            limitCash = 1000;
        }
    }

    public void setLimitCash(double limitCash) {
        this.limitCash = limitCash;
    }

    public void setLimitTransfer(double limitTransfer) {
        this.limitTransfer = limitTransfer;
    }

    public double getLimitTransfer() {
        return limitTransfer;
    }

    public double getLimitCash() {
        return limitCash;
    }
    
    

    // Account constructor initializes attributes
    // determines whether a user-specified PIN matches PIN in Account
    public boolean validatePIN(int userPIN) {
        if (userPIN == pin) {
            return true;
        } else {
            return false;
        }
    }

    // returns available balance
    public double getAvailableBalance() {
        return availableBalance;
    }

    // returns the total balance
    public double getTotalBalance() {
        return totalBalance;

    }

    public boolean isBlocked() {
        return blocked;
    }

    public void credit(double amount) {
        availableBalance -= amount;
        totalBalance -= amount;
        addHistory(1, amount, null);
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public void validated(double amount) {
        availableBalance += amount;
    }

    public void debit(double amount, Deposit x) {
        totalBalance += amount;
        addHistory(2, amount, x);
    }

    public void transfer(double amount) {
        availableBalance += amount;
        totalBalance += amount;
    }

    public int getAccountNumber() {
        return accountNumber;

    }

    public boolean getAdmin() {
        return admin;
    }

    public ArrayList<History> getHistories() {
        return histories;
    }

    public void addHistory(int transaction, double amount, Deposit x) {
        String keterangan = null;
        if (transaction == 1) {
            keterangan = "Withdrawal";
            this.histories.add(new History(keterangan, amount));
        } else if (transaction == 2) {
            keterangan = "Deposit";
            this.histories.add(new History(keterangan, amount, x));
        }

    }

    public void setPin(int pin) {
        this.pin = pin;
    }

}
