package com.example.familycollection.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familycollection.R;
import com.example.familycollection.activity.CheckoutActivity;
import com.example.familycollection.activity.DetailProdukActivity;
import com.example.familycollection.activity.TransaksiActivity;
import com.example.familycollection.models.Pesan;
import com.example.familycollection.models.Transaction;

import java.util.List;

public class AdapterStatus extends RecyclerView.Adapter<AdapterStatus.MyViewHolder> {
    private List<Transaction> transactionList;
    public AdapterStatus (List<Transaction>transactionList){
        this.transactionList=transactionList;
    }

    @NonNull
    @Override
    public AdapterStatus.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.from(parent.getContext()).inflate(R.layout.item_status,parent,false);
        return new AdapterStatus.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterStatus.MyViewHolder holder, int position) {
        String status="0";
        String pengiriman="0";
        Transaction transaction =transactionList.get(position);
        holder.namaproduk.setText("Code : "+transaction.getCode());
        holder.tanggal.setText(transaction.getTanggal());
        holder.total.setText(transaction.getTotal());
        switch(transaction.getStatus()) {
            case "0":
                status="Pending";
                break;
            case "1":
                status="Lunas";
                break;
            case "2":
                status="Dikirim";
                break;
            case "3":
                status="Selesai";
                break;
            case "4":
                status="Diterima";
                break;
            default:
                status="Pending";
                break;
        }

        switch(transaction.getPengiriman()) {
            case "1":
                pengiriman="Kirim Pesanan";
                break;
            case "2":
                pengiriman="Ambil Sendiri";
                break;
            default:
                pengiriman="Ambil Sendiri";
                break;
        }
        holder.status.setText(status);
        holder.tv_pengiriman.setText(pengiriman);
        holder.jumlah.setText(transaction.getTotal_product()+ " Item");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), TransaksiActivity.class);
                mIntent.putExtra("code",transactionList.get(position).getCode());
                view.getContext().startActivity(mIntent);
            }
        });



    }

    @Override
    public int getItemCount() {

        return (transactionList == null) ? 0 : transactionList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView namaproduk;
        private TextView tanggal;
        private TextView total;
        private TextView jumlah;
        private TextView status,tv_pengiriman;
//        private TextView detail;


        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            namaproduk=itemView.findViewById(R.id.tv_namaproduk);
            tanggal=itemView.findViewById(R.id.tv_tgl);
            total=itemView.findViewById(R.id.tv_total);
            jumlah=itemView.findViewById(R.id.tv_jumlah);
            status=itemView.findViewById(R.id.tv_status);
            tv_pengiriman=itemView.findViewById(R.id.tv_pengiriman);
//            detail=itemView.findViewById(R.id.btn_detail);
        }

    }
}
