package com.rsm.familycollection.models;

public class Pesan {

    private String namaproduk;
    private String tanggal;
    private String total;
    private String jumlah;
    private String status;




        public Pesan(String namaproduk, String tanggal, String total, String jumlah, String status){
            this.namaproduk= namaproduk;
            this.tanggal=tanggal;
            this.total= total;
            this.jumlah= jumlah;
            this.status= status;

        }

    public String getNamaproduk() {
        return namaproduk;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getTotal() {
        return total;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getStatus() {
        return status;
    }
}
