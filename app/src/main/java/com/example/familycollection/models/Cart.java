package com.example.familycollection.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cart {

    @SerializedName("id")
    private String id;
    @SerializedName("user_id")
    private String user_id;

    @SerializedName("product_id")
    private String product_id;

    @SerializedName("size")
    private String size;

    @SerializedName("qty")
    private String qty;

    @SerializedName("product")
    private Product listProduct=null;

    @SerializedName("nama")
    private String nama;

    @SerializedName("weight")
    private String weight;

    @SerializedName("price")
    private String price;

    @SerializedName("total_qty")
    private String total_qty;

    @SerializedName("total_weight")
    private String total_weight;

    @SerializedName("gambar")
    private String gambar;






    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public Product getListProduct() {
        return listProduct;
    }

    public void setListProduct(Product listProduct) {
        this.listProduct = listProduct;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal_qty() {
        return total_qty;
    }

    public void setTotal_qty(String total_qty) {
        this.total_qty = total_qty;
    }

    public String getTotal_weight() {
        return total_weight;
    }

    public void setTotal_weight(String total_weight) {
        this.total_weight = total_weight;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
