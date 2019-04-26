
import java.util.ArrayList;
import java.util.Date;

public class Account {

    private int accountNumber; // account number
    private int pin; // PIN for authentication
    private double availableBalance; // funds available for withdrawal
    private double totalBalance; // funds available & pending deposits
    private boolean blocked;
    private String status;
//    private boolean admin;

    private boolean admin;
    private double limitTransfer;
    private double limitCash;
    private ArrayList<History> histories;
    private ArrayList<Payment> payments;

    // Account constructor initializes attributes
    public Account(int theAccountNumber, int thePIN,
            double theAvailableBalance, double theTotalBalance,
            boolean blocked, BankDatabase.Jenis status, ArrayList<History> histories, ArrayList<Payment> payments) {
        accountNumber = theAccountNumber;
        pin = thePIN;
        availableBalance = theAvailableBalance;
        totalBalance = theTotalBalance;
        this.blocked = blocked;
        this.status = status.toString();
        this.histories = histories;
        this.payments = payments;
        this.admin = admin;

        if (this.status.toUpperCase().equals("SISWA")) {
            limitCash = 20;
        } else if (this.status.toUpperCase().equals("MASA DEPAN")) {
            limitTransfer = 500;
            limitCash = 100;
        } else {
            limitTransfer = 10000;
            limitCash = 1000;
        }
//        this.admin = admin;
    }

    Account(int theAccountNumber, int thePIN,
            double theAvailableBalance, double theTotalBalance,
            boolean blocked, BankDatabase.Jenis status) {
        accountNumber = theAccountNumber;
        pin = thePIN;
        availableBalance = theAvailableBalance;
        totalBalance = theTotalBalance;
        this.blocked = blocked;
        this.status = status.toString();
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
        addHistory(3, amount, null);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public ArrayList<History> getHistories() {
        return histories;
    }

    public void addHistory(int transaction, double amount, Deposit x) {
        Date today = ATM.tanggal;
        String keterangan = null;
        if (transaction == 1) {
            keterangan = "Withdrawal";
            this.histories.add(new History(keterangan, amount, today));
        } else if (transaction == 2) {
            keterangan = "Deposit";
            this.histories.add(new History(keterangan, amount, x, today));
        } else if (transaction == 3) {
            keterangan = "Transfer";
            this.histories.add(new History(keterangan, amount, today));
        }

    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public void addPayment(String info, double amount) {
        this.payments.add(new Payment(info, amount));
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

}
