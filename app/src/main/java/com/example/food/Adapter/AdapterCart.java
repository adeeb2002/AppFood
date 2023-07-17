package com.example.food.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.Holder.MyCartHolder;
import com.example.food.Holder.MyHolder;
import com.example.food.Listener.Click;
import com.example.food.Listener.ListenerAdapter;
import com.example.food.Models.CartModel;
import com.example.food.R;

import java.util.ArrayList;

public class AdapterCart extends RecyclerView.Adapter<MyCartHolder> {


    ArrayList<CartModel> cartModels = new ArrayList<>();
    Context context;
    Click listenerAdapter;



    public AdapterCart(ArrayList<CartModel> cartModels, Context context,Click listenerAdapter) {
        this.cartModels = cartModels;
        this.context = context;
        this.listenerAdapter=listenerAdapter;
    }

    @NonNull
    @Override
    public MyCartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyCartHolder(LayoutInflater.from(context).inflate(R.layout.item_like_cart,parent,false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MyCartHolder holder, int position) {
        CartModel as = cartModels.get(position);
        int tot= (int) as.getTotalPrice();
        int pc= (int) as.getPrice();
        as.setQoantity(tot/pc);
        Bitmap bitmap = BitmapFactory.decodeByteArray(as.getImage(), 0, as.getImage().length);
        holder.imageView.setImageBitmap(bitmap);
        holder.name.setText(as.getName());
        holder.detils.setText(as.getDetails());
        holder.totalPrice.setText(as.getTotalPrice() + " $ ");
        holder.quantity.setText(String.valueOf(as.getQoantity()));
        holder.price.setText(String.valueOf(as.getPrice()));

        holder.plus.setOnClickListener(v -> {
            as.setTotalPrice(as.getTotalPrice()+as.getPrice());
            holder.totalPrice.setText(String.valueOf(as.getTotalPrice()));
            as.setQoantity(as.getQoantity()+1);
            holder.quantity.setText(String.valueOf(as.getQoantity()));

            listenerAdapter.onPlus(position,as.getPrice());
            listenerAdapter.onClick(position,as.getTotalPrice(),as);
        });
        holder.mins.setOnClickListener(v -> {
            if (as.getQoantity()>1) {
                as.setTotalPrice(as.getTotalPrice() - as.getPrice());
                holder.totalPrice.setText(String.valueOf(as.getTotalPrice()));
                as.setQoantity(as.getQoantity() - 1);
                holder.quantity.setText(String.valueOf(as.getQoantity()));
                listenerAdapter.onMinus(position,as.getPrice());
                listenerAdapter.onClick(position,as.getTotalPrice(),as);
            }else{
                listenerAdapter.onRemove(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartModels.size();
    }
}