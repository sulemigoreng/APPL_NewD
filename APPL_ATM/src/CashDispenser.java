public class CashDispenser {
   // the default initial number of bills in the cash dispenser
   private static final int INITIAL_COUNT = 500;
   private int count; // number of $20 bills remaining
   private Screen screen = new Screen(); // ATM screen
   
   
   // no-argument CashDispenser constructor initializes count to default
   public CashDispenser(Screen atmScreen) {
      count = INITIAL_COUNT; // set count attribute to default
      screen  = atmScreen;
   }

   
   // simulates dispensing of specified amount of cash
   public void dispenseCash(int amount) {
      int billsRequired = amount / 20; // number of $20 bills required
      count -= billsRequired; // update the count of bills
   }
   
//   public boolean cekAdmin() {
//       if (this.admin == true) {
//       return true;
//   }
//       else {
//           return false;
//       
//       }
//   }
//
   
    public void displayDispenser() {
       screen.displayMessage("Total Cash Dispenser: "+count); 
    }
            
    public void addCashDispenser(double amount){
       count += amount;
   }
    
    
    
   // indicates whether cash dispenser can dispense desired amount
   public boolean isSufficientCashAvailable(int amount) {
      int billsRequired = amount / 20; // number of $20 bills required

      if (count >= billsRequired) {
         return true; // enough bills available
      }
      else {
         return false; // not enough bills available
      }
   }
} 