package com.example.familycollection.activitymenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familycollection.R;
import com.example.familycollection.activity.CartActivity;
import com.example.familycollection.adapter.AdapterBayar;
import com.example.familycollection.adapter.AdapterKategori;
import com.example.familycollection.adapter.AdapterPesan;
import com.example.familycollection.adapter.AdapterStatus;
import com.example.familycollection.models.Kategori;
import com.example.familycollection.models.Pesan;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;

    RecyclerView recyclerViewStatus;

    RelativeLayout btnCart;
    LinearLayoutManager layoutManager;
    List<Pesan> userList;
    AdapterStatus adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        recyclerViewStatus = (RecyclerView) findViewById(R.id.rv_status);
        btnCart = (RelativeLayout) findViewById(R.id.btn_Keranjang);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(Test1);
            }
        });
        initPesan();

        layoutManager=new LinearLayoutManager(OrderActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        adapter1= new AdapterStatus(userList);
        recyclerViewStatus.setLayoutManager(layoutManager);
        recyclerViewStatus.setAdapter(adapter1);


        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.navigation_status);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.navigation_home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_note:
                        startActivity(new Intent(getApplicationContext(), KategoriActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_status:
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
    private void initPesan() {
        userList = new ArrayList<>();
        userList.add(new Pesan("Jaket Reglan","4 Juni 2023","96000", "1", "Diterima"));
    }
//    }
}
