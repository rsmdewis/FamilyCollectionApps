package com.example.familycollection.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.familycollection.R;

public class ListAlamatActivity extends AppCompatActivity {

    TextView textAlamat;
    LinearLayout layoutKosong;
    RecyclerView recyclerViewAlamat;
    Button btnTambahAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_alamat);

        textAlamat = (TextView) findViewById(R.id.tv_alamat);
        layoutKosong = (LinearLayout) findViewById(R.id.div_kosong);
        recyclerViewAlamat = (RecyclerView) findViewById(R.id.rv_alamat);
        btnTambahAlamat = (Button) findViewById(R.id.btn_tambahAlamat);
        btnTambahAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), TambahAlamatActivity.class);
                startActivity(Test1);
            }
        });
    }
}