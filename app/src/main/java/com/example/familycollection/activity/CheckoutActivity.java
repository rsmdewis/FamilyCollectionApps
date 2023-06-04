package com.example.familycollection.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.familycollection.R;
import com.example.familycollection.activitymenu.OrderActivity;
import com.example.familycollection.adapter.AdapterProdukTransaksi;
import com.example.familycollection.models.ProdukTransaksi;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {
    TextView textTotalBelanja, textOngkir, textTotal;
    RecyclerView recyclerViewProduk;
    LinearLayout layoutFooter;
    Button btnpesan, tambahFoto, cekOngkir;
    EditText editKeterangan, editPenerima, editTelepon, editAlamat;
    Spinner spinnerJasa, spinnerProvinsi, spinnerKota, spinnerKurir;
    RelativeLayout layoutProvinsi, layoutKota;
    LinearLayoutManager layoutManager;
    List<ProdukTransaksi> userList;
    AdapterProdukTransaksi adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        textTotalBelanja = (TextView) findViewById(R.id.tv_totalBelanja);
        textOngkir = (TextView) findViewById(R.id.tv_ongkir);
        textTotal = (TextView) findViewById(R.id.tv_total);
        layoutFooter = (LinearLayout) findViewById(R.id.div_footer);
        btnpesan = (Button) findViewById(R.id.btn_pesan);
        tambahFoto = (Button) findViewById(R.id.foto_tambahan);
        cekOngkir = (Button) findViewById(R.id.cek_ongkir);
        layoutProvinsi = (RelativeLayout) findViewById(R.id.div_provinsi);
        layoutKota = (RelativeLayout) findViewById(R.id.div_kota);
        editKeterangan = (EditText) findViewById(R.id.edt_keterangan);
        editPenerima = (EditText) findViewById(R.id.edt_penerima);
        editTelepon = (EditText) findViewById(R.id.edt_telp);
        editAlamat = (EditText) findViewById(R.id.edt_alamat);
        spinnerJasa = (Spinner) findViewById(R.id.spinner_jasa);
        spinnerKurir = (Spinner) findViewById(R.id.spinner_kurir);
        spinnerProvinsi = (Spinner) findViewById(R.id.spn_provinsi);
        spinnerKota = (Spinner) findViewById(R.id.spn_kota);

        btnpesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), OrderActivity.class);
                startActivity(Test1);
            }
        });

        initData();

        recyclerViewProduk=(RecyclerView) findViewById(R.id.rv_produk);
        layoutManager=new LinearLayoutManager(CheckoutActivity.this);
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