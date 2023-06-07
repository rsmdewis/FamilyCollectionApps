package com.example.familycollection.models;

import com.google.gson.annotations.SerializedName;

public class AddPayment {
    @SerializedName("success")
    String success;
    @SerializedName("message")
    String message;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
