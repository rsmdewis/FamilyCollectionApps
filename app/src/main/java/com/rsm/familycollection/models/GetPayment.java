package com.rsm.familycollection.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPayment {
    @SerializedName("success")
    private String success;

    @SerializedName("data")
    List<Payment> paymentList;

    public GetPayment() {
    }

    public GetPayment(String success, List<Payment> paymentList) {
        this.success = success;
        this.paymentList = paymentList;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }
}
