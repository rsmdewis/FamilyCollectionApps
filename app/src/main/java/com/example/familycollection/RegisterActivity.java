package com.example.familycollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.familycollection.RestApi.ApiClient;
import com.example.familycollection.RestApi.ApiInterface;
import com.example.familycollection.activity.EditProfileActivity;
import com.example.familycollection.models.City;
import com.example.familycollection.models.GetCity;
import com.example.familycollection.models.GetProvince;
import com.example.familycollection.models.Province;
import com.example.familycollection.models.Register;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    TextView tvRegister, tvLogin;
    EditText editPhone, editPass, editNama, editEmail,edtAlamat;
    Button btnRegister;
    ApiInterface mApiInterface;

    ProgressDialog progressDialog;
    Spinner spinnerProvinsi, spinnerKota;
    List<Province> provinceList;

    List<City> cityList;

    String destination,province;

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
        edtAlamat = (EditText) findViewById(R.id.edt_alamat);
        spinnerProvinsi = (Spinner) findViewById(R.id.spn_provinsi);
        spinnerKota = (Spinner) findViewById(R.id.spn_kota);
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
                }else if (edtAlamat.getText().toString().length() == 0) {
                    edtAlamat.setError("Tidak Boleh Kosong!");
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

    public void register(){
        progressDialog.show();
        Call<Register> registerCall= mApiInterface.register(editEmail.getText().toString(),editPhone.getText().toString(),editPass.getText().toString(),editNama.getText().toString(),destination,province,edtAlamat.getText().toString());
        registerCall.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                progressDialog.dismiss();
                String message=response.body().getMessage();
                String status=response.body().getSuccess();
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
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterActivity.this,
                        android.R.layout.simple_spinner_item, listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerKota.setAdapter(adapter);
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
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterActivity.this,
                        android.R.layout.simple_spinner_item, listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerProvinsi.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GetProvince> call, Throwable t) {

            }
        });
    }
}