package com.rsm.familycollection.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rsm.familycollection.R;
import com.rsm.familycollection.activity.ProdukActivity;
import com.rsm.familycollection.models.ProductCategory;

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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), ProdukActivity.class);
                mIntent.putExtra("id",productCategoryList.get(position).getId());
                view.getContext().startActivity(mIntent);
            }
        });


    }

    @Override
    public int getItemCount() {

        return (productCategoryList == null) ? 0 : productCategoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView namakategori;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            namakategori=itemView.findViewById(R.id.tv_nama);
        }

    }
}


