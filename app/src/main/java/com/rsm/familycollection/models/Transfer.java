package com.rsm.familycollection.models;

public class Transfer {
    private int buktiTransfer;
    private String tanggal;
    private String nominal;


    public Transfer(int buktiTransfer, String tanggal, String nominal){
        this.buktiTransfer= buktiTransfer;
        this.tanggal=tanggal;
        this.nominal=nominal;
    }

    public int getBuktiTransfer() {
        return buktiTransfer;
    }

    public void setBuktiTransfer(int buktiTransfer) {
        this.buktiTransfer = buktiTransfer;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }
}
