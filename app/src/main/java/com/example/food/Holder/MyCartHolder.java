package com.example.food.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.R;

public class MyCartHolder extends RecyclerView.ViewHolder {

    public ImageView imageView,plus,mins;
    public TextView name,detils,price,totalPrice,quantity;

    public MyCartHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.imgCard);
        plus=itemView.findViewById(R.id.plusImg);
        mins=itemView.findViewById(R.id.minsImg);
        name=itemView.findViewById(R.id.nameCardTv);
        detils=itemView.findViewById(R.id.detilsCardTv);
        totalPrice=itemView.findViewById(R.id.priceCardTv);
        price=itemView.findViewById(R.id.priceTv);
        quantity=itemView.findViewById(R.id.quantityCartTv);

    }
}
