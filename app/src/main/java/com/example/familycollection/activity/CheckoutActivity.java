package com.example.familycollection.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.familycollection.LoginActivity;
import com.example.familycollection.R;
import com.example.familycollection.RestApi.ApiClient;
import com.example.familycollection.RestApi.ApiInterface;
import com.example.familycollection.activitymenu.OrderActivity;
import com.example.familycollection.activitymenu.ProfileActivity;
import com.example.familycollection.adapter.AdapterCart;
import com.example.familycollection.adapter.AdapterProdukTransaksi;
import com.example.familycollection.models.AddCheckout;
import com.example.familycollection.models.Cart;
import com.example.familycollection.models.City;
import com.example.familycollection.models.GetCart;
import com.example.familycollection.models.GetCity;
import com.example.familycollection.models.GetProvince;
import com.example.familycollection.models.OngkirCost;
import com.example.familycollection.models.ProdukTransaksi;
import com.example.familycollection.models.Province;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity implements AdapterCart.IMethodCaller {
    TextView textTotalBelanja, textOngkir, textTotal;
    RecyclerView recyclerViewProduk;
    LinearLayout layoutFooter;
    CardView cardPengiriman;
    Button btnpesan, tambahFoto, cekOngkir;
    EditText editKeterangan, editPenerima, editTelepon, editAlamat;
    Spinner spinnerJasa, spinnerProvinsi, spinnerKota, spinnerKurir;
    RelativeLayout layoutProvinsi, layoutKota;
    LinearLayoutManager layoutManager;
    List<Cart> cartList;
    List<Province> provinceList;
    List<City> cityList;

    AdapterCart adapter;

    //Image request code
    private int PICK_IMAGE_REQUEST = 1;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;
    private Bitmap bitmap;

    //Uri to store the image uri
    private Uri filePath;
    ApiInterface mApiInterface;
    SharedPreferences sharedPreferences;
    String id,token;
    ImageView imageKeterangan;
    ProgressDialog progressDialog;

    String origin,destination,weight,totalBelanja,ongkir,part_image,jasa="2";
    MultipartBody.Part fileToUpload;
    File finalFile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        requestStoragePermission();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("Check Out");
        }

        textTotalBelanja = (TextView) findViewById(R.id.tv_totalBelanja);
        textOngkir = (TextView) findViewById(R.id.tv_ongkir);
        textTotal = (TextView) findViewById(R.id.tv_total);
        layoutFooter = (LinearLayout) findViewById(R.id.div_footer);
        btnpesan = (Button) findViewById(R.id.btn_pesan);
        tambahFoto = (Button) findViewById(R.id.foto_tambahan);
        cekOngkir = (Button) findViewById(R.id.cek_ongkir);
        layoutProvinsi = (RelativeLayout) findViewById(R.id.div_provinsi);
        layoutKota = (RelativeLayout) findViewById(R.id.div_kota);
        editKeterangan = (EditText) findViewById(R.id.edt_keterangan);
        editPenerima = (EditText) findViewById(R.id.edt_penerima);
        editTelepon = (EditText) findViewById(R.id.edt_telp);
        editAlamat = (EditText) findViewById(R.id.edt_alamat);
        spinnerJasa = (Spinner) findViewById(R.id.spinner_jasa);
        spinnerKurir = (Spinner) findViewById(R.id.spinner_kurir);
        spinnerProvinsi = (Spinner) findViewById(R.id.spn_provinsi);
        spinnerKota = (Spinner) findViewById(R.id.spn_kota);
        imageKeterangan = (ImageView) findViewById(R.id.image_keterangan);
        cardPengiriman = (CardView) findViewById(R.id.card_pengiriman);

        progressDialog = new ProgressDialog(CheckoutActivity.this);
        progressDialog.setMessage("Tunggu sebentar... ");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sharedPreferences = getApplicationContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN", "fail");
        id = sharedPreferences.getString("USER_ID", "fail");
        btnpesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _checkout();
            }
        });

        recyclerViewProduk=(RecyclerView) findViewById(R.id.rv_produk);
        layoutManager=new LinearLayoutManager(CheckoutActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        loadData();
        getProvince();
        tambahFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        spinnerJasa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (parentView.getItemAtPosition(position).toString().equals("Kirim Pesanan")){
                    jasa="1";
                    cardPengiriman.setVisibility(View.VISIBLE);
                }else{
                    jasa="2";
                    cardPengiriman.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
        spinnerProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
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

        cekOngkir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _cekOngkkir();
            }
        });


    }
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                // Get the image file URI
                String[] imageProjection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(filePath, imageProjection, null, null, null);
                if(cursor != null) {
                    cursor.moveToFirst();
                    int indexImage = cursor.getColumnIndex(imageProjection[0]);
                    part_image = cursor.getString(indexImage);
                }
                Uri tempUri = getImageUri(getApplicationContext(), bitmap);
                Log.d("PATH",getRealPathFromURI(tempUri));
                finalFile = new File(getRealPathFromURI(tempUri));
                imageKeterangan.setImageBitmap(bitmap);


            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }
    public void loadData(){
        Call<GetCart> getCartCall=mApiInterface.getCart(id,"Bearer "+token);
        getCartCall.enqueue(new Callback<GetCart>() {
            @Override
            public void onResponse(Call<GetCart> call, Response<GetCart> response) {
                cartList=response.body().getCartList();
                adapter= new AdapterCart(cartList,getApplicationContext(),CheckoutActivity.this,textTotalBelanja);
                recyclerViewProduk.setLayoutManager(layoutManager);
                recyclerViewProduk.setAdapter(adapter);
                weight=response.body().getGrand_total_weight();
                totalBelanja=response.body().getGrand_total_total();
                textTotalBelanja.setText(response.body().getGrand_total_total());
                textTotal.setText(response.body().getGrand_total_total());

            }

            @Override
            public void onFailure(Call<GetCart> call, Throwable t) {
                Log.d("ERR",""+t);
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
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CheckoutActivity.this,
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
        Call<GetProvince> getProvinceCall=mApiInterface.getProvince();
        getProvinceCall.enqueue(new Callback<GetProvince>() {
            @Override
            public void onResponse(Call<GetProvince> call, Response<GetProvince> response) {
                provinceList=response.body().getProvinces();
                List<String> listSpinner = new ArrayList<String>();
                for (int i = 0; i < provinceList.size(); i++){
                    listSpinner.add(provinceList.get(i).getProvince());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CheckoutActivity.this,
                        android.R.layout.simple_spinner_item, listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerProvinsi.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GetProvince> call, Throwable t) {

            }
        });
    }

    private void _cekOngkkir(){
        Call<OngkirCost> ongkirCostCall=mApiInterface.cekOngkir("Bearer "+token,"160",destination,weight,spinnerKurir.getSelectedItem().toString());
        ongkirCostCall.enqueue(new Callback<OngkirCost>() {
            @Override
            public void onResponse(Call<OngkirCost> call, Response<OngkirCost> response) {
                Log.d("RES",response.body().getCost());
                textOngkir.setText(response.body().getCost());
                Integer total=Integer.parseInt(response.body().getCost())+Integer.parseInt(totalBelanja);
                ongkir=response.body().getCost();
                textTotal.setText(total.toString());

            }

            @Override
            public void onFailure(Call<OngkirCost> call, Throwable t) {

            }
        });
    }

    private void _checkout(){
        RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), finalFile);
        MultipartBody.Part partImage = MultipartBody.Part.createFormData("image", finalFile.getName(), reqBody);
        Call<AddCheckout> checkoutCall;
        if(jasa.equals("1")){
            checkoutCall=mApiInterface.checkout("Bearer "+token,editPenerima.getText().toString(),editTelepon.getText().toString(),editKeterangan.getText().toString(),spinnerProvinsi.getSelectedItem().toString(),spinnerKota.getSelectedItem().toString(),editAlamat.getText().toString(),spinnerKurir.getSelectedItem().toString(),ongkir,partImage,id,jasa);
        }else{
            checkoutCall= mApiInterface.checkout("Bearer "+token,editPenerima.getText().toString(),editTelepon.getText().toString(),editKeterangan.getText().toString(),"","","","","",partImage,id,jasa);
        }
        checkoutCall.enqueue(new Callback<AddCheckout>() {
            @Override
            public void onResponse(Call<AddCheckout> call, Response<AddCheckout> response) {
                Toast.makeText(getApplicationContext(),"Checkout berhasil, lakukan pembayaran",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CheckoutActivity.this, OrderActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<AddCheckout> call, Throwable t) {
                Log.d("ERR",""+t);
            }
        });
    }
}