package com.example.familycollection.RestApi;


import com.example.familycollection.Model.GetAkun;
import com.example.familycollection.models.GetProduct;
import com.example.familycollection.models.GetProductCategory;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("auth/login")
    Call<GetAkun> postLogin(@Field("email") String email,
                            @Field("password") String password);

    @GET("product")
    Call<GetProduct> getProduct(@Query("product_category_id") String product_category_id, @Header("Authorization") String auth);

    @GET("product/category")
    Call<GetProductCategory> getCategory(@Header("Authorization") String auth);

}
