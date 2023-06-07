package com.example.familycollection.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.familycollection.R;

public class HelpActivity extends AppCompatActivity {
    ImageView imageViewpp;
    TextView tvPp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("Bantuan");
        }
        tvPp = (TextView) findViewById(R.id.tv_pp);
        imageViewpp = (ImageView) findViewById(R.id.imageView);
    }
}