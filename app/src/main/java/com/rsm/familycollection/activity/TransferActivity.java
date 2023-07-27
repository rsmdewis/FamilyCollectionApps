package com.rsm.familycollection.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.rsm.familycollection.R;
import com.rsm.familycollection.RestApi.ApiClient;
import com.rsm.familycollection.RestApi.ApiInterface;
import com.rsm.familycollection.adapter.AdapterTransfer;
import com.rsm.familycollection.models.AddPayment;
import com.rsm.familycollection.models.GetPayment;
import com.rsm.familycollection.models.Payment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransferActivity extends AppCompatActivity {
    RecyclerView recyclerViewTransfer;
    Button btnBukti, btnKonfirmasi;
    LinearLayoutManager layoutManager;
    List<Payment> paymentList;
    AdapterTransfer adapter;
    ApiInterface mApiInterface;
    SharedPreferences sharedPreferences;
    String token,code;
    ImageView imgBukti;
    private int PICK_IMAGE_REQUEST = 1;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;
    private Bitmap bitmap;

    //Uri to store the image uri
    private Uri filePath;
    String part_image,user_id;
    File finalFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestStoragePermission();
        setContentView(R.layout.activity_transfer);
//        ActionBar actionBar = getSupportActionBar();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Konfirmasi Pembayaran");
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnBukti = (Button) findViewById(R.id.btn_bukti);
        btnKonfirmasi = (Button) findViewById(R.id.btn_konfirmasi);
        imgBukti = (ImageView) findViewById(R.id.img_bukti);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sharedPreferences = getApplicationContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN", "fail");
        user_id = sharedPreferences.getString("USER_ID", "fail");
        Intent mIntent=getIntent();
        code=mIntent.getStringExtra("code");

        initData();

        recyclerViewTransfer=(RecyclerView) findViewById(R.id.rv_transfer);
        layoutManager=new LinearLayoutManager(TransferActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        btnBukti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(part_image == null){
                    Toast.makeText(getApplicationContext(),"Bukti transfer tidak boleh kosong!",Toast.LENGTH_LONG).show();
                }else{
                    uploadBukti();
                }
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
                part_image=tempUri.toString();
                finalFile = new File(getRealPathFromURI(tempUri));
                imgBukti.setImageBitmap(bitmap);


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
    private void initData() {
        Call<GetPayment> getPaymentCall= mApiInterface.getPayment("Bearer "+token,code);
        getPaymentCall.enqueue(new Callback<GetPayment>() {
            @Override
            public void onResponse(Call<GetPayment> call, Response<GetPayment> response) {
                paymentList=response.body().getPaymentList();

                Log.d("RES",""+code);
                adapter= new AdapterTransfer(paymentList);
                recyclerViewTransfer.setLayoutManager(layoutManager);
                recyclerViewTransfer.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GetPayment> call, Throwable t) {
                Log.d("ERR",""+t);
            }
        });
    }

    public  void uploadBukti(){
        RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), finalFile);
        MultipartBody.Part partImage = MultipartBody.Part.createFormData("image", finalFile.getName(), reqBody);
        Call<AddPayment> addPaymentCall= mApiInterface.addPayment("Bearer "+token,code,partImage,user_id);
        addPaymentCall.enqueue(new Callback<AddPayment>() {
            @Override
            public void onResponse(Call<AddPayment> call, Response<AddPayment> response) {
                Toast.makeText(getApplicationContext(),"Bukti transfer berhasil diupload, tunggu di cek oleh admin",Toast.LENGTH_LONG).show();
                initData();
            }

            @Override
            public void onFailure(Call<AddPayment> call, Throwable t) {
                Log.d("ERR",""+t);
            }
        });
    }
}