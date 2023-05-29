package com.example.familycollection.models;

public class User {
    private String idUser;
    private String email;
    private String username;
    private String nama;
    private String notelp;


    public User(String idUser, String email, String username, String nama, String notelpn) {
        this.idUser = idUser;
        this.email = email;
        this.username = username;
        this.nama = nama;
        this.notelp = notelpn;

    }

    public String getIdUser() {
        return idUser;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
    public String getNama(){return nama;}
    public String getNotelpn(){return notelp;}
}
