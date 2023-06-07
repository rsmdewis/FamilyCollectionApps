package com.example.familycollection.activity;

import androidx.appcompat.app.ActionBar;
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
import com.example.familycollection.activitymenu.OrderActivity;
import com.example.familycollection.adapter.AdapterStatus;
import com.example.familycollection.models.GetTransaction;
import com.example.familycollection.models.Pesan;
import com.example.familycollection.models.Transaction;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatActivity extends AppCompatActivity {

    RecyclerView recyclerViewRiwayat;
    LinearLayoutManager layoutManager;
    List<Transaction> transactionList;
    AdapterStatus adapter1;
    ApiInterface mApiInterface;
    SharedPreferences sharedPreferences;

    String id,token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("Riwayat Pesanan");
        }
        recyclerViewRiwayat = (RecyclerView) findViewById(R.id.rv_riwayat);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sharedPreferences = getApplicationContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN", "fail");
        id = sharedPreferences.getString("USER_ID", "fail");
        initRiwayat();

        layoutManager=new LinearLayoutManager(RiwayatActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        adapter1= new AdapterStatus(transactionList);
        recyclerViewRiwayat.setLayoutManager(layoutManager);
    }
    private void initRiwayat() {
        Call<GetTransaction> transactionCall= mApiInterface.getTransaction(id,"Bearer "+token,"1");
        transactionCall.enqueue(new Callback<GetTransaction>() {
            @Override
            public void onResponse(Call<GetTransaction> call, Response<GetTransaction> response) {
                transactionList=response.body().getTransactionList();
                adapter1= new AdapterStatus(transactionList);
                recyclerViewRiwayat.setAdapter(adapter1);

            }

            @Override
            public void onFailure(Call<GetTransaction> call, Throwable t) {

            }
        });
    }
}