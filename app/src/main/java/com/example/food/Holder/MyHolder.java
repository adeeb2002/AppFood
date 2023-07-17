package com.example.food.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.R;

public class MyHolder extends RecyclerView.ViewHolder {

    public ImageView imgItem;
    public ImageView addToCart;
    public TextView nameItem;
    public TextView priceItem;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imgItem=itemView.findViewById(R.id.imgItem);
            addToCart=itemView.findViewById(R.id.addToCartImage);
            nameItem=itemView.findViewById(R.id.nameItemTv);
            priceItem=itemView.findViewById(R.id.priceTv);
        }
    }