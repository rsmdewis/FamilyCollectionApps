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

        editNama = (EditText) findViewById(R.id.edt_nama);
        editNohp = (EditText) findViewById(R.id.edt_phone);
        editAlamat = (EditText) findViewById(R.id.edt_alamat);
        editType = (EditText) findViewById(R.id.edt_type);
        editKodepos = (EditText) findViewById(R.id.edt_kodePos);
        rlProvinsi = (RelativeLayout) findViewById(R.id.div_provinsi);
        rlKota = (RelativeLayout) findViewById(R.id.div_kota);
        rlKecamatan = (RelativeLayout) findViewById(R.id.div_kecamatan);
        spinnerProvinsi = (Spinner) findViewById(R.id.spn_provinsi);
        spinnerKota = (Spinner) findViewById(R.id.spn_kota);
        spinnerKecamatan = (Spinner) findViewById(R.id.spn_kecamatan);
        progressBar = (ProgressBar) findViewById(R.id.pb);
        btnsimpan = (Button) findViewById(R.id.btn_simpan);

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), ListAlamatActivity.class);
                startActivity(Test1);
            }
        });
    }
}