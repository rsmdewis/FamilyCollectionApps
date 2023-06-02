package com.example.familycollection.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.familycollection.R;
import com.example.familycollection.RestApi.ApiClient;
import com.example.familycollection.RestApi.ApiInterface;
import com.example.familycollection.activitymenu.MainActivity;
import com.example.familycollection.adapter.AdapterKategori;
import com.example.familycollection.adapter.AdapterProduk;
import com.example.familycollection.models.GetProduct;
import com.example.familycollection.models.Product;
import com.example.familycollection.models.Produk;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProdukActivity extends AppCompatActivity {
    RecyclerView recyclerViewProduk;
    LinearLayoutManager layoutManager;
    List<Product> productList;
    AdapterProduk adapter;
    SharedPreferences sharedPreferences;
    String token,id;
    ApiInterface mApiInterface;
    Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk);
        recyclerViewProduk = (RecyclerView) findViewById(R.id.recyclerview_produk);
        layoutManager=new GridLayoutManager(ProdukActivity.this,2);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sharedPreferences = getApplicationContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN", "fail");
        mIntent=getIntent();
        id=mIntent.getStringExtra("id");

        initData();
    }
    private void initData() {
        Call<GetProduct> getProductCall = mApiInterface.getProduct(id,"Bearer "+token);
        getProductCall.enqueue(new Callback<GetProduct>() {
            @Override
            public void onResponse(Call<GetProduct> call, Response<GetProduct> response) {
                productList=response.body().getListProduct();
                adapter= new AdapterProduk(productList);
                recyclerViewProduk.setLayoutManager(layoutManager);
                recyclerViewProduk.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GetProduct> call, Throwable t) {

            }
        });
    }
}