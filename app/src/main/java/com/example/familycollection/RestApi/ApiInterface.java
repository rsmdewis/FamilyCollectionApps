package com.example.familycollection.RestApi;


import com.example.familycollection.Model.GetAkun;
import com.example.familycollection.models.AddCart;
import com.example.familycollection.models.AddCheckout;
import com.example.familycollection.models.AddPayment;
import com.example.familycollection.models.DeleteCart;
import com.example.familycollection.models.GetCart;
import com.example.familycollection.models.GetCity;
import com.example.familycollection.models.GetPayment;
import com.example.familycollection.models.GetProduct;
import com.example.familycollection.models.GetProductCategory;
import com.example.familycollection.models.GetProvince;
import com.example.familycollection.models.GetTransaction;
import com.example.familycollection.models.OngkirCost;
import com.example.familycollection.models.Register;
import com.example.familycollection.models.ShowOrder;
import com.example.familycollection.models.UpdateStatus;
import com.example.familycollection.models.UpdateUser;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("auth/login")
    Call<GetAkun> postLogin(@Field("email") String email,
                            @Field("password") String password);

    @GET("product")
    Call<GetProduct> getProduct(@Query("product_category_id") String product_category_id, @Header("Authorization") String auth,@Query("search") String search);

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
                           @Field("product_id") String product_id,
                           @Field("size") String size);

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
    @GET("transaction/history")
    Call<GetTransaction> getTransaction(@Query("user_id") String user_id, @Header("Authorization") String auth,@Query("type") String type);

    @GET("transaction/show")
    Call<ShowOrder> showOrder(@Query("code") String code, @Header("Authorization") String auth);

    @GET("ongkir/province")
    Call<GetProvince> getProvince();
    @GET("ongkir/city")
    Call<GetCity> getCity(@Query("province_id") String province_id);

    @FormUrlEncoded
    @POST("ongkir/cost")
    Call<OngkirCost> cekOngkir(@Header("Authorization") String auth,
                               @Field("origin") String origin,
                               @Field("destination") String destination,
                               @Field("weight") String weight,
                               @Field("courier") String courier);
    @Multipart
    @POST("transaction/add")
    Call<AddCheckout> checkout(@Header("Authorization") String auth,
                               @Query("name") String name,
                               @Query("phone") String phone,
                               @Query("description") String description,
                               @Query("province") String province,
                               @Query("city") String city,
                               @Query("address") String address,
                               @Query("courier") String courier,
                               @Query("cost") String cost,
                               @Part MultipartBody.Part image,
                               @Query("user_id") String user_id,
                               @Query("pengiriman") String pengiriman);
    @GET("payment")
    Call<GetPayment> getPayment(@Header("Authorization") String auth,
                                @Query("code") String code);
    @Multipart
    @POST("transaction/payment")
    Call<AddPayment> addPayment(@Header("Authorization") String auth,
                                @Query("code_transaction") String code_transaction,
                                @Part MultipartBody.Part image,
                                @Query("user_id") String user_id);
    @FormUrlEncoded
    @POST("transaction/update-status")
    Call<UpdateStatus> updateStatus(@Header("Authorization") String auth,
                                  @Field("code") String code,
                                    @Field("status") String status,
                                  @Field("user_id") String user_id);
}