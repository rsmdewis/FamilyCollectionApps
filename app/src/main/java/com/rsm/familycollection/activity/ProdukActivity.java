package com.rsm.familycollection.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.rsm.familycollection.R;
import com.rsm.familycollection.RestApi.ApiClient;
import com.rsm.familycollection.RestApi.ApiInterface;
import com.rsm.familycollection.adapter.AdapterProduk;
import com.rsm.familycollection.models.GetProduct;
import com.rsm.familycollection.models.Product;

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
    String token,id,nama="";
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
        nama=mIntent.getStringExtra("nama");
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Produk");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initData(nama);
    }
    private void initData(String nama) {
        Call<GetProduct> getProductCall = mApiInterface.getProduct(id,"Bearer "+token,nama);
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