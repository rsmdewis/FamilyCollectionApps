package com.example.familycollection.models;

public class Kategori {
    private int fotokategori;
    private String namakategori;


    public Kategori(int fotokategori, String namakategori){
        this.fotokategori= fotokategori;
        this.namakategori=namakategori;
    }

    public int getFotokategori() {
        return fotokategori;
    }

    public String getNamakategori() {
        return namakategori;
    }
}
