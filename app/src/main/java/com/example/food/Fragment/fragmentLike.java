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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.food.Activity.detailsFood;
import com.example.food.Adapter.AdapterLike;
import com.example.food.Listener.Click;
import com.example.food.Listener.ListenerAdapter;
import com.example.food.Models.CartModel;
import com.example.food.Models.LikeModel;
import com.example.food.R;
import com.example.food.TableDataBase.DataBase;
import com.example.food.databinding.FragmentLikeBinding;

import java.util.ArrayList;


public class fragmentLike extends Fragment implements Click {

    FragmentLikeBinding binding;
    DataBase dataBase;
    Context context;
    ArrayList<LikeModel> likeModels=new ArrayList<>();
    AdapterLike like;
    int count=0;
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

        binding=FragmentLikeBinding.inflate(inflater,container,false);
        context=container.getContext();
        dataBase=new DataBase(context);
        likeModels=dataBase.getLikeDataBase();

        like=new AdapterLike(likeModels,context,this);
        binding.recycleLike.setAdapter(like);
        binding.recycleLike.setLayoutManager(new LinearLayoutManager(context));

        count=likeModels.size();
        binding.countItemTv.setText(count+"");

        return binding.getRoot();
    }


    @Override
    public void onClick(int index) {
        Intent intent=new Intent(context, detailsFood.class);
        intent.putExtra("index" , index);
        intent.putExtra("TypeFood" , "FastFood");
        launcher.launch(intent);
    }

    @Override
    public void onRemove(int index) {
        new AlertDialog.Builder(context)
                .setTitle("Do you want to delete ? ")
                .setMessage("This item will be removed from the cart permanently")
                .setPositiveButton("Yas", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LikeModel like=dataBase.getLikeDataBase().get(index);
                        String id=String.valueOf(like.getId()+1);
                        dataBase.deleteLikeDataBase(id);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
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

    }


}