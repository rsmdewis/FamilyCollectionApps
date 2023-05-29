package com.example.familycollection.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familycollection.R;
import com.example.familycollection.models.ProdukTransaksi;

import java.util.List;

public class AdapterProdukTransaksi extends RecyclerView.Adapter<AdapterProdukTransaksi.MyViewHolder> {
    private List<ProdukTransaksi> userList;
    public AdapterProdukTransaksi (List<ProdukTransaksi>userList){this.userList=userList;}

    @NonNull
    @Override
    public AdapterProdukTransaksi.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.from(parent.getContext()).inflate(R.layout.item_produk_transaksi,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProdukTransaksi.MyViewHolder holder, int position) {
        ProdukTransaksi produk =userList.get(position);
        holder.fotoproduk.setImageResource(produk.getFotoproduk());
        holder.namaproduk.setText(produk.getNamaproduk());
        holder.berat.setText(produk.getBerat());
        holder.harga.setText(produk.getHarga());
        holder.item.setText(produk.getItem());
        holder.total.setText(produk.getTotal());


    }

    @Override
    public int getItemCount() {

        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView fotoproduk;
        private TextView namaproduk;
        private TextView berat;
        private TextView harga;
        private TextView item;
        private TextView total;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            fotoproduk=itemView.findViewById(R.id.img_produk);
            namaproduk=itemView.findViewById(R.id.tv_nama);
            berat=itemView.findViewById(R.id.tv_berat);
            harga=itemView.findViewById(R.id.tv_harga);
            item=itemView.findViewById(R.id.tv_jumlah);
            total=itemView.findViewById(R.id.tv_totalHarga);
        }

    }
}
