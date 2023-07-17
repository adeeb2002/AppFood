package com.example.food.Fragment.InnerFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.food.R;
import com.example.food.databinding.FragmentBankBinding;


public class BankFragment extends Fragment {

    FragmentBankBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentBankBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }
}