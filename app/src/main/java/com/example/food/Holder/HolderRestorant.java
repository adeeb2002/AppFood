package com.example.food.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.R;

public class HolderRestorant extends RecyclerView.ViewHolder {

    public TextView name,detales,rating;
    public ImageView imgRes;

    public HolderRestorant(@NonNull View itemView) {
        super(itemView);

        name=itemView.findViewById(R.id.nameItemResTv);
        detales=itemView.findViewById(R.id.detilsResTv);
        rating=itemView.findViewById(R.id.ratingResTv);
        imgRes=itemView.findViewById(R.id.imgItemRes);

    }
}
