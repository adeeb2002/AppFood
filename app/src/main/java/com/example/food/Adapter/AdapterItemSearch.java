package com.example.food.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.Holder.HolderItemSearch;
import com.example.food.Models.ItemSearch;
import com.example.food.R;

import java.util.ArrayList;

public class AdapterItemSearch extends RecyclerView.Adapter<HolderItemSearch> {

    ArrayList<ItemSearch> itemSearches=new ArrayList<>();
    Context context;

    public AdapterItemSearch(ArrayList<ItemSearch> itemSearches, Context context) {
        this.itemSearches = itemSearches;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderItemSearch onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HolderItemSearch(LayoutInflater.from(context).inflate(R.layout.search_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HolderItemSearch holder, int position) {
        ItemSearch itemSearch=itemSearches.get(position);
        holder.nameItem.setText(itemSearch.getName());
        byte[] imageItem=itemSearch.getImage();
        Bitmap bitmap= BitmapFactory.decodeByteArray(imageItem,0,imageItem.length);
        holder.imageItem.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return itemSearches.size();
    }

    public void filter(){
        notifyDataSetChanged();
    }
}
