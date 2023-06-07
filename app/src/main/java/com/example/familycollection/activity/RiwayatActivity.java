package com.example.familycollection.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.familycollection.R;
import com.example.familycollection.activitymenu.OrderActivity;
import com.example.familycollection.adapter.AdapterStatus;
import com.example.familycollection.models.Pesan;
import com.example.familycollection.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RiwayatActivity extends AppCompatActivity {

    RecyclerView recyclerViewRiwayat;
    LinearLayoutManager layoutManager;
    List<Transaction> transactionList;
    AdapterStatus adapter1;

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
        initRiwayat();

        layoutManager=new LinearLayoutManager(RiwayatActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        adapter1= new AdapterStatus(transactionList);
        recyclerViewRiwayat.setLayoutManager(layoutManager);
        recyclerViewRiwayat.setAdapter(adapter1);
    }
    private void initRiwayat() {

    }
}