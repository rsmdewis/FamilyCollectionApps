package com.rsm.familycollection.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetProduct {

    @SerializedName("success")
    String success;
    @SerializedName("data")
    @Expose
    List<Product>  listProduct=null;

    public GetProduct() {
    }

    public GetProduct(String success, List<Product> listProduct) {
        this.success = success;
        this.listProduct = listProduct;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }
}
