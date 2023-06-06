package com.example.familycollection.models;

import com.google.gson.annotations.SerializedName;

public class OngkirCost {

    @SerializedName("success")
    private String success;

    @SerializedName("cost")
    private String cost;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
