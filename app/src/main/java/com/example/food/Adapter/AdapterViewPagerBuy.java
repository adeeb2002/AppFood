package com.example.food.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.food.Fragment.InnerFragment.BankFragment;
import com.example.food.Fragment.InnerFragment.MasterCardFragment;
import com.example.food.Fragment.InnerFragment.PayPulFragment;

public class AdapterViewPagerBuy extends FragmentStateAdapter {


    public AdapterViewPagerBuy(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new MasterCardFragment();
            case 1:
                return new PayPulFragment();
            case 2:
                return new BankFragment();
            default:return new MasterCardFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
