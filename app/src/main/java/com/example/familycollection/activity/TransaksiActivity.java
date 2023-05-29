package com.example.familycollection.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.familycollection.R;
import com.example.familycollection.activitymenu.MainActivity;
import com.example.familycollection.adapter.AdapterProdukTransaksi;
import com.example.familycollection.models.ProdukTransaksi;

import java.util.ArrayList;
import java.util.List;

public class TransaksiActivity extends AppCompatActivity {

    TextView textStatus, textTanggal, textPenerima, textAlamat, textTotalHarga, textOngkir, textKode, textTotal;
    RecyclerView recyclerViewProduk;
    LinearLayout layoutFooter;
    Button btnBatal;
    LinearLayoutManager layoutManager;
    List<ProdukTransaksi> userList;
    AdapterProdukTransaksi adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        textStatus = (TextView) findViewById(R.id.tv_status);
        textTanggal = (TextView) findViewById(R.id.tv_tgl);
        textPenerima = (TextView) findViewById(R.id.tv_penerima);
        textAlamat = (TextView) findViewById(R.id.tv_alamat);
        textTotalHarga = (TextView) findViewById(R.id.tv_totalBelanja);
        textOngkir = (TextView) findViewById(R.id.tv_ongkir);
        textKode = (TextView) findViewById(R.id.tv_kodeUnik);
        textTotal = (TextView) findViewById(R.id.tv_total);
        layoutFooter = (LinearLayout) findViewById(R.id.div_footer);
        btnBatal = (Button) findViewById(R.id.btn_batal);

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Test1);
            }
        });

        initData();

        recyclerViewProduk=(RecyclerView) findViewById(R.id.rv_produk);
        layoutManager=new LinearLayoutManager(TransaksiActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        adapter= new AdapterProdukTransaksi(userList);
        recyclerViewProduk.setLayoutManager(layoutManager);
        recyclerViewProduk.setAdapter(adapter);


    }

    private void initData() {
        userList = new ArrayList<>();
        userList.add(new ProdukTransaksi(R.drawable.top_background1, "Seragam Olahraga", "0,25kg", "65.000", "2", "130000"));
        userList.add(new ProdukTransaksi(R.drawable.top_background1, "Seragam Olahraga", "0,25kg", "65.000", "1", "65000"));
    }
}