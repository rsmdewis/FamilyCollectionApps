package com.example.familycollection.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familycollection.R;
import com.example.familycollection.activity.DetailProdukActivity;
import com.example.familycollection.models.Product;
import com.example.familycollection.models.Produk;
import com.example.familycollection.models.ProdukTransaksi;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterProduk extends RecyclerView.Adapter<AdapterProduk.MyViewHolder> {
    private List<Product> productList;
    public AdapterProduk (List<Product>productList){this.productList=productList;}

    @NonNull
    @Override
    public AdapterProduk.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.from(parent.getContext()).inflate(R.layout.item_produk,parent,false);
        return new AdapterProduk.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProduk.MyViewHolder holder, int position) {
        Product produk =productList.get(position);
        holder.namaproduk.setText(produk.getNama());
        holder.harga.setText(produk.getPrice());
        final String urlGambarBerita = "http://192.168.1.10:8000/storage/" + produk.getGambar();
        Picasso.get().load(urlGambarBerita).into(holder.fotoproduk);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), DetailProdukActivity.class);
                mIntent.putExtra("id",productList.get(position).getId());
                mIntent.putExtra("nama",productList.get(position).getNama());
                mIntent.putExtra("harga",productList.get(position).getPrice());
                mIntent.putExtra("deskripsi",productList.get(position).getDeskripsi());
                mIntent.putExtra("gambar",productList.get(position).getGambar());
                view.getContext().startActivity(mIntent);

            }
        });


    }

    @Override
    public int getItemCount() {

        return (productList == null) ? 0 : productList.size();
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

