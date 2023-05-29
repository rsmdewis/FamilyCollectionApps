package com.example.familycollection.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetProductCategory {
    @SerializedName("success")
    private String success;
    @SerializedName("data")
    @Expose
    List<ProductCategory> listProductCategory=null;

    public GetProductCategory() {
    }

    public GetProductCategory(String success, List<ProductCategory> listProductCategory) {
        this.success = success;
        this.listProductCategory = listProductCategory;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<ProductCategory> getListProductCategory() {
        return listProductCategory;
    }

    public void setListProductCategory(List<ProductCategory> listProductCategory) {
        this.listProductCategory = listProductCategory;
    }
}
