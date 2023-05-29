package com.example.familycollection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familycollection.R;
import com.example.familycollection.activity.TransaksiActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class CartActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;

    CheckBox checkBoxAll;
    ImageView imageDelete;
    RecyclerView recyclerViewCart;
    RelativeLayout relativeLayoutFooter;
    TextView textViewTotal, textViewBayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        checkBoxAll = (CheckBox) findViewById(R.id.cb_all);
        imageDelete = (ImageView) findViewById(R.id.btn_delete);
        recyclerViewCart = (RecyclerView) findViewById(R.id.rv_produk);
        relativeLayoutFooter = (RelativeLayout) findViewById(R.id.div_footer);
        textViewTotal = (TextView) findViewById(R.id.tv_total);
        textViewBayar = (TextView) findViewById(R.id.btn_bayar);

        textViewBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), TransaksiActivity.class);
                startActivity(Test1);
            }
        });


    }
}
