package com.rsm.familycollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MasukActivity extends AppCompatActivity {

    Button buttonMasuk, buttonDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);

        buttonMasuk = (Button) findViewById(R.id.btn_Login);
        buttonDaftar = (Button) findViewById(R.id.btn_register);
        buttonMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(Test1);
            }
        });
        buttonDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(Test1);
            }
        });
    }
}