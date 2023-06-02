package com.example.familycollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.familycollection.Model.Akun;
import com.example.familycollection.Model.GetAkun;
import com.example.familycollection.RestApi.ApiClient;
import com.example.familycollection.RestApi.ApiInterface;
import com.example.familycollection.activitymenu.MainActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    LinearLayout layout;
    ImageView imageView;
    TextView textView, textRegister;
    EditText editUser, editPass;
    Button btnLogin;

    ApiInterface mApiInterface;
    String id_user, namaMhs;
    ArrayList<Akun> akuns;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;



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

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sharedPreferences = LoginActivity.this.getSharedPreferences("remember", Context.MODE_PRIVATE);
        String token= sharedPreferences.getString("TOKEN","fail");
        editor = sharedPreferences.edit();

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Tunggu sebentar... ");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                Call<GetAkun> akunCall = mApiInterface.postLogin(editUser.getText().toString(), editPass.getText().toString());
                akunCall.enqueue(new Callback<GetAkun>() {
                    @Override
                    public void onResponse(Call<GetAkun> call, Response<GetAkun> response) {
                        progressDialog.dismiss();
                        String message=response.message();
                        if(response.message().equals("Created")){
                            akuns = new ArrayList<>();
                            akuns.add(response.body().getListDataAkun());
                            Toast.makeText(LoginActivity.this, "Berhasil Login", Toast.LENGTH_LONG).show();
                            String token=response.body().getToken();
                            String id=akuns.get(0).getId();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            editor.putString("TOKEN", token);
                            editor.putString("USER_ID", id);

                            editor.apply();
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this, "Username atau password salah", Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<GetAkun> call, Throwable t) {
                        Log.e("Error", ""+t);
                        progressDialog.dismiss();
                    }
                });
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