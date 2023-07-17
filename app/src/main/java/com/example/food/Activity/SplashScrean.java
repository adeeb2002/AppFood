package com.example.food.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.food.R;
import com.example.food.TableDataBase.DataBase;
import com.example.food.databinding.ActivitySplashScreanBinding;

public class SplashScrean extends AppCompatActivity {

    ActivitySplashScreanBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screan);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        },1500);


    }

}