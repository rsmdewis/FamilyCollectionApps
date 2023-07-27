package com.rsm.familycollection.activitymenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.rsm.familycollection.R;
import com.rsm.familycollection.RestApi.ApiClient;
import com.rsm.familycollection.RestApi.ApiInterface;
import com.rsm.familycollection.activity.CartActivity;
import com.rsm.familycollection.adapter.AdapterKategori;
import com.rsm.familycollection.models.GetProductCategory;
import com.rsm.familycollection.models.Kategori;
import com.rsm.familycollection.models.ProductCategory;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KategoriActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    RecyclerView recyclerViewKategori;
    ApiInterface mApiInterface;

    RelativeLayout btnCart;
    LinearLayoutManager layoutManager;
    List<Kategori> userList;
    List<ProductCategory> productCategories;
    AdapterKategori adapter;
    SharedPreferences sharedPreferences;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

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

        initData();
        recyclerViewKategori = (RecyclerView) findViewById(R.id.recyclerview_kategori);
        layoutManager=new LinearLayoutManager(KategoriActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewKategori.setLayoutManager(layoutManager);


        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.navigation_note);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.navigation_home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_note:
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
        Call<GetProductCategory> getProductCategoryCall = mApiInterface.getCategory("Bearer "+token);
        getProductCategoryCall.enqueue(new Callback<GetProductCategory>() {
            @Override
            public void onResponse(Call<GetProductCategory> call, Response<GetProductCategory> response) {
                productCategories=response.body().getListProductCategory();

                Log.d("RES",productCategories.get(0).getNama());
                adapter= new AdapterKategori(productCategories);
                recyclerViewKategori.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<GetProductCategory> call, Throwable t) {

            }
        });
    }
}