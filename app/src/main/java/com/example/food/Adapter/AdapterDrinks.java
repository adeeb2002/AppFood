package com.example.food.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.Activity.MainActivity;
import com.example.food.Holder.MyHolder;
import com.example.food.Listener.Click;
import com.example.food.Listener.ListenerAdapter;
import com.example.food.Models.CartModel;
import com.example.food.Models.DrinkModel;
import com.example.food.R;

import java.util.ArrayList;

public class AdapterDrinks extends RecyclerView.Adapter<MyHolder> {

    ArrayList<DrinkModel> drinkModelArrayList=new ArrayList<>();
    Context context;
    Click listenerAdapter;
    int onclick=0;

    public AdapterDrinks(ArrayList<DrinkModel> drinkModelArrayList, Context context,Click listenerAdapter) {
        this.drinkModelArrayList = drinkModelArrayList;
        this.context = context;
        this.listenerAdapter=listenerAdapter;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.card_item_food_fragment,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        DrinkModel as = drinkModelArrayList.get(position);
        int idUser= MainActivity.getId();
        Bitmap bitmap = BitmapFactory.decodeByteArray(as.getImage(), 0, as.getImage().length);
        holder.imgItem.setImageBitmap(bitmap);
        holder.nameItem.setText(as.getName());
        holder.priceItem.setText(as.getPrice() + " $ ");
        holder.itemView.setOnClickListener(v -> {
            listenerAdapter.onClick(position);
        });
        holder.addToCart.setOnClickListener(v -> {
            as.setQoantity(1);
            CartModel cartModel=new CartModel(as.getName(), as.getPrice(), as.getTotalPrice(), as.getQoantity(), as.getDetails(), as.getImage());
            cartModel.setUserId(idUser);
            listenerAdapter.addCart(position,cartModel);
        });
    }

    @Override
    public int getItemCount() {
        return drinkModelArrayList.size();
    }
}
