package com.example.familycollection.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.familycollection.LoginActivity;
import com.example.familycollection.R;
import com.example.familycollection.RestApi.ApiClient;
import com.example.familycollection.RestApi.ApiInterface;
import com.example.familycollection.activitymenu.MainActivity;
import com.example.familycollection.activitymenu.OrderActivity;
import com.example.familycollection.adapter.AdapterProdukTransaksi;
import com.example.familycollection.models.Order;
import com.example.familycollection.models.ProdukTransaksi;
import com.example.familycollection.models.ShowOrder;
import com.example.familycollection.models.UpdateStatus;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiActivity extends AppCompatActivity {

    TextView textStatus, textTanggal, textKeterangan, textPegiriman, textKurir, textPenerima,
            textTelepon, textAlamat, textTotalBelanja, textOngkir, textTotal, textLunas, tvBank, tvNobank;
    RecyclerView recyclerViewProduk;
    ImageView fotoTambahan;
    LinearLayout layoutFooter;
    Button btnPesanan, btnBukti, btnKonfirmasi;
    LinearLayoutManager layoutManager;
    List<Order> orderList;
    AdapterProdukTransaksi adapter;
    ApiInterface mApiInterface;
    SharedPreferences sharedPreferences;
    String token,code,user_id;

    Intent mIntent;

    ProgressDialog progressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("Detail Pesanan");
        }

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
        btnKonfirmasi = (Button) findViewById(R.id.btn_konfirmasi);
        btnPesanan = (Button) findViewById(R.id.btn_pesanan_diterima);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sharedPreferences = getApplicationContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN", "fail");
        user_id = sharedPreferences.getString("USER_ID", "fail");
        mIntent=getIntent();
        code=mIntent.getStringExtra("code");

        btnPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               confirm();
            }
        });
        btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), TransferActivity.class);
                Test1.putExtra("code",code);
                startActivity(Test1);
            }
        });

        recyclerViewProduk=(RecyclerView) findViewById(R.id.rv_produk);
        layoutManager=new LinearLayoutManager(TransaksiActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        progressDialog = new ProgressDialog(TransaksiActivity.this);
        progressDialog.setMessage("Tunggu sebentar... ");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        initData();

        recyclerViewProduk.setLayoutManager(layoutManager);


    }

    private void initData() {
        progressDialog.show();
        Call<ShowOrder> showOrderCall= mApiInterface.showOrder(code,"Bearer "+token);
        showOrderCall.enqueue(new Callback<ShowOrder>() {
            @Override
            public void onResponse(Call<ShowOrder> call, Response<ShowOrder> response) {
                progressDialog.dismiss();
                orderList=response.body().getOrderList();
                adapter= new AdapterProdukTransaksi(orderList);
                recyclerViewProduk.setAdapter(adapter);
                final String urlGambarBerita = "http://192.168.1.5:8000/storage/" + response.body().getOrderDetail().getImage();
                Picasso.get().load(urlGambarBerita).into(fotoTambahan);
                textKeterangan.setText(response.body().getOrderDetail().getDescription());
                textKurir.setText(response.body().getOrderDetail().getCourier());
                textPenerima.setText(response.body().getOrderDetail().getName());
                textTelepon.setText(response.body().getOrderDetail().getPhone());
                textAlamat.setText(response.body().getOrderDetail().getAddress());
                textTotalBelanja.setText("Rp. "+response.body().getOrderDetail().getTotal());
                textOngkir.setText("Rp."+response.body().getOrderDetail().getCost());
                textTotal.setText("Rp." +response.body().getOrderDetail().getTotal_bayar());
                if(response.body().getOrderDetail().getPengiriman().equals("1")){
                    textPegiriman.setText("Kirim Pesnanan");
                }else{
                    textPegiriman.setText("Ambil Sendiri");
                    textOngkir.setText("Rp. 0");
                }

                textLunas.setText("Rp. "+response.body().getOrderDetail().getTotal_pelunasan());

            }

            @Override
            public void onFailure(Call<ShowOrder> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }

    public void confirm(){
        Call<UpdateStatus> updateStatusCall= mApiInterface.updateStatus("Bearer "+token,code,"3",user_id);
        updateStatusCall.enqueue(new Callback<UpdateStatus>() {
            @Override
            public void onResponse(Call<UpdateStatus> call, Response<UpdateStatus> response) {
                Toast.makeText(getApplicationContext(),"Pesanan telah diterima",Toast.LENGTH_LONG).show();
                Intent Test1 = new Intent(getApplicationContext(), RiwayatActivity.class);
                startActivity(Test1);
            }

            @Override
            public void onFailure(Call<UpdateStatus> call, Throwable t) {

            }
        });
    }
}