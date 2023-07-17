package com.example.food.Listener;

import com.example.food.Models.CartModel;

public interface Click {

    void onClick(int index);
    void onRemove(int index);
    void onPlus(int index,double value);
    void onMinus(int index,double value);
    void onClick(int index, double value, CartModel cartModel);
    void addCart(int index,CartModel cartModel);

}
