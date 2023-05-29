package com.example.familycollection.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familycollection.R;
import com.example.familycollection.models.Produk;
import com.example.familycollection.models.ProdukTransaksi;

import java.util.List;

public class AdapterProduk extends RecyclerView.Adapter<AdapterProduk.MyViewHolder> {
    private List<Produk> userList;
    public AdapterProduk (List<Produk>userList){this.userList=userList;}

    @NonNull
    @Override
    public AdapterProduk.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.from(parent.getContext()).inflate(R.layout.item_produk,parent,false);
        return new AdapterProduk.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProduk.MyViewHolder holder, int position) {
        Produk produk =userList.get(position);
        holder.fotoproduk.setImageResource(produk.getFotoproduk());
        holder.namaproduk.setText(produk.getNamaproduk());
        holder.harga.setText(produk.getHarga());


    }

    @Override
    public int getItemCount() {

        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView fotoproduk;
        private TextView namaproduk;
        private TextView harga;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            fotoproduk=itemView.findViewById(R.id.img_produk);
            namaproduk=itemView.findViewById(R.id.tv_nama);
            harga=itemView.findViewById(R.id.tv_harga);
        }

    }
}

