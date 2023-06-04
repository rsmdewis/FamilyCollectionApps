package com.example.familycollection.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.familycollection.R;
import com.example.familycollection.activitymenu.MainActivity;
import com.example.familycollection.activitymenu.OrderActivity;
import com.example.familycollection.adapter.AdapterProdukTransaksi;
import com.example.familycollection.models.ProdukTransaksi;

import java.util.ArrayList;
import java.util.List;

public class TransaksiActivity extends AppCompatActivity {

    TextView textStatus, textTanggal, textKeterangan, textPegiriman, textKurir, textPenerima,
            textTelepon, textAlamat, textTotalBelanja, textOngkir, textTotal, textLunas, tvBank, tvNobank;
    RecyclerView recyclerViewProduk;
    ImageView fotoTambahan;
    LinearLayout layoutFooter;
    Button btnPesanan, btnBukti, btnKonfirmasi;
    LinearLayoutManager layoutManager;
    List<ProdukTransaksi> userList;
    AdapterProdukTransaksi adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        textStatus = (TextView) findViewById(R.id.tv_status);
        textTanggal = (TextView) findViewById(R.id.tv_tgl);
        textKeterangan = (TextView) findViewById(R.id.tv_keterangan);
        textPegiriman = (TextView) findViewById(R.id.tv_kirim);
        textKurir = (TextView) findViewById(R.id.tv_kurir);
        textPenerima = (TextView) findViewById(R.id.tv_penerima);
        textTelepon = (TextView) findViewById(R.id.tv_telp);
        textAlamat = (TextView) findViewById(R.id.tv_alamat);
        textTotalBelanja = (TextView) findViewById(R.id.tv_totalBelanja);
        textOngkir = (TextView) findViewById(R.id.tv_ongkir);
        textTotal = (TextView) findViewById(R.id.tv_total);
        textLunas = (TextView) findViewById(R.id.tv_lunas);
        tvBank = (TextView) findViewById(R.id.tv_bank);
        tvNobank = (TextView) findViewById(R.id.tv_nobank);
        fotoTambahan = (ImageView) findViewById(R.id.foto_tambahan);
        layoutFooter = (LinearLayout) findViewById(R.id.div_footer);
        btnBukti = (Button) findViewById(R.id.btn_bukti);
        btnKonfirmasi = (Button) findViewById(R.id.btn_konfirmasi);
        btnPesanan = (Button) findViewById(R.id.btn_pesanan_diterima);

        btnPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), OrderActivity.class);
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