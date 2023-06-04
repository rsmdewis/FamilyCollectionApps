package com.example.familycollection.RestApi;


import com.example.familycollection.Model.GetAkun;
import com.example.familycollection.models.AddCart;
import com.example.familycollection.models.DeleteCart;
import com.example.familycollection.models.GetCart;
import com.example.familycollection.models.GetProduct;
import com.example.familycollection.models.GetProductCategory;
import com.example.familycollection.models.Register;
import com.example.familycollection.models.UpdateUser;

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

    @GET("cart")
    Call<GetCart> getCart(@Query("user_id") String user_id, @Header("Authorization") String auth);

    @GET("cart/delete")
    Call<DeleteCart> deleteCart(@Query("id") String id, @Header("Authorization") String auth);

    @FormUrlEncoded
    @POST("cart/add")
    Call<AddCart> postCart(@Header("Authorization") String auth,
                           @Field("user_id") String user_id,
                            @Field("product_id") String product_id);

    @FormUrlEncoded
    @POST("auth/register")
    Call<Register> register(@Field("email") String email,
                            @Field("phone") String phone,
                            @Field("password") String password,
                            @Field("name") String name);

    @FormUrlEncoded
    @POST("auth/update")
    Call<UpdateUser> updateUser(
                            @Header("Authorization") String auth,
                            @Field("id") String id,
                            @Field("email") String email,
                            @Field("phone") String phone,
                            @Field("password") String password,
                            @Field("name") String name);

}
