package com.example.food.Fragment.InnerFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.food.Fragment.fragmentHome;
import com.example.food.R;
import com.example.food.databinding.FragmentNotifationFrabmentBinding;


public class notifationFragment extends Fragment {

    FragmentNotifationFrabmentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentNotifationFrabmentBinding.inflate(inflater,container,false);

        binding.backCard.setOnClickListener(v -> {
            Fragment fragment=null;
            fragment=new fragmentHome();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenarMain,fragment).commit();
        });

        return binding.getRoot();
    }
}