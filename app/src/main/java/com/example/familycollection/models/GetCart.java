package com.example.familycollection.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCart {

    @SerializedName("success")
    String success;
    @SerializedName("data")
    @Expose
    List<Cart> cartList=null;

    @SerializedName("total")
    String total;

    @SerializedName("weight")
    String weight;

    public GetCart() {
    }

    public GetCart(String success, List<Cart> cartList, String total, String weight) {
        this.success = success;
        this.cartList = cartList;
        this.total = total;
        this.weight = weight;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
