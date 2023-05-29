package com.example.familycollection.activitymenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.familycollection.RestApi.ApiClient;
import com.example.familycollection.RestApi.ApiInterface;
import com.example.familycollection.activity.CartActivity;
import com.example.familycollection.activity.DetailProdukActivity;
import com.example.familycollection.R;
import com.example.familycollection.activity.ProdukActivity;
import com.example.familycollection.activity.ResetActivity;
import com.example.familycollection.adapter.AdapterProduk;
import com.example.familycollection.models.GetProduct;
import com.example.familycollection.models.GetProductCategory;
import com.example.familycollection.models.Product;
import com.example.familycollection.models.Produk;
import com.example.familycollection.models.ProdukTransaksi;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;

    EditText edtCari;
    LinearLayout layout1;
    TextView textLastview, textSeeall;
    RecyclerView recyclerViewHome;
    RelativeLayout btnCart;
    LinearLayoutManager layoutManager;
    List<Product> productList;
    AdapterProduk adapter;
    SharedPreferences sharedPreferences;
    ApiInterface mApiInterface;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtCari = (EditText) findViewById(R.id.edtcari);
        layout1 = (LinearLayout) findViewById(R.id.produk1);
        textLastview = (TextView) findViewById(R.id.tv_lastview);
        textSeeall = (TextView) findViewById(R.id.btn_seeall);
        recyclerViewHome = (RecyclerView) findViewById(R.id.recyclerview_home);
        btnCart = (RelativeLayout) findViewById(R.id.btn_Keranjang);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(Test1);
            }
        });
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sharedPreferences = getApplicationContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN", "fail");
        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), DetailProdukActivity.class);
                startActivity(Test1);
            }
        });
        textSeeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), ProdukActivity.class);
                startActivity(Test1);
            }
        });

        initData();
        layoutManager=new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);



        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.navigation_home);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.navigation_home:
                        return true;
                    case R.id.navigation_note:
                        startActivity(new Intent(getApplicationContext(), KategoriActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_status:
                        startActivity(new Intent(getApplicationContext(), OrderActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }


    private void initData() {
        Call<GetProduct> getProductCall = mApiInterface.getProduct("1","Bearer "+token);
        getProductCall.enqueue(new Callback<GetProduct>() {
            @Override
            public void onResponse(Call<GetProduct> call, Response<GetProduct> response) {
                productList=response.body().getListProduct();
                adapter= new AdapterProduk(productList);
                recyclerViewHome.setLayoutManager(layoutManager);
                recyclerViewHome.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GetProduct> call, Throwable t) {

            }
        });

    }

}