package com.rsm.familycollection.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetProvince {


    @SerializedName("results")
    List<Province> provinces;

    public GetProvince() {
    }

    public GetProvince(List<Province> provinces) {
        this.provinces = provinces;
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }
}
