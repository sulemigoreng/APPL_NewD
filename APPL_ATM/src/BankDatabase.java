
import java.util.ArrayList;

public class BankDatabase {

   private Account[] accounts; // array of Accounts
   
   public BankDatabase() {
      accounts = new Account[10]; // just 2 accounts for testing
      accounts[0] = new Account(12345, 54321, 1000.0, 1200.0,false);
      accounts[1] = new Account(8765, 5678, 200.0, 200.0,false);
      accounts[2] = new Account(0, 0, 0, 0,true); 
      accounts[3] = new Account(1, 1, 1, 1, false, false, new ArrayList<History>());
   }
   
   private Account getAccount(int accountNumber) {
      for(Account x : this.accounts){
          if(x.getAccountNumber()==accountNumber){
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

    public boolean isAdmin(int accountNumber) {
        for (Account x : this.accounts) {
            if (x.getAccountNumber() == accountNumber) {
                return x.getAdmin();
            }
        }
        return false; // if no matching account was found, return null
    }

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

    public void debit(int userAccountNumber, double amount) {
        getAccount(userAccountNumber).debit(amount);
    }
    
    public void transfer(int userAccountNumber, double amount) {
        getAccount(userAccountNumber).transfer(amount);
    }
    public ArrayList<History> getHistories(int userAccountNumber){
       return getAccount(userAccountNumber).getHistories();
   }
}

   
   