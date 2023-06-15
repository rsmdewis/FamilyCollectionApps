package com.example.familycollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.familycollection.RestApi.ApiClient;
import com.example.familycollection.RestApi.ApiInterface;
import com.example.familycollection.models.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    TextView tvRegister, tvLogin;
    EditText editPhone, editPass, editNama, editEmail;
    Button btnRegister;
    ApiInterface mApiInterface;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        tvLogin = (TextView) findViewById(R.id.tv_login);
        editPhone = (EditText) findViewById(R.id.inp_phone);
        editPass = (EditText) findViewById(R.id.inp_password);
        editNama = (EditText) findViewById(R.id.inp_nama);
        editEmail = (EditText) findViewById(R.id.inp_email);
        btnRegister = (Button) findViewById(R.id.btn_register);

        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setMessage("Tunggu sebentar... ");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editNama.getText().toString().length() == 0)
                    editNama.setError("Tidak Boleh Kosong!");
                else if (editPass.getText().toString().length() == 0) {
                    editPass.setError("Tidak Boleh Kosong!");
                } else if (editPhone.getText().toString().length() == 0) {
                    editPhone.setError("Tidak Boleh Kosong!");
                } else if (editPass.getText().toString().length() == 0) {
                    editPass.setError("Tidak Boleh Kosong!");
                } else if (editEmail.getText().toString().length() == 0) {
                    editEmail.setError("Tidak Boleh Kosong!");
                }else{
                    register();
                }
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

    public void register(){
        progressDialog.show();
        Call<Register> registerCall= mApiInterface.register(editEmail.getText().toString(),editPhone.getText().toString(),editPass.getText().toString(),editNama.getText().toString());
        registerCall.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                progressDialog.dismiss();
                String message=response.body().getMessage();
                String status=response.body().getSuccess();
                Log.d("Status",status);
                if(status.equals("true")){
                    Toast.makeText(getApplicationContext(), "Register berhasil", Toast.LENGTH_LONG).show();
                    Intent Test1 = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(Test1);
                }else{
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("Error", ""+t);

            }
        });
    }
}