package com.example.familycollection.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.familycollection.R;
import com.example.familycollection.activitymenu.ProfileActivity;

public class EditProfileActivity extends AppCompatActivity {

    ConstraintLayout clRegister;
    ImageView imgLogo;
    TextView textEdit;
    EditText etUsername, etNama, etEmail, etnohp, etPassword;
    Button buttonSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etUsername = (EditText) findViewById(R.id.inp_username);
        etNama = (EditText) findViewById(R.id.inp_nama);
        etEmail = (EditText) findViewById(R.id.inp_email);
        etnohp = (EditText) findViewById(R.id.inp_nohp);
        etPassword = (EditText) findViewById(R.id.inp_password);
        buttonSimpan = (Button) findViewById(R.id.btn_simpan);
        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(Test1);
            }
        });

    }
}