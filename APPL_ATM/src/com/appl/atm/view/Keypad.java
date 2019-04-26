package com.appl.atm.view;

import java.util.Scanner;

public class Keypad {
   private Scanner input; // reads data from the command line
                         
   public Keypad() {
      input = new Scanner(System.in);    
   } 

   public int getInputInt() {
      return input.nextInt(); // user enters an integer
   }
   
   public String getInputStr() {
       return input.nextLine(); // user enters an string
   }
   
   public Double getInputDbl() {
       return input.nextDouble();
   }
} 