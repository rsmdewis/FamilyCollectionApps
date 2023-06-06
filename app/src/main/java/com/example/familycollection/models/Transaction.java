package com.example.familycollection.models;

import com.google.gson.annotations.SerializedName;

public class Transaction {

    @SerializedName("id")
    private String id;
    @SerializedName("code")
    private String code;
    @SerializedName("status")
    private String status;
    @SerializedName("total")
    private String total;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("total_product")
    private String total_product;

    @SerializedName("pengiriman")
    private String pengiriman;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTotal_product() {
        return total_product;
    }

    public void setTotal_product(String total_product) {
        this.total_product = total_product;
    }

    public String getPengiriman() {
        return pengiriman;
    }

    public void setPengiriman(String pengiriman) {
        this.pengiriman = pengiriman;
    }
}
