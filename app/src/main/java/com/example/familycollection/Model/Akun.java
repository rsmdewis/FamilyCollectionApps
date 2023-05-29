package com.example.familycollection.Model;

import com.google.gson.annotations.SerializedName;

public class Akun {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String password;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
