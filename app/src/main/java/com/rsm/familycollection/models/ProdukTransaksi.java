package com.rsm.familycollection.models;

public class ProdukTransaksi {
    private int fotoproduk;
    private String namaproduk;
    private String berat;
    private String harga;
    private String item;
    private String total;


    public ProdukTransaksi(int fotoproduk, String namaproduk, String berat, String harga, String item, String total){
        this.fotoproduk= fotoproduk;
        this.namaproduk=namaproduk;
        this.berat=berat;
        this.harga=harga;
        this.item=item;
        this.total=total;
    }

    public int getFotoproduk() {
        return fotoproduk;
    }

    public String getNamaproduk() {
        return namaproduk;
    }

    public String getBerat() {
        return berat;
    }

    public String getHarga() {
        return harga;
    }

    public String getItem() {
        return item;
    }

    public String getTotal() {
        return total;
    }

    public void setFotoproduk(int fotoproduk) {
        this.fotoproduk = fotoproduk;
    }

    public void setNamaproduk(String namaproduk) {
        this.namaproduk = namaproduk;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
