package com.example.familycollection.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCity {


    @SerializedName("results")
    List<City> cityList;

    public GetCity() {
    }

    public GetCity(List<City> cityList) {
        this.cityList = cityList;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
