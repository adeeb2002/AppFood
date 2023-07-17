package com.example.food.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.food.Activity.AddItem;
import com.example.food.Models.FastFoodModel;
import com.example.food.R;
import com.example.food.TableDataBase.DataBase;
import com.example.food.databinding.FragmentAddBinding;

public class fragmentAdd extends Fragment {

    FragmentAddBinding binding;
    DataBase dataBase;
    Context context;
    ActivityResultLauncher<Intent> launcher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                }
            }
    );

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentAddBinding.inflate(inflater,container,false);
        context=container.getContext();
        dataBase=new DataBase(context);

        binding.addFastFoodBt.setOnClickListener(v -> {
            Intent intent=new Intent(context, AddItem.class);
            intent.putExtra("type","FastFood");
            launcher.launch(intent);
        });
        binding.addFruiteBt.setOnClickListener(v -> {
            Intent intent=new Intent(context, AddItem.class);
            intent.putExtra("type","Fruite");
            launcher.launch(intent);
        });
        binding.addDrinkBt.setOnClickListener(v -> {
            Intent intent=new Intent(context, AddItem.class);
            intent.putExtra("type","Drink");
            launcher.launch(intent);
        });
        binding.addRestorantBt.setOnClickListener(v -> {
            Intent intent=new Intent(context, AddItem.class);
            intent.putExtra("type","Restorant");
            launcher.launch(intent);

//            byte [] ph=dataBase.getFastFoodDataBase().get(0).getImage();
//            Bitmap bitmap=getImage(ph);
//            binding.imageView.setImageBitmap(bitmap);

        });

        return binding.getRoot();
    }

}