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

    @SerializedName("product")
    private Product listProduct=null;

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
}
