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
import com.example.food.databinding.FragmentRegisterBinding;

import java.util.ArrayList;


public class FragmentRegister extends Fragment {

    FragmentRegisterBinding binding;
    DataBase dataBase;
    ArrayList<UserModel> user = new ArrayList<>();
    Context context;
    int counterPassword = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        context = container.getContext();
        dataBase = new DataBase(context);
        user = dataBase.getUserInDataBase();

        SharedPreferences sp=getActivity().getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor Ed=sp.edit();



        binding.cardBack.setOnClickListener(v -> {
            Fragment fragment = null;
            fragment = new FragmintLogin();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenarLogin, fragment).commit();
        });

        binding.cardEye.setOnClickListener(v -> {
            counterPassword++;
            if (binding.cardEye.isClickable()) {
                if (counterPassword % 2 == 0) {
                    binding.eyePasswordImg.setImageResource(R.drawable.eye_password_food_app);
                    binding.passwordET.setInputType(InputType.TYPE_CLASS_TEXT);
                } else if (counterPassword % 2 != 0) {
                    binding.eyePasswordImg.setImageResource(R.drawable.hied_eye_password_food_app__1_);
                    binding.passwordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        binding.cardConvertEye.setOnClickListener(v -> {
            counterPassword++;
            if (binding.cardConvertEye.isClickable()) {
                if (counterPassword % 2 == 0) {
                    binding.eyeConvertPasswordImg.setImageResource(R.drawable.eye_password_food_app);
                    binding.convertPasswordET.setInputType(InputType.TYPE_CLASS_TEXT);
                } else if (counterPassword % 2 != 0) {
                    binding.eyeConvertPasswordImg.setImageResource(R.drawable.hied_eye_password_food_app__1_);
                    binding.convertPasswordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        binding.registerBt.setOnClickListener(v -> {
            if (binding.UserNameET.length() == 0 || binding.passwordET.length() == 0) {
                Toast.makeText(context, "Please Full Option", Toast.LENGTH_SHORT).show();
            } else {
                for (UserModel userModel : user) {
                    if (userModel.getUserName().equals(binding.UserNameET.getText().toString())) {
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
        binding.registerBt.setOnClickListener(v -> {
            if (binding.UserNameET.length() == 0 || binding.passwordET.length() == 0) {
                Toast.makeText(context, "Please Full Option", Toast.LENGTH_SHORT).show();
            } else {
                for (UserModel userModel : user) {
                    if (userModel.getUserName().equals(binding.UserNameET.getText().toString())) {
                        Toast.makeText(context, "The User Alredy In", Toast.LENGTH_SHORT).show();
                    }
                }
                String userName = binding.UserNameET.getText().toString();
                String password = binding.passwordET.getText().toString();
                String convertPassword = binding.convertPasswordET.getText().toString();
                if (password.equals(convertPassword)) {
                    UserModel userModel=new UserModel(userName, password);
                    dataBase.insertUser(userModel);
                    Intent intent=new Intent(context, MainActivity.class);
                    intent.putExtra("idUser",userModel.getIdUser());
                    Ed.putString("UserName",userModel.getUserName() );
                    Ed.putString("Password",userModel.getPassowrd());
                    Ed.apply();
                    startActivity(intent);
                } else {
                    Toast.makeText(context, "The Password Not Correct", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.cardGoogle.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://myaccount.google.com/?utm_source=sign_in_no_continue&pli=1&nlr=1"));
            startActivity(intent);
        });

        binding.cardFaceBook.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.facebook.com/"));
            startActivity(intent);
        });
        binding.cardTwitter.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://twitter.com/i/flow/login?redirect_after_login=%2Flogin%3Flang%3Dar"));
            startActivity(intent);
        });

        binding.signInTv.setOnClickListener(v -> {
            Fragment fragment = null;
            fragment = new FragmintLogin();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenarLogin, fragment).commit();
        });

        return binding.getRoot();
    }
}