package com.example.food.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.food.Activity.BuyActivity;
import com.example.food.Activity.MainActivity;
import com.example.food.Adapter.AdapterCart;
import com.example.food.Listener.Click;
import com.example.food.Models.CartModel;
import com.example.food.Models.DrinkModel;
import com.example.food.Models.FastFoodModel;
import com.example.food.Models.FruitsModel;
import com.example.food.Models.LikeModel;
import com.example.food.R;
import com.example.food.TableDataBase.DataBase;
import com.example.food.databinding.FragmentCartBinding;

import java.util.ArrayList;


public class fragmentCart extends Fragment implements Click {

    FragmentCartBinding binding;
    DataBase dataBase;
    ArrayList<CartModel> carts=new ArrayList<>();
    ArrayList<CartModel> cartsIdUser=new ArrayList<>();
    AdapterCart adapter;
    Context context;
    int refrasher=0;
    double totalAmount=0;
    int idUser=0;
    ActivityResultLauncher<Intent> launcher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                }
            });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentCartBinding.inflate(inflater,container,false);
        context =container.getContext();
        dataBase=new DataBase(context);
        carts=dataBase.getCartDataBase();

        try {
            idUser= MainActivity.getId();
        }catch (Exception e){
            Toast.makeText(context, "The Id Empty", Toast.LENGTH_SHORT).show();
        }

        for (CartModel cartModel:carts){
            if (cartModel.getUserId()==idUser){
                cartsIdUser.add(cartModel);
            }
        }

        adapter=new AdapterCart(cartsIdUser,context,this);
        binding.recycleCart.setAdapter(adapter);
        binding.recycleCart.setLayoutManager(new LinearLayoutManager(context));

        carts=dataBase.getCartDataBase();
        for (CartModel cartModel:cartsIdUser){
            totalAmount+=cartModel.getTotalPrice();
        }
        binding.totalPriceTv.setText(String.valueOf(totalAmount));

        binding.checkOutBt.setOnClickListener(v -> {
            Intent intent=new Intent(context, BuyActivity.class);
            launcher.launch(intent);
        });

        return binding.getRoot();
    }

    @Override
    public void onClick(int index) {

    }

    int index=0;
    @Override
    public void onRemove(int index) {
        this.index=index;
        Toast.makeText(context, ""+cartsIdUser.get(index).getId(), Toast.LENGTH_SHORT).show();
        new AlertDialog.Builder(context)
                .setTitle("Delete Cart ?")
                .setPositiveButton("Yas", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id=cartsIdUser.get(index).getId();
                        dataBase.deleteCartDataBase(String.valueOf(id));
                        Toast.makeText(context, ""+cartsIdUser.get(index).getId(), Toast.LENGTH_SHORT).show();
                        cartsIdUser=dataBase.getCartDataBase();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
        notifyCart();
    }

    @Override
    public void onPlus(int index,double price) {
        totalAmount+=price;
        binding.totalPriceTv.setText(String.valueOf(totalAmount));
    }

    @Override
    public void onMinus(int index,double price) {
        totalAmount-=price;
        binding.totalPriceTv.setText(String.valueOf(totalAmount));
    }

    @Override
    public void onClick(int index, double value, CartModel cartModel) {
        dataBase.updateCartDataBase(cartModel);
    }

    @Override
    public void addCart(int index, CartModel cartModel) {
        //dataBase.insertCart(cartModel);
    }



    public void RefreshData() {
        notifyCart();
        Refresh(1000);
    }

    private void Refresh(int milisacend) {

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                RefreshData();
            }
        };
        handler.postDelayed(runnable, milisacend);
    }

    public void notifyCart(){
        carts=dataBase.getCartDataBase();
        for (CartModel cartModel:carts){
            if (cartModel.getUserId()==idUser){
                cartsIdUser.add(cartModel);
            }
        }
        adapter=new AdapterCart(cartsIdUser,context,this);
        binding.recycleCart.setAdapter(adapter);
        adapter.notifyItemRemoved(index);
    }

}