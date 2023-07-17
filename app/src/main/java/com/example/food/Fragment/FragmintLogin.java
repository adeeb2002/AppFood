package com.example.food.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.food.Activity.LoginActivity;
import com.example.food.Activity.MainActivity;
import com.example.food.Models.UserModel;
import com.example.food.R;
import com.example.food.TableDataBase.DataBase;
import com.example.food.databinding.FragmentFragmintLoginBinding;
import com.example.food.databinding.FragmentRegisterBinding;

import java.util.ArrayList;

public class FragmintLogin extends Fragment {

    FragmentFragmintLoginBinding binding;
    DataBase dataBase;
    ArrayList<UserModel> user=new ArrayList<>();
    Context context;
    int counterPassword=1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentFragmintLoginBinding.inflate(inflater,container,false);
        context=container.getContext();
        dataBase=new DataBase(context);
        user=dataBase.getUserInDataBase();

        SharedPreferences sp=getActivity().getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor Ed=sp.edit();


        binding.cardBack.setOnClickListener(v -> {
            Intent intent=new Intent(context, LoginActivity.class);
            startActivity(intent);
        });

        binding.cardEye.setOnClickListener(v -> {
            counterPassword++;
            if (binding.cardEye.isClickable()){
                if (counterPassword%2==0){
                    binding.eyePasswordImg.setImageResource(R.drawable.eye_password_food_app);
                    binding.passwordET.setInputType(InputType.TYPE_CLASS_TEXT);
                } else if (counterPassword%2!=0) {
                    binding.eyePasswordImg.setImageResource(R.drawable.hied_eye_password_food_app__1_);
                    binding.passwordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        binding.signInBt.setOnClickListener(v -> {
            if (binding.userNameET.length()==0 || binding.passwordET.length()==0){
                Toast.makeText(context, "Please Full Option", Toast.LENGTH_SHORT).show();
            }else {
                for (UserModel userModel : user) {
                    if (userModel.getUserName().equals(binding.userNameET.getText().toString())) {
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.putExtra("idUser", userModel.getIdUser());
                        Ed.putString("UserName",userModel.getUserName() );
                        Ed.putString("Password",userModel.getPassowrd());
                        Ed.apply();
                        startActivity(intent);
                    }
                }
            }
        });

        binding.cardGoogle.setOnClickListener(v -> {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://myaccount.google.com/?utm_source=sign_in_no_continue&pli=1&nlr=1"));
            startActivity(intent);
        });

        binding.cardFaceBook.setOnClickListener(v -> {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.facebook.com/"));
            startActivity(intent);
        });
        binding.cardTwitter.setOnClickListener(v -> {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://twitter.com/i/flow/login?redirect_after_login=%2Flogin%3Flang%3Dar"));
            startActivity(intent);
        });

        binding.registerTv.setOnClickListener(v -> {
            Fragment fragment=null;
            fragment=new FragmentRegister();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenarLogin,fragment).commit();
        });

        return binding.getRoot();
    }
}