package com.example.familycollection.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.familycollection.R;
import com.example.familycollection.adapter.AdapterProdukTransaksi;
import com.example.familycollection.adapter.AdapterTransfer;
import com.example.familycollection.models.ProdukTransaksi;
import com.example.familycollection.models.Transfer;

import java.util.ArrayList;
import java.util.List;

public class TransferActivity extends AppCompatActivity {
    RecyclerView recyclerViewTransfer;
    Button btnBukti, btnKonfirmasi;
    LinearLayoutManager layoutManager;
    List<Transfer> userList;
    AdapterTransfer adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("Konfirmasi Pembayaran");
        }

        btnBukti = (Button) findViewById(R.id.btn_bukti);
        btnKonfirmasi = (Button) findViewById(R.id.btn_konfirmasi);
        initData();

        recyclerViewTransfer=(RecyclerView) findViewById(R.id.rv_transfer);
        layoutManager=new LinearLayoutManager(TransferActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        adapter= new AdapterTransfer(userList);
        recyclerViewTransfer.setLayoutManager(layoutManager);
        recyclerViewTransfer.setAdapter(adapter);
    }
    private void initData() {
        userList = new ArrayList<>();
        userList.add(new Transfer(R.drawable.top_background1, "4 Juni 2023", "100.000"));
        userList.add(new Transfer(R.drawable.top_background1, "4 Juni 2023", "50.000"));
    }
}