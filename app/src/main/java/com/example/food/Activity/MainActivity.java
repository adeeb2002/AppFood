package com.example.food.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.food.Fragment.fragmentCart;
import com.example.food.Fragment.fragmentHome;
import com.example.food.Fragment.fragmentLike;
import com.example.food.Fragment.fragmentProfile;
import com.example.food.Models.DrinkModel;
import com.example.food.Models.FastFoodModel;
import com.example.food.Models.FruitsModel;
import com.example.food.Models.Restoraunt;
import com.example.food.Models.UserModel;
import com.example.food.R;
import com.example.food.TableDataBase.DataBase;
import com.example.food.databinding.ActivityMainBinding;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    ActivityMainBinding binding;
    DataBase dataBase=new DataBase(this);
    ArrayList<UserModel> user=new ArrayList<>();
    int id;
    boolean isLogin=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user=dataBase.getUserInDataBase();

        isLogin=getIntent().getBooleanExtra("isLogin",false);
        if (!isLogin){
            Toast.makeText(this, "Not Login", Toast.LENGTH_SHORT).show();
        }

        SharedPreferences sp1=getSharedPreferences("Login", MODE_PRIVATE);

        String UserName=sp1.getString("UserName", null);
        String Password = sp1.getString("Password", null);


        for (UserModel userModel:user){
            if (userModel.getUserName().equals(UserName) && userModel.getPassowrd().equals(Password)){
                ids=userModel.getIdUser();
                isLogin=true;
            }
        }

        id=getIntent().getIntExtra("idUser",-1);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenarMain,new fragmentHome()).commit();

        selectItemInChipNavigation();
//        insertDataInDataBase();
//        insertRestorantInDatabase();

    }

    static int ids = 0;
    public int getIdUser(){
        try {
            ids=getIntent().getIntExtra("idUser",-1);
        }catch (Exception e){
            Toast.makeText(this, "Null Id", Toast.LENGTH_SHORT).show();
        }
        return ids;
    }
    public static int getId(){
        return ids;
    }
    public  void setIds(int id){
        ids=id;
    }

    public void selectItemInChipNavigation(){
        binding.ismaelnetv.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment=null;
                if (i==R.id.homeMenu){
                    fragment=new fragmentHome();
                } else if (i==R.id.likeMenu) {
                    fragment=new fragmentLike();
                }else if (i==R.id.cartMenu) {
                    fragment=new fragmentCart();
                }else if (i==R.id.profilMenu) {
                    fragment=new fragmentProfile();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.contenarMain,fragment).commit();
            }
        });
    }

    public void insertDataInDataBase(){
        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();

        Bitmap bitmap1= BitmapFactory.decodeResource(getResources(),R.drawable.pizza1);
        bitmap1.compress(Bitmap.CompressFormat.PNG, 100, stream1);
        byte[] pizza1 = stream1.toByteArray();
        bitmap1.recycle();


        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
        Bitmap bitmap2=BitmapFactory.decodeResource(getResources(),R.drawable.pizza2);
        bitmap2.compress(Bitmap.CompressFormat.PNG, 100, stream2);
        byte[] pizza2 = stream2.toByteArray();
        bitmap2.recycle();



        ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
        Bitmap bitmap3=BitmapFactory.decodeResource(getResources(),R.drawable.pizza3);
        bitmap3.compress(Bitmap.CompressFormat.PNG, 100, stream3);
        byte[] pizza3 = stream3.toByteArray();
        bitmap3.recycle();



        ByteArrayOutputStream stream4 = new ByteArrayOutputStream();
        Bitmap bitmap4=BitmapFactory.decodeResource(getResources(),R.drawable.bergar);
        bitmap4.compress(Bitmap.CompressFormat.PNG, 100, stream4);
        byte[] berger = stream4.toByteArray();
        bitmap4.recycle();



        ByteArrayOutputStream stream5 = new ByteArrayOutputStream();
        Bitmap bitmap5=BitmapFactory.decodeResource(getResources(),R.drawable.chiken);
        bitmap5.compress(Bitmap.CompressFormat.PNG, 100, stream5);
        byte[] chiken = stream5.toByteArray();
        bitmap5.recycle();




        ByteArrayOutputStream stream6 = new ByteArrayOutputStream();
        Bitmap bitmap6=BitmapFactory.decodeResource(getResources(),R.drawable.blueberry_and_kiwi);
        bitmap6.compress(Bitmap.CompressFormat.PNG, 100, stream6);
        byte[] blueberry_and_kiwi = stream6.toByteArray();
        bitmap6.recycle();



        ByteArrayOutputStream stream7 = new ByteArrayOutputStream();
        Bitmap bitmap7=BitmapFactory.decodeResource(getResources(),R.drawable.blueberry_strawberry_and_watermelon);
        bitmap7.compress(Bitmap.CompressFormat.PNG, 100, stream7);
        byte[] blueberry_strawberry_and_watermelon = stream7.toByteArray();
        bitmap7.recycle();



        ByteArrayOutputStream stream8 = new ByteArrayOutputStream();
        Bitmap bitmap8=BitmapFactory.decodeResource(getResources(),R.drawable.mango_and_strawberry);
        bitmap8.compress(Bitmap.CompressFormat.PNG, 100, stream8);
        byte[] mango_and_strawberry = stream8.toByteArray();
        bitmap8.recycle();



        ByteArrayOutputStream stream9 = new ByteArrayOutputStream();
        Bitmap bitmap9=BitmapFactory.decodeResource(getResources(),R.drawable.strawberry_banana_mango_apple_and_orange);
        bitmap9.compress(Bitmap.CompressFormat.PNG, 100, stream9);
        byte[] strawberry_banana_mango_apple_and_orange = stream9.toByteArray();
        bitmap9.recycle();



        ByteArrayOutputStream stream10 = new ByteArrayOutputStream();
        Bitmap bitmap10=BitmapFactory.decodeResource(getResources(),R.drawable.strawberry_banana_mango_blueberr_and_kiwi);
        bitmap10.compress(Bitmap.CompressFormat.PNG, 100, stream10);
        byte[] strawberry_banana_mango_blueberr_and_kiwi = stream10.toByteArray();
        bitmap10.recycle();




        ByteArrayOutputStream stream11 = new ByteArrayOutputStream();
        Bitmap bitmap11=BitmapFactory.decodeResource(getResources(),R.drawable.watermelon_juice);
        bitmap11.compress(Bitmap.CompressFormat.PNG, 100, stream11);
        byte[] watermelon_juice = stream11.toByteArray();
        bitmap11.recycle();





        ByteArrayOutputStream stream12 = new ByteArrayOutputStream();
        Bitmap bitmap12=BitmapFactory.decodeResource(getResources(),R.drawable.strawberry_juice);
        bitmap12.compress(Bitmap.CompressFormat.PNG, 100, stream12);
        byte[] strawberry_juice = stream12.toByteArray();
        bitmap12.recycle();




        ByteArrayOutputStream stream13 = new ByteArrayOutputStream();
        Bitmap bitmap13=BitmapFactory.decodeResource(getResources(),R.drawable.lemon_juice_and_peach);
        bitmap13.compress(Bitmap.CompressFormat.PNG, 100, stream13);
        byte[] lemon_juice_and_peach = stream13.toByteArray();
        bitmap13.recycle();



        ByteArrayOutputStream stream14 = new ByteArrayOutputStream();
        Bitmap bitmap14=BitmapFactory.decodeResource(getResources(),R.drawable.fruit_juice);
        bitmap14.compress(Bitmap.CompressFormat.PNG, 100, stream14);
        byte[] fruit_juice = stream14.toByteArray();
        bitmap14.recycle();


        dataBase.insertFastFood(new FastFoodModel("Pizza1",10,10,"pizza1",pizza1));
        dataBase.insertFastFood(new FastFoodModel("Pizza2",20,20,"pizza2",pizza2));
        dataBase.insertFastFood(new FastFoodModel("Pizza3",30,30,"pizza3",pizza3));
        dataBase.insertFastFood(new FastFoodModel("Burger",15,15,"Burger",berger));
        dataBase.insertFastFood(new FastFoodModel("Chiken",22,22,"Chiken",chiken));
        dataBase.insertFruits(new FruitsModel("Blueberry And Kiwi",16,16,"Blueberry And Kiwi",blueberry_and_kiwi));
        dataBase.insertFruits(new FruitsModel("Blueberry Strawberry And Watermelon",10,10,"Blueberry Strawberry And Watermelon",blueberry_strawberry_and_watermelon));
        dataBase.insertFruits(new FruitsModel("Mango And Strawberry",8,8,"Mango And Strawberry",mango_and_strawberry));
        dataBase.insertFruits(new FruitsModel("Strawberry Banana Mango Apple And Orange",18,18,"Strawberry Banana Mango Apple And Orange",strawberry_banana_mango_apple_and_orange));
        dataBase.insertFruits(new FruitsModel("Strawberry Banana Mango Blueberr And Kiwi",15,15,"Strawberry Banana Mango Blueberr And Kiwi",strawberry_banana_mango_blueberr_and_kiwi));
        dataBase.insertDrinks(new DrinkModel("Watermelon Juice",10,10,"Watermelon Juice",watermelon_juice));
        dataBase.insertDrinks(new DrinkModel("Strawberry Juice",14,11,"Strawberry Juice",strawberry_juice));
        dataBase.insertDrinks(new DrinkModel("Lemon Juice And Peach",20,20,"Lemon Juice And Peach",lemon_juice_and_peach));
        dataBase.insertDrinks(new DrinkModel("Fruit Juice",13,13,"Fruit Juice",fruit_juice));

    }

    public void insertRestorantInDatabase(){

        ByteArrayOutputStream stream12 = new ByteArrayOutputStream();
        Bitmap bitmap12=BitmapFactory.decodeResource(getResources(),R.drawable.strawberry_juice);
        bitmap12.compress(Bitmap.CompressFormat.PNG, 100, stream12);
        byte[] strawberry_juice = stream12.toByteArray();
        bitmap12.recycle();


        ByteArrayOutputStream stream6 = new ByteArrayOutputStream();
        Bitmap bitmap6=BitmapFactory.decodeResource(getResources(),R.drawable.blueberry_and_kiwi);
        bitmap6.compress(Bitmap.CompressFormat.PNG, 100, stream6);
        byte[] blueberry_and_kiwi = stream6.toByteArray();
        bitmap6.recycle();



        ByteArrayOutputStream stream7 = new ByteArrayOutputStream();
        Bitmap bitmap7=BitmapFactory.decodeResource(getResources(),R.drawable.blueberry_strawberry_and_watermelon);
        bitmap7.compress(Bitmap.CompressFormat.PNG, 100, stream7);
        byte[] blueberry_strawberry_and_watermelon = stream7.toByteArray();
        bitmap7.recycle();



        ByteArrayOutputStream stream8 = new ByteArrayOutputStream();
        Bitmap bitmap8=BitmapFactory.decodeResource(getResources(),R.drawable.mango_and_strawberry);
        bitmap8.compress(Bitmap.CompressFormat.PNG, 100, stream8);
        byte[] mango_and_strawberry = stream8.toByteArray();
        bitmap8.recycle();



        ByteArrayOutputStream stream9 = new ByteArrayOutputStream();
        Bitmap bitmap9=BitmapFactory.decodeResource(getResources(),R.drawable.strawberry_banana_mango_apple_and_orange);
        bitmap9.compress(Bitmap.CompressFormat.PNG, 100, stream9);
        byte[] strawberry_banana_mango_apple_and_orange = stream9.toByteArray();
        bitmap9.recycle();



        ByteArrayOutputStream stream10 = new ByteArrayOutputStream();
        Bitmap bitmap10=BitmapFactory.decodeResource(getResources(),R.drawable.strawberry_banana_mango_blueberr_and_kiwi);
        bitmap10.compress(Bitmap.CompressFormat.PNG, 100, stream10);
        byte[] strawberry_banana_mango_blueberr_and_kiwi = stream10.toByteArray();
        bitmap10.recycle();



        dataBase.insertRestoraunt(new Restoraunt("Amazonico Dubai","",strawberry_juice));
        dataBase.insertRestoraunt(new Restoraunt("Egyptian Fairy Tale","",blueberry_and_kiwi));
        dataBase.insertRestoraunt(new Restoraunt("Shami House","",strawberry_banana_mango_blueberr_and_kiwi));
        dataBase.insertRestoraunt(new Restoraunt("Turkish Caftan","",strawberry_juice));
        dataBase.insertRestoraunt(new Restoraunt("Tuscan Bistro","",strawberry_banana_mango_apple_and_orange));
    }

}