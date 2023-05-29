package com.example.familycollection.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familycollection.R;
import com.example.familycollection.models.Kategori;
import com.example.familycollection.models.ProductCategory;

import java.util.List;

public class AdapterKategori extends RecyclerView.Adapter<AdapterKategori.MyViewHolder> {
    private List<ProductCategory> productCategoryList;
    public AdapterKategori (List<ProductCategory>productCategoryList){this.productCategoryList=productCategoryList;}

    @NonNull
    @Override
    public AdapterKategori.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.from(parent.getContext()).inflate(R.layout.item_kategori,parent,false);
        return new AdapterKategori.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKategori.MyViewHolder holder, int position) {
        ProductCategory productCategory =productCategoryList.get(position);
//        holder.fotokategori.setImageResource(kategori.getFotokategori());
        holder.namakategori.setText(productCategory.getNama());


    }

    @Override
    public int getItemCount() {

        return (productCategoryList == null) ? 0 : productCategoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView fotokategori;
        private TextView namakategori;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            fotokategori=itemView.findViewById(R.id.image);
            namakategori=itemView.findViewById(R.id.tv_nama);
        }

    }
}


