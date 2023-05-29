package com.example.familycollection.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.familycollection.models.User;

public class SessionHandler {
    private static final String PREF_NAME = "UserSession";
    private static final String KEY_ID = "id_user";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_NOTELP = "notelp";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_EMPTY = "";
    private Context mContext;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mPreferences;

    public SessionHandler(Context mContext) {
        this.mContext = mContext;
        mPreferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.mEditor = mPreferences.edit();
    }

    public void loginUser(String idUser, String email, String username, String nama, String notelp) {
        mEditor.putString(KEY_ID, idUser);
        mEditor.putString(KEY_USERNAME, username);
        mEditor.putString(KEY_EMAIL, email);
        mEditor.putString(KEY_NAMA, nama);
        mEditor.putString(KEY_NOTELP, notelp);
        mEditor.commit();
    }

    public User getUserDetails() {
        User user = new User(mPreferences.getString(KEY_ID, KEY_EMPTY), mPreferences.getString(KEY_USERNAME, KEY_EMPTY), mPreferences.getString(KEY_EMAIL, KEY_EMPTY), mPreferences.getString(KEY_NAMA, KEY_EMPTY), mPreferences.getString(KEY_NOTELP, KEY_EMPTY));
        return user;
    }

    public void logoutUser() {
        mEditor.clear();
        mEditor.commit();
    }
}
