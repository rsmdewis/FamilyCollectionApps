package com.rsm.familycollection.models;

import com.google.gson.annotations.SerializedName;

public class OrderDetail {

    @SerializedName("id")
    private String id;

    @SerializedName("code_transaction")
    private String code_transaction;

    @SerializedName("name")
    private String name;

    @SerializedName("phone")
    private String phone;

    @SerializedName("image")
    private String image;

    @SerializedName("description")
    private String description;
    @SerializedName("pengiriman")
    private String pengiriman;
    @SerializedName("province")
    private String province;
    @SerializedName("city")
    private String city;
    @SerializedName("address")
    private String address;
    @SerializedName("courier")
    private String courier;
    @SerializedName("cost")
    private String cost;
    @SerializedName("total")
    private String total;
    @SerializedName("total_bayar")
    private String total_bayar;

    @SerializedName("total_pelunasan")
    private String total_pelunasan;
    @SerializedName("deadline")
    private String deadline;

    @SerializedName("negosiasi")
    private String negosiasi;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode_transaction() {
        return code_transaction;
    }

    public void setCode_transaction(String code_transaction) {
        this.code_transaction = code_transaction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPengiriman() {
        return pengiriman;
    }

    public void setPengiriman(String pengiriman) {
        this.pengiriman = pengiriman;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal_bayar() {
        return total_bayar;
    }

    public void setTotal_bayar(String total_bayar) {
        this.total_bayar = total_bayar;
    }

    public String getTotal_pelunasan() {
        return total_pelunasan;
    }

    public void setTotal_pelunasan(String total_pelunasan) {
        this.total_pelunasan = total_pelunasan;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getNegosiasi() {
        return negosiasi;
    }

    public void setNegosiasi(String negosiasi) {
        this.negosiasi = negosiasi;
    }
}




