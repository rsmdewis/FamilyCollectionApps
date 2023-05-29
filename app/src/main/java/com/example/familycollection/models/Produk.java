package com.example.familycollection.models;

public class Produk {
    private int fotoproduk;
    private String namaproduk;
    private String harga;


    public Produk(int fotoproduk, String namaproduk, String harga){
        this.fotoproduk= fotoproduk;
        this.namaproduk=namaproduk;
        this.harga=harga;
    }

    public int getFotoproduk() {
        return fotoproduk;
    }

    public String getNamaproduk() {
        return namaproduk;
    }

    public String getHarga() {
        return harga;
    }

    public void setFotoproduk(int fotoproduk) {
        this.fotoproduk = fotoproduk;
    }

    public void setNamaproduk(String namaproduk) {
        this.namaproduk = namaproduk;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
