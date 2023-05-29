package com.example.familycollection.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAkun {

    @SerializedName("status")
    String status;
    @SerializedName("user")
    @Expose
    private Akun listDataAkun=null;
    @SerializedName("token")
    String token;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Akun getListDataAkun() {
        return listDataAkun;
    }

    public void setListDataAkun(Akun listDataAkun) {
        this.listDataAkun = listDataAkun;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
