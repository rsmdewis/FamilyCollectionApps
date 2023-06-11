package com.example.familycollection.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familycollection.R;
import com.example.familycollection.models.Order;
import com.example.familycollection.models.ProdukTransaksi;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterProdukTransaksi extends RecyclerView.Adapter<AdapterProdukTransaksi.MyViewHolder> {
    private List<Order> orderList;
    private  TextView textViewTotal,textGrandTotal;
    Integer grandTotal=0;

    String cost;
    public AdapterProdukTransaksi (List<Order>orderList,TextView textViewTotal,String cost, TextView textGrandTotal){
        this.orderList=orderList;
        this.textViewTotal=textViewTotal;
        this.cost=cost;
        this.textGrandTotal=textGrandTotal;
    }

    @NonNull
    @Override
    public AdapterProdukTransaksi.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.from(parent.getContext()).inflate(R.layout.item_produk_transaksi,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProdukTransaksi.MyViewHolder holder, int position) {
        Order order =orderList.get(position);
        final String urlGambarBerita = "http://192.168.1.5:8000/storage/" + order.getListProduct().getGambar();
        Picasso.get().load(urlGambarBerita).into(holder.fotoproduk);
        holder.namaproduk.setText(order.getListProduct().getNama());
        holder.berat.setText(order.getListProduct().getWeight()+ " Gram");

        Integer Total=Integer.parseInt(order.getListProduct().getPrice()) * Integer.parseInt(order.getQty());
        holder.total.setText(Total.toString());
        holder.harga.setText(order.getListProduct().getPrice());
        holder.tv_ukuran.setText(order.getSize());
        holder.tv_jumlah.setText(order.getQty()+ " item");

        grandTotal=grandTotal+Total;
        textViewTotal.setText("Rp. "+String.valueOf(grandTotal));

        if(cost != null){
            textGrandTotal.setText("Rp. "+String.valueOf(grandTotal+Integer.parseInt(cost)));
        }else{
            textGrandTotal.setText("Rp. "+String.valueOf(grandTotal));
        }

    }

    @Override
    public int getItemCount() {

        return (orderList == null) ? 0 : orderList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView fotoproduk;
        private TextView namaproduk,tv_ukuran,tv_jumlah;
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
            tv_ukuran=itemView.findViewById(R.id.tv_ukuran);
            tv_jumlah=itemView.findViewById(R.id.tv_jumlah);
        }

    }
}
