package com.example.food.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.food.Activity.HelpActivity;
import com.example.food.Activity.LoginActivity;
import com.example.food.Activity.MainActivity;
import com.example.food.Adapter.AdapterItemSearch;
import com.example.food.Adapter.AdapterRestoraunt;
import com.example.food.Adapter.AdapterViewPager2;
import com.example.food.Fragment.InnerFragment.notifationFragment;
import com.example.food.Listener.Click;
import com.example.food.Models.CartModel;
import com.example.food.Models.DrinkModel;
import com.example.food.Models.FastFoodModel;
import com.example.food.Models.FruitsModel;
import com.example.food.Models.ItemSearch;
import com.example.food.Models.Restoraunt;
import com.example.food.Models.UserModel;
import com.example.food.R;
import com.example.food.TableDataBase.DataBase;
import com.example.food.databinding.FragmentHomeBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class fragmentHome extends Fragment implements Click {

    FragmentHomeBinding binding;
    Context context;
    DataBase dataBase;
    Fragment fragment = null;
    int id = 0;
    AdapterViewPager2 adapterViewPager2;
    ArrayList<UserModel> user = new ArrayList<>();
    ArrayList<ItemSearch> itemSearches=new ArrayList<>();
    AdapterItemSearch adapterItemSearch;
    int refrasher=0;
    AdapterRestoraunt adapterRestoraunt;
    ArrayList<Restoraunt> restoraunts=new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        context = container.getContext();
        dataBase = new DataBase(context);
        restoraunts=dataBase.getRestorauntDataBase();


        binding.recycleSearch.setVisibility(View.GONE);

        MainActivity mainActivity = (MainActivity) getActivity();
        try {
            id = mainActivity.getIdUser();
        } catch (Exception e) {
            System.out.println();
        }

        user = dataBase.getUserInDataBase();
        //Toast.makeText(context, "" + id, Toast.LENGTH_SHORT).show();

        binding.notifationCard.setOnClickListener(v -> {
            fragment=new notifationFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenarMain,fragment).commit();
        });


        adapterViewPager2 = new AdapterViewPager2(getActivity().getSupportFragmentManager(), getLifecycle());
        binding.viewPager.setAdapter(adapterViewPager2);
        binding.tabLayet.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.tabLayet.getTabAt(position).select();
            }
        });

        binding.menuIdCard.setOnClickListener(v -> {
            binding.drwerLayat.openDrawer(GravityCompat.START);
            updateDrawerNavegation();
        });
        selectItemNavegationDrawer();

        binding.searchId.clearFocus();
        binding.searchId.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String Text) {
                //getQueryInSearch(Text);
                RefreshDataSexrch(Text);
                return true;
            }
        });
        binding.searchId.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                if (itemSearches.size()==0){
                    binding.recycleSearch.setVisibility(View.GONE);
                    binding.tabLayet.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });


        //Toast.makeText(mainActivity, "Null Restotant"+dataBase.getRestorauntDataBase().size(), Toast.LENGTH_SHORT).show();



            adapterRestoraunt =new AdapterRestoraunt(restoraunts,context);
            binding.recycleFavorite.setAdapter(adapterRestoraunt);
            binding.recycleFavorite.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));


        return binding.getRoot();
    }

    public void updateDrawerNavegation() {
        View header = binding.drawerNavegation.getHeaderView(0);
        ImageView image = header.findViewById(R.id.headerImage);
        byte[] imageGet = null;
        for (UserModel userModel : user) {
            if (userModel.getIdUser() == id) {
                imageGet = userModel.getImageUser();
            }
        }
        try {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageGet, 0, imageGet.length);
            image.setImageBitmap(bitmap);
        } catch (Exception e) {
            System.out.printf("");
        }

    }
    
    public void selectItemNavegationDrawer(){
        binding.drawerNavegation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.logoutMenu){
                    Intent intent=new Intent(context, LoginActivity.class);
                    startActivity(intent);
                    MainActivity mainActivity=(MainActivity) getActivity();
                    mainActivity.finish();
                } else if (item.getItemId()==R.id.helpMenu) {
                    Intent intent=new Intent(context, HelpActivity.class);
                    startActivity(intent);
                } else if (item.getItemId()==R.id.addMenu) {
                    fragment=new fragmentAdd();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenarMain,fragment).commit();
                }
                return true;
            }
        });
    }

    public void getQueryInSearch(String text){
            if (text.length()!=0) {
                ArrayList<FastFoodModel> fastFoodModels = new ArrayList<>();
                ArrayList<FruitsModel> fruitsModels = new ArrayList<>();
                ArrayList<DrinkModel> drinkModels = new ArrayList<>();
                fastFoodModels = dataBase.getFastFoodDataBase();
                fruitsModels = dataBase.getFruiteDataBase();
                drinkModels = dataBase.getDrinksDataBase();
                for (int i = 0; i < fastFoodModels.size(); i++) {
                    if (fastFoodModels.get(i).getName().toLowerCase().contains(text.toLowerCase())) {
                        itemSearches.add(new ItemSearch(fastFoodModels.get(i).getId(), fastFoodModels.get(i).getName(), fastFoodModels.get(i).getImage()));
                    }
                }
                for (int i = 0; i < fruitsModels.size(); i++) {
                    if (fruitsModels.get(i).getName().toLowerCase().contains(text.toLowerCase())) {
                        itemSearches.add(new ItemSearch(fruitsModels.get(i).getId(), fruitsModels.get(i).getName(), fruitsModels.get(i).getImage()));
                    }
                }
                for (int i = 0; i < drinkModels.size(); i++) {
                    if (drinkModels.get(i).getName().toLowerCase().contains(text.toLowerCase())) {
                        itemSearches.add(new ItemSearch(drinkModels.get(i).getId(), drinkModels.get(i).getName(), drinkModels.get(i).getImage()));
                    }
                }
                if (itemSearches.size() == 0) {
                    Toast.makeText(context, "Not Found Item", Toast.LENGTH_SHORT).show();

                } else {
                    viewSearch();
                }
            }else {
                binding.recycleSearch.setVisibility(View.GONE);
                for (int i = 0; i < itemSearches.size(); i++) {
                    itemSearches.remove(i);
                }
                binding.tabLayet.setVisibility(View.VISIBLE);
                binding.viewPager.setVisibility(View.VISIBLE);
            }
    }

    private void Refresh(int milisacend) {

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                RefreshData();
            }
        };
        handler.postDelayed(runnable, milisacend);
    }
    public void RefreshDataSexrch(String text) {
        refrasher++;
        getQueryInSearch(text);
        Refresh(1000);
    }
    public void RefreshData() {
        refrasher++;
        Refresh(1000);
    }
    public void viewSearch(){
        binding.recycleSearch.setVisibility(View.VISIBLE);
        binding.tabLayet.setVisibility(View.GONE);
        binding.viewPager.setVisibility(View.GONE);
        adapterItemSearch=new AdapterItemSearch(itemSearches,context);
        binding.recycleSearch.setAdapter(adapterItemSearch);
        binding.recycleSearch.setLayoutManager(new LinearLayoutManager(context));
        adapterItemSearch.filter();
    }

    @Override
    public void onClick(int index) {

    }

    @Override
    public void onRemove(int index) {

    }

    @Override
    public void onPlus(int index, double value) {

    }

    @Override
    public void onMinus(int index, double value) {

    }

    @Override
    public void onClick(int index, double value, CartModel cartModel) {

    }

    @Override
    public void addCart(int index, CartModel cartModel) {
        //dataBase.insertCart(cartModel);
    }
}