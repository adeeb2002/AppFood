package com.example.food.Activity;

import static com.example.food.R.color.white;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.food.Fragment.FragmentRegister;
import com.example.food.Fragment.FragmintLogin;
import com.example.food.R;
import com.example.food.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    Fragment fragment=null;
    static boolean isLogin=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.loginBt.setOnClickListener(v -> {
            fragment = new FragmintLogin();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenarLogin,fragment).commit();
            binding.loginBt.setVisibility(View.GONE);
            binding.contenarInnerLogin.setVisibility(View.GONE);
            binding.contenarLogin.setBackgroundResource(R.color.white);
            isLogin=true;
        });

    }
    public static boolean getIdLogin(){
        if (isLogin){
            return true;
        }else {
            return false;
        }
    }

}