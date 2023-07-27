package com.rsm.familycollection.models;

import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("province_id")
    private String province_id;
    @SerializedName("province")
    private String province;
    @SerializedName("city_id")
    private String city_id;
    @SerializedName("city_name")
    private String city_name;


    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
}
