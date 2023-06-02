package com.example.familycollection.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familycollection.R;
import com.example.familycollection.RestApi.ApiClient;
import com.example.familycollection.RestApi.ApiInterface;
import com.example.familycollection.activity.TransaksiActivity;
import com.example.familycollection.adapter.AdapterCart;
import com.example.familycollection.adapter.AdapterProduk;
import com.example.familycollection.models.Cart;
import com.example.familycollection.models.GetCart;
import com.example.familycollection.models.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

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
                Intent Test1 = new Intent(getApplicationContext(), TransaksiActivity.class);
                startActivity(Test1);
            }
        });


    }

    public void loadData(){
        Log.d("HERE","DISINI");
        Call<GetCart> getCartCall=mApiInterface.getCart(id,"Bearer "+token);
        getCartCall.enqueue(new Callback<GetCart>() {
            @Override
            public void onResponse(Call<GetCart> call, Response<GetCart> response) {
                cartList=response.body().getCartList();
                adapter= new AdapterCart(cartList,getApplicationContext(),CartActivity.this);
                recyclerViewCart.setLayoutManager(layoutManager);
                recyclerViewCart.setAdapter(adapter);

                textViewTotal.setText(response.body().getTotal());
            }

            @Override
            public void onFailure(Call<GetCart> call, Throwable t) {
                Log.d("ERR",""+t);
            }
        });
    }
}
