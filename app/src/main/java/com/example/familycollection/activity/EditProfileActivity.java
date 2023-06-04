package com.example.familycollection.activity;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.familycollection.LoginActivity;
import com.example.familycollection.R;
import com.example.familycollection.RestApi.ApiClient;
import com.example.familycollection.RestApi.ApiInterface;
import com.example.familycollection.activitymenu.MainActivity;
import com.example.familycollection.activitymenu.ProfileActivity;
import com.example.familycollection.models.UpdateUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    ConstraintLayout clRegister;
    ImageView imgLogo;
    TextView textEdit;
    EditText etUsername, etNama, etEmail, etnohp, etPassword;
    Button buttonSimpan;
    ApiInterface mApiInterface;
    SharedPreferences sharedPreferences;
    String token,id;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etNama = (EditText) findViewById(R.id.inp_nama);
        etEmail = (EditText) findViewById(R.id.inp_email);
        etnohp = (EditText) findViewById(R.id.inp_nohp);
        etPassword = (EditText) findViewById(R.id.inp_password);
        buttonSimpan = (Button) findViewById(R.id.btn_simpan);
        sharedPreferences = getApplicationContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN", "fail");
        id = sharedPreferences.getString("USER_ID", "fail");
        progressDialog = new ProgressDialog(EditProfileActivity.this);
        progressDialog.setMessage("Tunggu sebentar... ");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

    }

    private void update(){
        Log.d("DATA",token);
        progressDialog.show();
        Call<UpdateUser> updateUserCall=mApiInterface.updateUser("Bearer "+token,id,etEmail.getText().toString(),etnohp.getText().toString(),etPassword.getText().toString(),etNama.getText().toString());
        updateUserCall.enqueue(new Callback<UpdateUser>() {
            @Override
            public void onResponse(Call<UpdateUser> call, Response<UpdateUser> response) {
                progressDialog.dismiss();
                Toast.makeText(EditProfileActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<UpdateUser> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditProfileActivity.this, "Email sudah terpakai", Toast.LENGTH_LONG).show();

            }
        });
    }
}