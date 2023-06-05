package com.example.familycollection.models;

import com.google.gson.annotations.SerializedName;

public class Province {

    @SerializedName("province_id")
    private String province_id;
    @SerializedName("province")
    private String province;

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
}
