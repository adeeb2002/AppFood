package com.example.food.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.R;

public class HolderItemSearch extends RecyclerView.ViewHolder {

    public TextView nameItem;
    public ImageView imageItem;

    public HolderItemSearch(@NonNull View itemView) {
        super(itemView);
        nameItem=itemView.findViewById(R.id.nameItemSearch);
        imageItem=itemView.findViewById(R.id.imageItemSearch);
    }
}
