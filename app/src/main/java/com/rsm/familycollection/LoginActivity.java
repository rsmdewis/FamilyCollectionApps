package com.rsm.familycollection;

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

import com.rsm.familycollection.Model.Akun;
import com.rsm.familycollection.Model.GetAkun;
import com.rsm.familycollection.RestApi.ApiClient;
import com.rsm.familycollection.RestApi.ApiInterface;
import com.rsm.familycollection.activitymenu.MainActivity;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        if(!token.equals("fail")){
            Intent intent= new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Tunggu sebentar... ");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editUser.getText().toString().length() == 0)
                    editUser.setError("Tidak Boleh Kosong!");
                else if (editPass.getText().toString().length() == 0) {
                    editPass.setError("Tidak Boleh Kosong!");
                } else if (!isEmailValid(editUser.getText().toString())) {
                    editUser.setError("Data harus berupa email !");
                } else{
                    login();
                }

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

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private void login(){
        progressDialog.show();
        Call<GetAkun> akunCall = mApiInterface.postLogin(editUser.getText().toString(), editPass.getText().toString());
        akunCall.enqueue(new Callback<GetAkun>() {
            @Override
            public void onResponse(Call<GetAkun> call, Response<GetAkun> response) {
                progressDialog.dismiss();
                String message=response.message();

                Log.d("RES",""+response.code());
                if(response.code() == 201){
                    akuns = new ArrayList<>();
                    akuns.add(response.body().getListDataAkun());
                    Toast.makeText(LoginActivity.this, "Berhasil Login", Toast.LENGTH_LONG).show();
                    String token=response.body().getToken();
                    String id=akuns.get(0).getId();
                    String nama=akuns.get(0).getName();
                    String email=akuns.get(0).getEmail();
                    String phone=akuns.get(0).getPhone();
                    String city_id=akuns.get(0).getCity_id();
                    String province_id=akuns.get(0).getProvince_id();
                    String address=akuns.get(0).getAddress();


                    Intent intent = new Intent(LoginActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    editor.putString("TOKEN", token);
                    editor.putString("USER_ID", id);
                    editor.putString("NAMA", nama);
                    editor.putString("EMAIL", email);
                    editor.putString("PHONE", phone);
                    editor.putString("CITY_ID", city_id);
                    editor.putString("PROVINCE_ID", province_id);
                    editor.putString("ADDRESS", address);

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
}