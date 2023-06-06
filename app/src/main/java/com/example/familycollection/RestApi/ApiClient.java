package com.example.familycollection.RestApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class  ApiClient {

<<<<<<< HEAD
    public static final String BASE_URL = "http://192.168.1.32:8000/api/";
=======
    public static final String BASE_URL = "http://192.168.1.10:8000/api/";
>>>>>>> f1790af6e206f4aec1e14ceaf6e4f86460a7b621
    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
