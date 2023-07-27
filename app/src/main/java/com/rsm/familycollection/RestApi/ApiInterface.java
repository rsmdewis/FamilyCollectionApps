package com.rsm.familycollection.RestApi;


import com.rsm.familycollection.Model.GetAkun;
import com.rsm.familycollection.models.AddCart;
import com.rsm.familycollection.models.AddCheckout;
import com.rsm.familycollection.models.AddPayment;
import com.rsm.familycollection.models.DeleteCart;
import com.rsm.familycollection.models.GetCart;
import com.rsm.familycollection.models.GetCity;
import com.rsm.familycollection.models.GetPayment;
import com.rsm.familycollection.models.GetProduct;
import com.rsm.familycollection.models.GetProductCategory;
import com.rsm.familycollection.models.GetProvince;
import com.rsm.familycollection.models.GetTransaction;
import com.rsm.familycollection.models.OngkirCost;
import com.rsm.familycollection.models.Register;
import com.rsm.familycollection.models.ShowOrder;
import com.rsm.familycollection.models.UpdateStatus;
import com.rsm.familycollection.models.UpdateUser;

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
                           @Field("size") String size,
                           @Field("qty") String qty);
    @FormUrlEncoded
    @POST("auth/register")
    Call<Register> register(@Field("email") String email,
                            @Field("phone") String phone,
                            @Field("password") String password,
                            @Field("name") String name,
                            @Field("city_id") String city_id,
                            @Field("province_id") String province_id,
                            @Field("address") String address);

    @FormUrlEncoded
    @POST("auth/update")
    Call<UpdateUser> updateUser(
                            @Header("Authorization") String auth,
                            @Field("id") String id,
                            @Field("email") String email,
                            @Field("phone") String phone,
                            @Field("password") String password,
                            @Field("name") String name,
                            @Field("province_id") String province_id,
                            @Field("city_id") String city_id,
                            @Field("address") String address);
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
                               @Query("pengiriman") String pengiriman,
                               @Query("deadline") String deadline);

    @POST("transaction/add")
    Call<AddCheckout> checkoutNull(@Header("Authorization") String auth,
                               @Query("name") String name,
                               @Query("phone") String phone,
                               @Query("description") String description,
                               @Query("province") String province,
                               @Query("city") String city,
                               @Query("address") String address,
                               @Query("courier") String courier,
                               @Query("cost") String cost,
                               @Query("user_id") String user_id,
                               @Query("pengiriman") String pengiriman,
                               @Query("deadline") String deadline);
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