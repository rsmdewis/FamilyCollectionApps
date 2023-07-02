package com.example.familycollection.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.familycollection.R;

public class TambahAlamatActivity extends AppCompatActivity {

    EditText editNama, editNohp, editAlamat, editType, editKodepos;
    RelativeLayout rlProvinsi, rlKota, rlKecamatan;
    Spinner spinnerProvinsi, spinnerKota, spinnerKecamatan;
    ProgressBar progressBar;
    Button btnsimpan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_alamat);

        editNama = (EditText) findViewById(R.id.edt_penerima);
        editNohp = (EditText) findViewById(R.id.edt_telp);
        editAlamat = (EditText) findViewById(R.id.edt_alamat);
        rlProvinsi = (RelativeLayout) findViewById(R.id.div_provinsi);
        rlKota = (RelativeLayout) findViewById(R.id.div_kota);
        spinnerProvinsi = (Spinner) findViewById(R.id.spn_provinsi);
        spinnerKota = (Spinner) findViewById(R.id.spn_kota);
        btnsimpan = (Button) findViewById(R.id.simpan);

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), ListAlamatActivity.class);
                startActivity(Test1);
            }
        });
    }
}