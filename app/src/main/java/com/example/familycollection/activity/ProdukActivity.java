package com.example.familycollection.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.familycollection.R;
import com.example.familycollection.RestApi.ApiClient;
import com.example.familycollection.RestApi.ApiInterface;
import com.example.familycollection.activitymenu.MainActivity;
import com.example.familycollection.adapter.AdapterKategori;
import com.example.familycollection.adapter.AdapterProduk;
import com.example.familycollection.models.Product;
import com.example.familycollection.models.Produk;

import java.util.ArrayList;
import java.util.List;

public class ProdukActivity extends AppCompatActivity {
    RecyclerView recyclerViewProduk;
    LinearLayoutManager layoutManager;
    List<Produk> userList;
    AdapterProduk adapter;
    SharedPreferences sharedPreferences;
    String token;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk);
        recyclerViewProduk = (RecyclerView) findViewById(R.id.recyclerview_produk);
        initData();
        layoutManager=new LinearLayoutManager(ProdukActivity.this);
//        adapter= new AdapterProduk(userList);
        recyclerViewProduk.setLayoutManager(layoutManager);
        recyclerViewProduk.setAdapter(adapter);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sharedPreferences = getApplicationContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN", "fail");
    }
    private void initData() {
        userList = new ArrayList<>();
        userList.add(new Produk(R.drawable.top_background1,"Seragam SMP","100.000"));
        userList.add(new Produk(R.drawable.top_background1,"Seragam SD","75.000"));
    }
}