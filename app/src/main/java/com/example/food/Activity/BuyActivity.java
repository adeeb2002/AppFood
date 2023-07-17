package com.example.food.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.food.Adapter.AdapterViewPagerBuy;
import com.example.food.Fragment.InnerFragment.BankFragment;
import com.example.food.Fragment.InnerFragment.MasterCardFragment;
import com.example.food.Fragment.InnerFragment.PayPulFragment;
import com.example.food.R;
import com.example.food.databinding.ActivityBuyBinding;
import com.google.android.material.navigation.NavigationBarView;

public class BuyActivity extends AppCompatActivity {

    ActivityBuyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBuyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportFragmentManager().beginTransaction().replace(R.id.viewPagerBuy,new MasterCardFragment()).commit();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.navegationPayment.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                if (item.getItemId()==R.id.masterCardMenu){
                    fragment=new MasterCardFragment();
                } else if (item.getItemId()==R.id.payPulMenu) {
                    fragment=new PayPulFragment();
                }else if (item.getItemId()==R.id.bankMenu){
                    fragment=new BankFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.viewPagerBuy,fragment).commit();
                return true;
            }
        });

        binding.backBuyActivity.setOnClickListener(v -> {
            finish();
        });

    }
}