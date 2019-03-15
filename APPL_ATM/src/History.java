
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Regawa
 */
public class History implements Comparable<History> {

    private String keterangan;
    private double amount;
    private Deposit sDeposit;
    private Date date;

    public History(String keterangan, double amount, Deposit sDeposit, Date date) {
        this.keterangan = keterangan;
        this.amount = amount;
        this.sDeposit = sDeposit;
        this.date = date;
    }

    public History(String keterangan, double amount, Date date) {
        this.keterangan = keterangan;
        this.amount = amount;
        this.date = date;
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
    
    public Date getDate(){
        return date;
    }
//
//    public void setsDeposit(boolean sDeposit) {
//        this.sDeposit = sDeposit;
//    }

    @Override
    public int compareTo(History o) {
        if (amount > o.getAmount())
            return 1;
        if (amount < o.getAmount())
            return -1;
        return 0;
    }

}
