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
    private int amount;

    public History(String keterangan, int amount) {
        this.keterangan = keterangan;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public String getKeterangan() {
        return keterangan;
    }
    
    
}
