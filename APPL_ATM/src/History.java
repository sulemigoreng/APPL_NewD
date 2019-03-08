/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Regawa
 */
public class History {

    private String keterangan;
    private double amount;
    private Deposit sDeposit;

    public History(String keterangan, double amount, Deposit sDeposit) {
        this.keterangan = keterangan;
        this.amount = amount;
        this.sDeposit = sDeposit;
    }

    public History(String keterangan, double amount) {
        this.keterangan = keterangan;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getKeterangan() {
        return keterangan;
    }
//

    public Deposit getDeposit() {
        return sDeposit;
    }
//
//    public void setsDeposit(boolean sDeposit) {
//        this.sDeposit = sDeposit;
//    }

}
