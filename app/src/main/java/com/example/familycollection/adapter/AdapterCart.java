package com.example.familycollection.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familycollection.LoginActivity;
import com.example.familycollection.R;
import com.example.familycollection.RestApi.ApiClient;
import com.example.familycollection.RestApi.ApiInterface;
import com.example.familycollection.activity.CartActivity;
import com.example.familycollection.models.AddCart;
import com.example.familycollection.models.Cart;
import com.example.familycollection.models.DeleteCart;
import com.example.familycollection.models.Pesan;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.MyViewHolder> {
    private List<Cart> cartList;
    Context context;
    private IMethodCaller iMethodCaller;
    Integer calQty=0;
    private  TextView textViewTotal;
    public AdapterCart(List<Cart>cartList,Context context,IMethodCaller iMethodCaller,TextView textViewTotal){
        this.cartList=cartList;
        this.context=context;
        this.iMethodCaller = iMethodCaller;
        this.textViewTotal = textViewTotal;
    }

    ApiInterface mApiInterface;
    SharedPreferences sharedPreferences;
    String token,qty_ori;
    ProgressDialog progressDialog;

    Integer grandTotal=0;

    @NonNull
    @Override
    public AdapterCart.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sharedPreferences = parent.getContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN", "fail");

        progressDialog = new ProgressDialog(parent.getContext());
        progressDialog.setMessage("Tunggu sebentar... ");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        View view= layoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,parent,false);
        return new AdapterCart.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCart.MyViewHolder holder, int position) {


        Cart cart =cartList.get(position);
        calQty=Integer.parseInt(cart.getTotal_qty());
        holder.namaproduk.setText(cart.getNama());
        holder.tv_ukuran.setText(cart.getSize());
        holder.tv_berat.setText(cart.getTotal_weight() + " Gram");
        holder.tv_jumlah.setText(cart.getQty());
        holder.tv_harga.setText("Rp. "+cart.getPrice());
        holder.tv_totalHarga.setText("Rp. "+cart.getTotal_qty());
        final String urlGambarBerita = "http://192.168.18.206:8000/storage/" + cart.getGambar();
        Picasso.get().load(urlGambarBerita).into(holder.img_produk);
        holder.tv_jumlah.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!(charSequence.toString().equals("") || charSequence.toString().equals("0") )){
                    progressDialog.show();
                    Integer qty=Integer.parseInt(charSequence.toString())-Integer.parseInt(cart.getQty());
                    Call<AddCart> addCartCall=mApiInterface.postCart("Bearer "+token, cart.getUser_id(), cart.getProduct_id(),cart.getSize(),qty.toString());
                    addCartCall.enqueue(new Callback<AddCart>() {
                        @Override
                        public void onResponse(Call<AddCart> call, Response<AddCart> response) {
                            progressDialog.dismiss();
                            iMethodCaller.loadData();
                            Toast.makeText(context, "Update Cart", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<AddCart> call, Throwable t) {
                            Log.d("ERR",""+t);
                            progressDialog.dismiss();
                        }
                    });
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        grandTotal=grandTotal+calQty;
        textViewTotal.setText(String.valueOf(grandTotal));
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                Call<DeleteCart> deleteCartCall= mApiInterface.deleteCart(cart.getId(),"Bearer "+token);
                deleteCartCall.enqueue(new Callback<DeleteCart>() {
                    @Override
                    public void onResponse(Call<DeleteCart> call, Response<DeleteCart> response) {
                        progressDialog.dismiss();
                        iMethodCaller.loadData();
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();

                    }
                    @Override
                    public void onFailure(Call<DeleteCart> call, Throwable t) {

                    }
                });

            }
        });

    }

    public interface IMethodCaller {
        void loadData();

    }
    @Override
    public int getItemCount() {

        return (cartList == null) ? 0 : cartList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView namaproduk,tv_berat,tv_harga,tv_totalHarga,tv_ukuran,tv_jumlah;
        private ImageView btn_delete,img_produk;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            namaproduk = itemView.findViewById(R.id.tv_nama);
            tv_jumlah = itemView.findViewById(R.id.tv_jumlah);
            tv_ukuran = itemView.findViewById(R.id.tv_ukuran);
            tv_berat = itemView.findViewById(R.id.tv_berat);
            tv_harga = itemView.findViewById(R.id.tv_harga);
            tv_totalHarga = itemView.findViewById(R.id.tv_totalHarga);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            img_produk = itemView.findViewById(R.id.img_produk);

        }
    }
}
