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

import com.example.familycollection.activitymenu.MainActivity;

public class LoginActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    LinearLayout layout;
    ImageView imageView;
    TextView textView, textRegister;
    EditText editUser, editPass;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        constraintLayout = (ConstraintLayout) findViewById(R.id.cl_login);
        imageView = (ImageView) findViewById(R.id.imageLogin);
        layout = (LinearLayout) findViewById(R.id.ll);
        textView = (TextView) findViewById(R.id.tv_login);
        textRegister = (TextView) findViewById(R.id.tv_daftar);
        editUser = (EditText) findViewById(R.id.inp_username);
        editPass = (EditText) findViewById(R.id.inp_password);
        btnLogin = (Button) findViewById(R.id.btn_login);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Test1);
            }
        });
        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Test1 = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(Test1);
            }
        });
    }
}