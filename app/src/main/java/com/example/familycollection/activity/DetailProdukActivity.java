package com.example.familycollection.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.familycollection.R;

public class DetailProdukActivity extends AppCompatActivity {

    ImageView imgProduk;
    TextView textNama, textHarga, textBahan, textBerat, textUkuran, textEmail, textDeskripsi;
    RecyclerView recyclerViewProduk;
    LinearLayout layoutFooter;
    RelativeLayout btnKeranjang, btnBeli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        imgProduk = (ImageView) findViewById(R.id.image_produk);
        textNama = (TextView) findViewById(R.id.tv_nama);
        textHarga = (TextView) findViewById(R.id.tv_harga);
        textBahan = (TextView) findViewById(R.id.tv_bahan);
        textBerat = (TextView) findViewById(R.id.tv_berat);
        textUkuran = (TextView) findViewById(R.id.tv_ukuran);
        textEmail = (TextView) findViewById(R.id.tv_emailfc);
        textDeskripsi = (TextView) findViewById(R.id.tv_deskripsi);
        recyclerViewProduk = (RecyclerView) findViewById(R.id.rv_produk);
        layoutFooter = (LinearLayout) findViewById(R.id.div_footer);
        btnKeranjang = (RelativeLayout) findViewById(R.id.btn_keranjang);
        btnBeli = (RelativeLayout) findViewById(R.id.btn_beli);
        btnKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(Test1);
            }
        });
    }
}