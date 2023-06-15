package com.example.familycollection.activitymenu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.familycollection.activity.AboutActivity;
import com.example.familycollection.activity.CartActivity;
import com.example.familycollection.activity.EditProfileActivity;
import com.example.familycollection.activity.HelpActivity;
import com.example.familycollection.activity.ListAlamatActivity;
import com.example.familycollection.LoginActivity;
import com.example.familycollection.R;
import com.example.familycollection.activity.RiwayatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    LinearLayout layout1, layout3;
    RelativeLayout rlEditProfile, rlTentang, rlHelp, btnCart, rlRiwayat;
    Button btnLogout;
    TextView textNama, textEmail, textLogout;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        sharedPreferences = getApplicationContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        String nama = sharedPreferences.getString("NAMA", "-");
        String email = sharedPreferences.getString("EMAIL", "-");

        textNama = (TextView) findViewById(R.id.tv_nama);
        textEmail = (TextView) findViewById(R.id.tv_email);
        btnLogout = (Button) findViewById(R.id.btn_logout);
        layout1 = (LinearLayout) findViewById(R.id.linearlayout);
        layout3 = (LinearLayout) findViewById(R.id.linearlayout3);
        rlEditProfile = (RelativeLayout) findViewById(R.id.btn_editProfile);
        rlRiwayat = (RelativeLayout) findViewById(R.id.btn_riwayat);
        rlTentang = (RelativeLayout) findViewById(R.id.btn_tentang);
        rlHelp = (RelativeLayout) findViewById(R.id.btn_help);
        btnCart = (RelativeLayout) findViewById(R.id.btn_Keranjang);



        textNama.setText(nama);
        textEmail.setText(email);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(Test1);
            }
        });

        rlEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), EditProfileActivity.class);
                startActivity(Test1);
            }
        });
        rlRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), RiwayatActivity.class);
                startActivity(Test1);
            }
        });
        rlTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(Test1);
            }
        });
        rlHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(Test1);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.navigation_profile);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.navigation_home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_note:
                        startActivity(new Intent(getApplicationContext(), KategoriActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_status:
                        startActivity(new Intent(getApplicationContext(), OrderActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_profile:
                        return true;
                }
                return false;
            }
        });
    }
    private void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title dialog
        alertDialogBuilder.setTitle("Apakah Anda Ingin Logout?");

        // set pesan dari dialog
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        Intent Test1 = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(Test1);
                    }
                })
                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }
}
