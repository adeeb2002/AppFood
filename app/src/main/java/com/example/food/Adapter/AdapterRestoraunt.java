package com.example.food.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.Holder.HolderRestorant;
import com.example.food.Holder.MyHolder;
import com.example.food.Listener.ListenerAdapter;
import com.example.food.Models.FastFoodModel;
import com.example.food.Models.Restoraunt;
import com.example.food.R;

import java.util.ArrayList;

public class AdapterRestoraunt extends RecyclerView.Adapter<HolderRestorant> {

    ArrayList<Restoraunt> fastFoodModels=new ArrayList<>();
    Context context;
    ListenerAdapter listenerAdapter;

    public AdapterRestoraunt(ArrayList<Restoraunt> fastFoodModels, Context context) {
        this.fastFoodModels = fastFoodModels;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderRestorant onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HolderRestorant(LayoutInflater.from(context).inflate(R.layout.card_item_restoraunt_fragment,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HolderRestorant holder, int position) {
        Restoraunt as=fastFoodModels.get(position);
        Bitmap bitmap= BitmapFactory.decodeByteArray(as.getImage(),0,as.getImage().length) ;
        holder.imgRes.setImageBitmap(bitmap);
        holder.name.setText(as.getName());
        holder.detales.setText(as.getDetails());
    }


    @Override
    public int getItemCount() {
        return fastFoodModels.size();
    }
}
