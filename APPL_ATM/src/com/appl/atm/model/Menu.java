/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

import static com.appl.atm.model.Constants.*;

/**
 *
 * @author C70
 */
public class Menu {

    private int noPilihan;
    private String keteranganPilihan;
    private boolean forAdmin;
    private boolean forSiswa;
    private boolean forBisnis;
    private boolean forMasaDepan;

    public Menu(int noPilihan, String keteranganPilihan, boolean forAdmin,
	    boolean forSiswa, boolean forBisnis, boolean forMasaDepan) {
	
	this.noPilihan = noPilihan;
	this.keteranganPilihan = keteranganPilihan;
	this.forAdmin = forAdmin;
	this.forSiswa = forSiswa;
	this.forBisnis = forBisnis;
	this.forMasaDepan = forMasaDepan;
    }
    
    public boolean isAvailable(int accountType)
    {
	switch(accountType) {
	    case ADMIN : return isForAdmin();
	    case SISWA : return isForSiswa();
	    case BISNIS : return isForBisnis();
	    case MASA_DEPAN : return isForMasaDepan();
	    default : return false;
	}
    }

    /**
     * @return the noPilihan
     */
    public int getNoPilihan() {
	return noPilihan;
    }

    /**
     * @param noPilihan the noPilihan to set
     */
    public void setNoPilihan(int noPilihan) {
	this.noPilihan = noPilihan;
    }

    /**
     * @return the keteranganPilihan
     */
    public String getKeteranganPilihan() {
	return keteranganPilihan;
    }

    /**
     * @param keteranganPilihan the keteranganPilihan to set
     */
    public void setKeteranganPilihan(String keteranganPilihan) {
	this.keteranganPilihan = keteranganPilihan;
    }

    /**
     * @return the forAdmin
     */
    public boolean isForAdmin() {
	return forAdmin;
    }

    /**
     * @param forAdmin the forAdmin to set
     */
    public void setForAdmin(boolean forAdmin) {
	this.forAdmin = forAdmin;
    }

    /**
     * @return the forSiswa
     */
    public boolean isForSiswa() {
	return forSiswa;
    }

    /**
     * @param forSiswa the forSiswa to set
     */
    public void setForSiswa(boolean forSiswa) {
	this.forSiswa = forSiswa;
    }

    /**
     * @return the forBisnis
     */
    public boolean isForBisnis() {
	return forBisnis;
    }

    /**
     * @param forBisnis the forBisnis to set
     */
    public void setForBisnis(boolean forBisnis) {
	this.forBisnis = forBisnis;
    }

    /**
     * @return the forMasaDepan
     */
    public boolean isForMasaDepan() {
	return forMasaDepan;
    }

    /**
     * @param forMasaDepan the forMasaDepan to set
     */
    public void setForMasaDepan(boolean forMasaDepan) {
	this.forMasaDepan = forMasaDepan;
    }
    
    
}
