package com.rsm.familycollection.activitymenu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rsm.familycollection.R;
import com.rsm.familycollection.RestApi.ApiClient;
import com.rsm.familycollection.RestApi.ApiInterface;
import com.rsm.familycollection.activity.CartActivity;
import com.rsm.familycollection.adapter.AdapterStatus;
import com.rsm.familycollection.models.GetTransaction;
import com.rsm.familycollection.models.Transaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;

    RecyclerView recyclerViewStatus;

    RelativeLayout btnCart;
    LinearLayoutManager layoutManager;
    List<Transaction> transactionList;
    AdapterStatus adapter1;

    ApiInterface mApiInterface;
    SharedPreferences sharedPreferences;

    String id,token;
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


        layoutManager=new LinearLayoutManager(OrderActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerViewStatus.setLayoutManager(layoutManager);
        recyclerViewStatus.setAdapter(adapter1);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sharedPreferences = getApplicationContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN", "fail");
        id = sharedPreferences.getString("USER_ID", "fail");

        initPesan();
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
        Call<GetTransaction> transactionCall= mApiInterface.getTransaction(id,"Bearer "+token,"0");
        transactionCall.enqueue(new Callback<GetTransaction>() {
            @Override
            public void onResponse(Call<GetTransaction> call, Response<GetTransaction> response) {
                transactionList=response.body().getTransactionList();
                adapter1= new AdapterStatus(transactionList);
                recyclerViewStatus.setAdapter(adapter1);

            }

            @Override
            public void onFailure(Call<GetTransaction> call, Throwable t) {

            }
        });
    }
//    }
}
