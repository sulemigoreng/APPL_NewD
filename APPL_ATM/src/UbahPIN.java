/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Randi
 */
public class UbahPIN extends Transaction{
   private int noAkun;
   private boolean userAuthenticated;
   private int amount; // amount to withdraw
   private Keypad keypad; // reference to keypad
   //private CashDispenser cashDispenser; // reference to cash dispenser
    
   public UbahPIN(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad){
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        
        userAuthenticated = false;
        this.noAkun = userAccountNumber;
   }
   
   
   
   
    public void execute(){
        BankDatabase bankDatabase = getBankDatabase();
        Account userAccount = bankDatabase.getAccount(noAkun);
        //Account userAccount = getAccount(userAccountNumber);
        
        Screen screen = getScreen();
        int pinBaru;
        
        screen.displayMessage("Insert your current PIN : ");
        int input = keypad.getInput();
        
        userAuthenticated = bankDatabase.authenticateUser(noAkun, input);
        while(!userAuthenticated){
            screen.displayMessageLine("You input the wrong PIN");
            screen.displayMessage("Insert your current PIN : ");
            input = keypad.getInput();
            
            userAuthenticated = bankDatabase.authenticateUser(noAkun, input);
        }
    
        if(userAuthenticated){
            
            screen.displayMessage("Insert your new PIN : ");
            pinBaru = keypad.getInput();
            
            while(pinBaru == input){
                screen.displayMessageLine("Your new PIN is the same as your current PIN..");
                screen.displayMessage("Insert your new PIN : ");
                pinBaru = keypad.getInput();
            }
            
            userAccount.setPin(pinBaru);
        }else{
            screen.displayMessageLine("You input the wrong PIN");
        }
    }
}
