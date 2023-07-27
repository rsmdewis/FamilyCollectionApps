package com.rsm.familycollection.models;

import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("id")
    private String id;

    @SerializedName("code")
    private String code;

    @SerializedName("size")
    private String size;

    @SerializedName("qty")
    private String qty;

    @SerializedName("status")
    private String status;

    @SerializedName("product")
    private Product listProduct=null;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
