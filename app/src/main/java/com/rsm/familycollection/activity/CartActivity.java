package com.rsm.familycollection.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rsm.familycollection.R;
import com.rsm.familycollection.RestApi.ApiClient;
import com.rsm.familycollection.RestApi.ApiInterface;
import com.rsm.familycollection.adapter.AdapterCart;
import com.rsm.familycollection.models.Cart;
import com.rsm.familycollection.models.GetCart;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements AdapterCart.IMethodCaller {
    BottomNavigationView bottomNavigation;

    CheckBox checkBoxAll;
    ImageView imageDelete;
    RecyclerView recyclerViewCart;
    RelativeLayout relativeLayoutFooter;
    TextView textViewTotal, textViewBayar;
    SharedPreferences sharedPreferences;
    ApiInterface mApiInterface;
    String token,id;
    LinearLayoutManager layoutManager;
    List<Cart> cartList;

    AdapterCart adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Keranjang");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        checkBoxAll = (CheckBox) findViewById(R.id.cb_all);
//        imageDelete = (ImageView) findViewById(R.id.btn_delete);
        recyclerViewCart = (RecyclerView) findViewById(R.id.rv_produk);
        relativeLayoutFooter = (RelativeLayout) findViewById(R.id.div_footer);
        layoutManager=new LinearLayoutManager(CartActivity.this);
        textViewTotal = (TextView) findViewById(R.id.tv_total);
        textViewBayar = (TextView) findViewById(R.id.btn_bayar);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sharedPreferences = getApplicationContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN", "fail");
        id = sharedPreferences.getString("USER_ID", "fail");

        loadData();

        textViewBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), CheckoutActivity.class);
                startActivity(Test1);
            }
        });


    }

    public void loadData(){

        Call<GetCart> getCartCall=mApiInterface.getCart(id,"Bearer "+token);
        getCartCall.enqueue(new Callback<GetCart>() {
            @Override
            public void onResponse(Call<GetCart> call, Response<GetCart> response) {
                cartList=response.body().getCartList();
                adapter= new AdapterCart(cartList,getApplicationContext(),CartActivity.this,textViewTotal);
                recyclerViewCart.setLayoutManager(layoutManager);
                recyclerViewCart.setAdapter(adapter);
//                textViewTotal.setText(response.body().getGrand_total_total());
            }

            @Override
            public void onFailure(Call<GetCart> call, Throwable t) {
                Log.d("ERR",""+t);
            }
        });
    }
}
