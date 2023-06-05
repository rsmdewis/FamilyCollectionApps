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
import com.example.familycollection.models.Transfer;

import java.util.List;

public class AdapterTransfer extends RecyclerView.Adapter<AdapterTransfer.MyViewHolder> {
    private List<Transfer> productList;
    public AdapterTransfer (List<Transfer>productList){this.productList=productList;}

    @NonNull
    @Override
    public AdapterTransfer.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.from(parent.getContext()).inflate(R.layout.item_transfer,parent,false);
        return new AdapterTransfer.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTransfer.MyViewHolder holder, int position) {
        Transfer transfer =productList.get(position);
        holder.buktiTransfer.setImageResource(transfer.getBuktiTransfer());
        holder.tanggal.setText(transfer.getTanggal());
        holder.nominal.setText(transfer.getNominal());


    }

    @Override
    public int getItemCount() {

        return (productList == null) ? 0 : productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView buktiTransfer;
        private TextView tanggal;
        private TextView nominal;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            buktiTransfer=itemView.findViewById(R.id.img_transfer);
            tanggal=itemView.findViewById(R.id.tv_tgl);
            nominal=itemView.findViewById(R.id.tv_nominal);
        }

    }
}
