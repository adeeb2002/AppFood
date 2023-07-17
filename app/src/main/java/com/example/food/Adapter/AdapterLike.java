package com.example.food.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.Holder.MyCartHolder;
import com.example.food.Listener.Click;
import com.example.food.Listener.ListenerAdapter;
import com.example.food.Models.LikeModel;
import com.example.food.R;

import java.util.ArrayList;

public class AdapterLike extends RecyclerView.Adapter<MyCartHolder> {

    ArrayList<LikeModel> likeModels=new ArrayList<>();
    Context context;
    Click listenerAdapter;
    int quantity=0;
    double totalPrice=0;
    int plus=1;

    public AdapterLike(ArrayList<LikeModel> likeModels, Context context,Click listenerAdapter) {
        this.likeModels = likeModels;
        this.context = context;
        this.listenerAdapter=listenerAdapter;
    }

    @NonNull
    @Override
    public MyCartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyCartHolder(LayoutInflater.from(context).inflate(R.layout.item_like,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartHolder holder, @SuppressLint("RecyclerView") int position) {
        LikeModel as=likeModels.get(position);
            Bitmap bitmap = BitmapFactory.decodeByteArray(as.getImage(), 0, as.getImage().length);
            holder.imageView.setImageBitmap(bitmap);
            holder.name.setText(as.getName());
            holder.detils.setText(as.getDetails());
            holder.totalPrice.setText(as.getPrice() + " $ ");
            holder.quantity.setText("");
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listenerAdapter.onRemove(position);
                    return true;
                }
            });
            holder.itemView.setOnClickListener(v -> {
                listenerAdapter.onClick(position);
            });
    }


    @Override
    public int getItemCount() {
        return likeModels.size();
    }
}
