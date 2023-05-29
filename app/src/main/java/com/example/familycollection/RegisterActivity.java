package com.example.familycollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    TextView tvRegister, tvLogin;
    EditText editUser, editPass, editNama, editEmail;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        tvLogin = (TextView) findViewById(R.id.tv_login);
        editUser = (EditText) findViewById(R.id.inp_username);
        editPass = (EditText) findViewById(R.id.inp_password);
        editNama = (EditText) findViewById(R.id.inp_nama);
        editEmail = (EditText) findViewById(R.id.inp_email);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(Test1);
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(Test1);
            }
        });

    }
}