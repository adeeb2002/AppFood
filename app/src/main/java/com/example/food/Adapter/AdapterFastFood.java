package com.example.food.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.Activity.MainActivity;
import com.example.food.Holder.MyHolder;
import com.example.food.Listener.Click;
import com.example.food.Listener.ListenerAdapter;
import com.example.food.Models.CartModel;
import com.example.food.Models.FastFoodModel;
import com.example.food.R;

import java.util.ArrayList;

public class AdapterFastFood extends RecyclerView.Adapter<MyHolder>{


    ArrayList<FastFoodModel>fastFoodModels=new ArrayList<>();
    Context context;
    ListenerAdapter listenerAdapter;
    Click click;

    public AdapterFastFood(ArrayList<FastFoodModel> fastFoodModels, Context context,Click click) {
        this.fastFoodModels = fastFoodModels;
        this.context = context;
        this.click=click;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.card_item_food_fragment,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        int idUser= MainActivity.getId();
        FastFoodModel as=fastFoodModels.get(position);
        Bitmap bitmap= BitmapFactory.decodeByteArray(as.getImage(),0,as.getImage().length) ;
        holder.imgItem.setImageBitmap(bitmap);
        holder.nameItem.setText(as.getName());
        holder.addToCart.setOnClickListener(v -> {
            CartModel cartModel=new CartModel(as.getName(), as.getPrice(), as.getTotalPrice(), as.getQoantity(), as.getDetails(), as.getImage());
            cartModel.setUserId(idUser);
            click.addCart(position,cartModel);
        });
        holder.priceItem.setText(as.getPrice()+" $ ");
        holder.itemView.setOnClickListener(v -> {
            click.onClick(position);
            Toast.makeText(context, ""+as.getName()+"  "+as.getPrice(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return fastFoodModels.size();
    }

}
