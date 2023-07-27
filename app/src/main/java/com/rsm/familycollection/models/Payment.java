package com.rsm.familycollection.models;

import com.google.gson.annotations.SerializedName;

public class Payment {
    @SerializedName("id")
    private String id;

    @SerializedName("price")
    private String price;

    @SerializedName("image")
    private String image;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("status")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
