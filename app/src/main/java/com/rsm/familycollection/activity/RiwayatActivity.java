package com.rsm.familycollection.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.rsm.familycollection.R;
import com.rsm.familycollection.RestApi.ApiClient;
import com.rsm.familycollection.RestApi.ApiInterface;
import com.rsm.familycollection.adapter.AdapterStatus;
import com.rsm.familycollection.models.GetTransaction;
import com.rsm.familycollection.models.Transaction;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Riwayat Pesanan");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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