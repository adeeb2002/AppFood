package com.example.food.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.food.Fragment.InnerFragment.DrinksFragment;
import com.example.food.Fragment.InnerFragment.FastFoodFragment;
import com.example.food.Fragment.InnerFragment.FruitesFragment;

public class AdapterViewPager2 extends FragmentStateAdapter {


    public AdapterViewPager2(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FastFoodFragment();
            case 1:
                return new FruitesFragment();
            case 2:
                return new DrinksFragment();
            default: return new FastFoodFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
