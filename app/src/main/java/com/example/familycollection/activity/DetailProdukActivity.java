package com.example.familycollection.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.familycollection.LoginActivity;
import com.example.familycollection.R;
import com.example.familycollection.RestApi.ApiClient;
import com.example.familycollection.RestApi.ApiInterface;
import com.example.familycollection.models.AddCart;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProdukActivity extends AppCompatActivity {

    ImageView imgProduk;
    TextView textNama, textHarga, textBahan, textBerat, textUkuran, textEmail, textDeskripsi;
    RecyclerView recyclerViewProduk;
    LinearLayout layoutFooter;
    RelativeLayout btnKeranjang, btnBeli;
    Intent mIntent;
    SharedPreferences sharedPreferences;
    String user_id,token;
    ApiInterface mApiInterface;
    ProgressDialog progressDialog;
    Spinner spinner_size;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("Detail Produk");
        }

        imgProduk = (ImageView) findViewById(R.id.image_produk);
        textNama = (TextView) findViewById(R.id.tv_nama);
        textHarga = (TextView) findViewById(R.id.tv_harga);
        textBerat = (TextView) findViewById(R.id.tv_berat);
        spinner_size = findViewById(R.id.spinner_size);
        textEmail = (TextView) findViewById(R.id.tv_emailfc);
        textDeskripsi = (TextView) findViewById(R.id.tv_deskripsi);
        recyclerViewProduk = (RecyclerView) findViewById(R.id.rv_produk);
        layoutFooter = (LinearLayout) findViewById(R.id.div_footer);
        btnBeli = (RelativeLayout) findViewById(R.id.btn_beli);
        sharedPreferences = getApplicationContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        user_id = sharedPreferences.getString("USER_ID", "fail");
        token = sharedPreferences.getString("TOKEN", "fail");


        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        mIntent=getIntent();
        final String urlGambarBerita = "http://192.168.1.5:8000/storage/" + mIntent.getStringExtra("gambar");
        Picasso.get().load(urlGambarBerita).into(imgProduk);
        textNama.setText(mIntent.getStringExtra("nama"));
        textHarga.setText("Rp. "+mIntent.getStringExtra("harga"));
        textDeskripsi.setText(mIntent.getStringExtra("deskripsi"));

        progressDialog = new ProgressDialog(DetailProdukActivity.this);
        progressDialog.setMessage("Tunggu sebentar... ");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);

        btnBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCart();
            }
        });
    }
    private void addCart() {
        progressDialog.show();
        Call<AddCart> addCartCall=mApiInterface.postCart("Bearer "+token,user_id,mIntent.getStringExtra("id"),""+spinner_size.getSelectedItem(),"1");
        addCartCall.enqueue(new Callback<AddCart>() {
            @Override
            public void onResponse(Call<AddCart> call, Response<AddCart> response) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                if(response.body().getSuccess().equals("true")){
                    Intent Test1 = new Intent(getApplicationContext(), CartActivity.class);
                    startActivity(Test1);
                }

            }

            @Override
            public void onFailure(Call<AddCart> call, Throwable t) {
                Log.d("ERR",""+t);
            }
        });
    }
}