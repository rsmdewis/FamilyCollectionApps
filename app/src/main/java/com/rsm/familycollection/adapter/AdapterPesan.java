package com.rsm.familycollection.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rsm.familycollection.R;
import com.rsm.familycollection.models.Pesan;

import java.util.List;

public class AdapterPesan extends RecyclerView.Adapter<AdapterPesan.MyViewHolder> {
    private List<Pesan> userList;
    public AdapterPesan (List<Pesan>userList){this.userList=userList;}

    @NonNull
    @Override
    public AdapterPesan.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.from(parent.getContext()).inflate(R.layout.item_pesan,parent,false);
        return new AdapterPesan.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPesan.MyViewHolder holder, int position) {
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
        private TextView detail;
        private TextView batal;
        private TextView lanjut;


        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            namaproduk=itemView.findViewById(R.id.tv_namaproduk);
            tanggal=itemView.findViewById(R.id.tv_tgl);
            total=itemView.findViewById(R.id.tv_total);
            jumlah=itemView.findViewById(R.id.tv_jumlah);
            status=itemView.findViewById(R.id.tv_status);
            detail=itemView.findViewById(R.id.btn_detail);
            batal=itemView.findViewById(R.id.tv_batal);
            lanjut=itemView.findViewById(R.id.btn_lanjut);
        }

    }
}
