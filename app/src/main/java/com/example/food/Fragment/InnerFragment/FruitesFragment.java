package com.example.food.Fragment.InnerFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.food.Activity.detailsFood;
import com.example.food.Adapter.AdapterFastFood;
import com.example.food.Adapter.AdapterFruits;
import com.example.food.Adapter.AdapterRestoraunt;
import com.example.food.Listener.Click;
import com.example.food.Models.CartModel;
import com.example.food.Models.FruitsModel;
import com.example.food.Models.Restoraunt;
import com.example.food.R;
import com.example.food.TableDataBase.DataBase;
import com.example.food.databinding.FragmentFruitesBinding;

import java.util.ArrayList;


public class FruitesFragment extends Fragment implements Click {


    FragmentFruitesBinding binding;
    Context context;
    DataBase dataBase;
    ArrayList<FruitsModel> fruitsModels=new ArrayList<>();
    ArrayList<Restoraunt> restoraunts=new ArrayList<>();
    AdapterFruits adapter;
    AdapterRestoraunt adapterRestoraunt;

    ActivityResultLauncher<Intent> launcher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                }
            }
    );

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentFruitesBinding.inflate(inflater,container,false);

        context=getActivity().getApplicationContext();;
        dataBase=new DataBase(context);
        fruitsModels= dataBase.getFruiteDataBase();
        //restoraunts=dataBase.getRestorauntDataBase();

        adapter=new AdapterFruits(fruitsModels,context,this);
        binding.recycleFood.setAdapter(adapter);
        binding.recycleFood.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));



        return binding.getRoot();
    }

    @Override
    public void onClick(int index) {
        Intent intent=new Intent(context, detailsFood.class);
        intent.putExtra("index" , index);
        intent.putExtra("TypeFood" , "Fruites");
        launcher.launch(intent);
    }

    @Override
    public void onRemove(int index) {

    }

    @Override
    public void onPlus(int index, double value) {

    }

    @Override
    public void onMinus(int index, double value) {

    }

    @Override
    public void onClick(int index, double value, CartModel cartModel) {

    }

    @Override
    public void addCart(int index, CartModel cartModel) {
        dataBase.insertCart(cartModel);
    }

}