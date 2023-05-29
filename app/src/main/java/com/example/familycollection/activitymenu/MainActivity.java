package com.example.familycollection.activitymenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.familycollection.activity.CartActivity;
import com.example.familycollection.activity.DetailProdukActivity;
import com.example.familycollection.R;
import com.example.familycollection.activity.ProdukActivity;
import com.example.familycollection.activity.ResetActivity;
import com.example.familycollection.adapter.AdapterProduk;
import com.example.familycollection.models.Produk;
import com.example.familycollection.models.ProdukTransaksi;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;

    EditText edtCari;
    LinearLayout layout1;
    TextView textLastview, textSeeall;
    RecyclerView recyclerViewHome;
    RelativeLayout btnCart;
    LinearLayoutManager layoutManager;
    List<Produk> userList;
    AdapterProduk adapter;

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
        adapter= new AdapterProduk(userList);
        recyclerViewHome.setLayoutManager(layoutManager);
        recyclerViewHome.setAdapter(adapter);


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
        userList = new ArrayList<>();
        userList.add(new Produk(R.drawable.top_background1,"Seragam SMP","100.000"));
        userList.add(new Produk(R.drawable.top_background1,"Seragam SD","75.000"));
    }

}