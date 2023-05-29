package com.example.familycollection.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familycollection.R;
import com.example.familycollection.models.Pesan;

import java.util.List;

public class AdapterStatus extends RecyclerView.Adapter<AdapterStatus.MyViewHolder> {
    private List<Pesan> userList;
    public AdapterStatus (List<Pesan>userList){this.userList=userList;}

    @NonNull
    @Override
    public AdapterStatus.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.from(parent.getContext()).inflate(R.layout.item_status,parent,false);
        return new AdapterStatus.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterStatus.MyViewHolder holder, int position) {
        Pesan pesan =userList.get(position);
        holder.namaproduk.setText(pesan.getNamaproduk());
        holder.tanggal.setText(pesan.getTanggal());
        holder.total.setText(pesan.getTotal());
        holder.jumlah.setText(pesan.getJumlah());
        holder.status.setText(pesan.getStatus());



    }

    @Override
    public int getItemCount() {

        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView namaproduk;
        private TextView tanggal;
        private TextView total;
        private TextView jumlah;
        private TextView status;
//        private TextView detail;


        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            namaproduk=itemView.findViewById(R.id.tv_namaproduk);
            tanggal=itemView.findViewById(R.id.tv_tgl);
            total=itemView.findViewById(R.id.tv_total);
            jumlah=itemView.findViewById(R.id.tv_jumlah);
            status=itemView.findViewById(R.id.tv_status);
//            detail=itemView.findViewById(R.id.btn_detail);
        }

    }
}
