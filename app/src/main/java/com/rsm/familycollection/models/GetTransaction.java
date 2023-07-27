package com.rsm.familycollection.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetTransaction {

    @SerializedName("success")
    String success;
    @SerializedName("data")
    @Expose
    List<Transaction> transactionList=null;

    public GetTransaction() {
    }

    public GetTransaction(String success, List<Transaction> transactionList) {
        this.success = success;
        this.transactionList = transactionList;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
