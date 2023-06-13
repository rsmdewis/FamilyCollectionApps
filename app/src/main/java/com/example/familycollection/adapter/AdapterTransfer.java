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
import com.example.familycollection.models.Payment;
import com.example.familycollection.models.Transfer;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterTransfer extends RecyclerView.Adapter<AdapterTransfer.MyViewHolder> {
    private List<Payment> paymentList;
    public AdapterTransfer (List<Payment>paymentList){this.paymentList=paymentList;}

    @NonNull
    @Override
    public AdapterTransfer.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.from(parent.getContext()).inflate(R.layout.item_transfer,parent,false);
        return new AdapterTransfer.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTransfer.MyViewHolder holder, int position) {
        Payment payment =paymentList.get(position);
        String status;
        final String urlGambarBerita = "http://10.10.173.97:8000/storage/" + payment.getImage();
        Picasso.get().load(urlGambarBerita).into(holder.buktiTransfer);
        holder.tanggal.setText(payment.getCreated_at());
        holder.nominal.setText(payment.getPrice());

        switch(payment.getStatus()) {
            case "0":
                status="Pending";
                break;
            case "1":
                status="Selesai";
                break;
            default:
                status="Pending";
        }
        holder.status.setText(status);
    }

    @Override
    public int getItemCount() {

        return (paymentList == null) ? 0 : paymentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView buktiTransfer;
        private TextView tanggal,status;
        private TextView nominal;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            buktiTransfer=itemView.findViewById(R.id.img_transfer);
            tanggal=itemView.findViewById(R.id.tv_tgl);
            nominal=itemView.findViewById(R.id.tv_nominal);
            status=itemView.findViewById(R.id.tv_status);
        }

    }
}
