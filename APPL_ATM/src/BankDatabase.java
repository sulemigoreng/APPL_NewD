
import java.util.ArrayList;

public class BankDatabase {

    private ArrayList<Account> accounts; // array of Accounts

    public enum Jenis {

        Siswa,
        Bisnis,
        Masa_Depan,
        Admin
    }

    public BankDatabase() {
        accounts = new ArrayList<>();
        accounts.add(new Account(12345, 54321, 1000.0, 1200.0, false, Jenis.Siswa, new ArrayList<History>()));
        accounts.add(new Account(8765, 5678, 200.0, 200.0, false, Jenis.Masa_Depan, new ArrayList<History>()));
        accounts.add(new Account(0, 0, 0, 0, true, Jenis.Siswa, new ArrayList<History>()));
        accounts.add(new Account(1, 1, 1, 1, false, Jenis.Admin));
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public Account getAccount(int accountNumber) {
        for (Account x : this.accounts) {
            if (x.getAccountNumber() == accountNumber) {
                return x;
            }
        }
        return null; // if no matching account was found, return null
    }

    public boolean authenticateUser(int userAccountNumber, int userPIN) {
        // attempt to retrieve the account with the account number
        Account userAccount = getAccount(userAccountNumber);

        // if account exists, return result of Account method validatePIN
        if (userAccount != null) {
            return userAccount.validatePIN(userPIN);
        } else {
            return false; // account number not found, so return false
        }
    }

//    public boolean isAdmin(int accountNumber) {
//        for (Account x : this.accounts) {
//            if (x.getAccountNumber() == accountNumber) {
//                return x.getStatus();
//            }
//        }
//        return false; // if no matching account was found, return null
//    }
    public Account checkAccountDest(int userAccountNumberDest) {
        for (Account x : this.accounts) {
            if (x.getAccountNumber() == userAccountNumberDest) {
                return x;
            }
        }
        return null;
    }

    public void transferToAccount(int userAccountNumberSource, int userAccountNumberDest, double amount) {
        credit(userAccountNumberSource, amount);
        transfer(userAccountNumberDest, amount);
    }

    public double getAvailableBalance(int userAccountNumber) {
        return getAccount(userAccountNumber).getAvailableBalance();
    }

    public double getTotalBalance(int userAccountNumber) {
        return getAccount(userAccountNumber).getTotalBalance();
    }

    public void credit(int userAccountNumber, double amount) {
        getAccount(userAccountNumber).credit(amount);
    }

    public void validate(int userAccountNumber, double amount) {
        getAccount(userAccountNumber).validated(amount);
    }

    public void debit(int userAccountNumber, double amount, Deposit x) {
        getAccount(userAccountNumber).debit(amount, x);
    }

    public void transfer(int userAccountNumber, double amount) {
        getAccount(userAccountNumber).transfer(amount);
    }

    public ArrayList<History> getHistories(int userAccountNumber) {
        return getAccount(userAccountNumber).getHistories();
    }

    public boolean isExists(int accountNumber) {
        for (Account x : this.accounts) {
            if (x.getAccountNumber() == accountNumber) {
                return true;
            }
        }
        return false; // if no matching account was found, return null
    }

    public Account getAccountUser(int accountNumber) {
        for (Account x : this.accounts) {
            if (x.getAccountNumber() == accountNumber) {
                return x;
            }
        }
        return null; // if no matching account was found, return null
    }

    public void unblock(int userAccountNumber) {
        getAccount(userAccountNumber).setBlocked(false);
    }

    public void addAccount(int userAccountNumber, int userAccountPIN, BankDatabase.Jenis status) {
        boolean stop = false;
        int i = 0;
        accounts.add(new Account(userAccountNumber, userAccountPIN, 0, 0, false, status, new ArrayList<History>()));
    }
}
