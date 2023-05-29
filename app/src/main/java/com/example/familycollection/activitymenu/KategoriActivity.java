package com.example.familycollection.activitymenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.familycollection.R;
import com.example.familycollection.activity.CartActivity;
import com.example.familycollection.adapter.AdapterKategori;
import com.example.familycollection.adapter.AdapterProduk;
import com.example.familycollection.models.Kategori;
import com.example.familycollection.models.Produk;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class KategoriActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    RecyclerView recyclerViewKategori;

    RelativeLayout btnCart;
    LinearLayoutManager layoutManager;
    List<Kategori> userList;
    AdapterKategori adapter;

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

        initData();
        recyclerViewKategori = (RecyclerView) findViewById(R.id.recyclerview_kategori);
        layoutManager=new LinearLayoutManager(KategoriActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        adapter= new AdapterKategori(userList);
        recyclerViewKategori.setLayoutManager(layoutManager);
        recyclerViewKategori.setAdapter(adapter);


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
        userList = new ArrayList<>();
        userList.add(new Kategori(R.drawable.top_background1,"Kaos Polos"));
        userList.add(new Kategori(R.drawable.top_background1,"Seragam"));
        userList.add(new Kategori(R.drawable.top_background1,"PDH"));
        userList.add(new Kategori(R.drawable.top_background1,"Jaket"));
        userList.add(new Kategori(R.drawable.top_background1,"Hem"));
        userList.add(new Kategori(R.drawable.top_background1,"atribut"));
    }
}