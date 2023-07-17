package com.example.food.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.food.Activity.LoginActivity;
import com.example.food.Activity.MainActivity;
import com.example.food.Models.UserModel;
import com.example.food.R;
import com.example.food.TableDataBase.DataBase;
import com.example.food.databinding.FragmentProfileBinding;

import java.util.ArrayList;


public class fragmentProfile extends Fragment {

    FragmentProfileBinding binding;
    DataBase dataBase;
    Context context;
    ArrayList<UserModel> user = new ArrayList<>();
    int idUser=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        context = container.getContext();
        dataBase = new DataBase(context);
        user = dataBase.getUserInDataBase();

        MainActivity mainActivity=(MainActivity) getActivity();
        try {
            idUser=mainActivity.getIdUser();
        }catch (NullPointerException e){
            Toast.makeText(mainActivity, "Null Id", Toast.LENGTH_SHORT).show();
        }

        if (idUser!=-1){
            Fragment fragment=null;
            fragment=new FragmentProfileUser();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenarMain,fragment).commit();
        }

        binding.registerProfileBt.setOnClickListener(v -> {
            Intent intent=new Intent(context, LoginActivity.class);
            startActivity(intent);
        });

        return binding.getRoot();
    }
}