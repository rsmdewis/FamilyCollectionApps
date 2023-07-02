package com.example.familycollection.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.familycollection.LoginActivity;
import com.example.familycollection.R;
import com.example.familycollection.RestApi.ApiClient;
import com.example.familycollection.RestApi.ApiInterface;
import com.example.familycollection.activitymenu.MainActivity;
import com.example.familycollection.activitymenu.ProfileActivity;
import com.example.familycollection.models.City;
import com.example.familycollection.models.GetCity;
import com.example.familycollection.models.GetProvince;
import com.example.familycollection.models.Province;
import com.example.familycollection.models.UpdateUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {


    EditText etUsername, etNama, etEmail, etnohp, etPassword,edtAddress;
    Button buttonSimpan;
    ApiInterface mApiInterface;
    SharedPreferences sharedPreferences;
    String token,id,name,email,phone,city_id,province_id,address;
    ProgressDialog progressDialog;

    SharedPreferences.Editor editor;
    Spinner spinnerProvinsi, spinnerKota;
    List<Province> provinceList;

    List<City> cityList;

    String destination,province;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Edit Profile");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNama = (EditText) findViewById(R.id.inp_nama);
        etEmail = (EditText) findViewById(R.id.inp_email);
        etnohp = (EditText) findViewById(R.id.inp_nohp);
        etPassword = (EditText) findViewById(R.id.inp_password);
        edtAddress = (EditText) findViewById(R.id.edt_alamat);
        buttonSimpan = (Button) findViewById(R.id.btn_simpan);
        sharedPreferences = getApplicationContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN", "fail");
        id = sharedPreferences.getString("USER_ID", "fail");
        name=sharedPreferences.getString("NAMA", "fail");
        email=sharedPreferences.getString("EMAIL", "fail");
        phone=sharedPreferences.getString("PHONE", "fail");
        city_id=sharedPreferences.getString("CITY_ID", "fail");
        province_id=sharedPreferences.getString("PROVINCE_ID", "fail");
        address=sharedPreferences.getString("ADDRESS", "fail");

        editor = sharedPreferences.edit();
        etNama.setText(name);
        etEmail.setText(email);
        etnohp.setText(phone);
        edtAddress.setText(address);
        spinnerProvinsi = (Spinner) findViewById(R.id.spn_provinsi);
        spinnerKota = (Spinner) findViewById(R.id.spn_kota);
        progressDialog = new ProgressDialog(EditProfileActivity.this);
        progressDialog.setMessage("Tunggu sebentar... ");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNama.getText().toString().length() == 0)
                    etNama.setError("Tidak Boleh Kosong!");
                else if (etEmail.getText().toString().length() == 0) {
                    etEmail.setError("Tidak Boleh Kosong!");
                } else if (etnohp.getText().toString().length() == 0) {
                    etnohp.setError("Tidak Boleh Kosong!");
                }else{
                    update();
                }

            }
        });
        getProvince();
        spinnerProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                province=provinceList.get(position).getProvince_id();
                getCity(provinceList.get(position).getProvince_id());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        spinnerKota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                destination=cityList.get(position).getCity_id();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

    }

    private void update(){
        Log.d("DATA",token);
        progressDialog.show();
        Call<UpdateUser> updateUserCall=mApiInterface.updateUser("Bearer "+token,id,etEmail.getText().toString(),etnohp.getText().toString(),etPassword.getText().toString(),etNama.getText().toString(),province_id,city_id,edtAddress.getText().toString());
        updateUserCall.enqueue(new Callback<UpdateUser>() {
            @Override
            public void onResponse(Call<UpdateUser> call, Response<UpdateUser> response) {
                progressDialog.dismiss();
                Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                editor.putString("NAMA", etNama.getText().toString());
                editor.putString("EMAIL", etEmail.getText().toString());
                editor.putString("PHONE", etnohp.getText().toString());
                editor.putString("CITY_ID", city_id);
                editor.putString("PROVINCE_ID", province_id);
                editor.putString("ADDRESS", edtAddress.getText().toString());
                editor.apply();
                Toast.makeText(EditProfileActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();

                startActivity(intent);

            }

            @Override
            public void onFailure(Call<UpdateUser> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditProfileActivity.this, "Email sudah terpakai", Toast.LENGTH_LONG).show();

            }
        });
    }
    private void getCity(String id){
        progressDialog.show();
        Call<GetCity> getCityCall=mApiInterface.getCity(id);
        getCityCall.enqueue(new Callback<GetCity>() {
            @Override
            public void onResponse(Call<GetCity> call, Response<GetCity> response) {
                cityList=response.body().getCityList();
                List<String> listSpinner = new ArrayList<String>();

                for (int i = 0; i < cityList.size(); i++){
                    listSpinner.add(cityList.get(i).getCity_name());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditProfileActivity.this,
                        android.R.layout.simple_spinner_item, listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerKota.setAdapter(adapter);
                for (City city : cityList) {
                    if (city.getCity_id().equals(city_id)) {
                        int spinnerPosition = adapter.getPosition(city.getCity_name());
                        spinnerKota.setSelection(spinnerPosition);
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<GetCity> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void getProvince(){
        progressDialog.show();
        Call<GetProvince> getProvinceCall=mApiInterface.getProvince();
        getProvinceCall.enqueue(new Callback<GetProvince>() {
            @Override
            public void onResponse(Call<GetProvince> call, Response<GetProvince> response) {
                provinceList=response.body().getProvinces();
                List<String> listSpinner = new ArrayList<String>();
                for (int i = 0; i < provinceList.size(); i++){
                    listSpinner.add(provinceList.get(i).getProvince());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditProfileActivity.this,
                        android.R.layout.simple_spinner_item, listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerProvinsi.setAdapter(adapter);
                for (Province province1 : provinceList) {
                    if (province1.getProvince_id().equals(province_id)) {
                        int spinnerPosition = adapter.getPosition(province1.getProvince());
                        spinnerProvinsi.setSelection(spinnerPosition);
                    }
                }

            }

            @Override
            public void onFailure(Call<GetProvince> call, Throwable t) {

            }
        });
    }
}