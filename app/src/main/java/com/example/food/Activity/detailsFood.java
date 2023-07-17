package com.example.food.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import com.example.food.Fragment.fragmentCart;
import com.example.food.Models.CartModel;
import com.example.food.Models.DrinkModel;
import com.example.food.Models.FastFoodModel;
import com.example.food.Models.FruitsModel;
import com.example.food.Models.LikeModel;
import com.example.food.R;
import com.example.food.TableDataBase.DataBase;
import com.example.food.databinding.ActivityDetailsFoodBinding;

public class detailsFood extends AppCompatActivity {

    ActivityDetailsFoodBinding binding;
    DataBase dataBase=new DataBase(this);
    String typeFood="";
    int index=0;
    double totalPrice =0;
    int plus=1;
    int onClick=0;
    boolean isLike=false;
    boolean isLogin=false;
    int idUser=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailsFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        typeFood=getIntent().getStringExtra("TypeFood");
        index=getIntent().getIntExtra("index",-1);

        isLogin=LoginActivity.getIdLogin();
        if (isLogin){
            Toast.makeText(this, "Is Login", Toast.LENGTH_SHORT).show();
        }
        idUser=MainActivity.getId();

        binding.backMainActivity.setOnClickListener(v -> {
            finish();
        });

        if (typeFood.equals("FastFood")){
            FastFoodModel fastFoodModel=dataBase.getFastFoodDataBase().get(index);
            binding.likeItemImg.setOnClickListener(v -> {
                onClick++;
                if (onClick%2!=0) {
                    binding.likeItemImg.setImageResource(R.drawable.lovenotemptyfoodicon);
                    isLike=true;
                } else if (onClick%2==0) {
                    binding.likeItemImg.setImageResource(R.drawable.loveemptyfoodicon);
                    isLike=false;
                }
            });
            totalPrice=fastFoodModel.getPrice();
            binding.counterTv.setText(String.valueOf(plus));
            binding.plusImg.setOnClickListener(v -> {
                plus++;
                totalPrice=totalPrice+fastFoodModel.getPrice();
                binding.counterTv.setText(String.valueOf(plus));
                binding.totalPriceTv.setText(String.valueOf(totalPrice));
            });
            binding.minasImg.setOnClickListener(v -> {
                if (plus>1) {
                    plus = plus - 1;
                    binding.counterTv.setText(String.valueOf(plus));
                    totalPrice-=fastFoodModel.getPrice();
                    binding.totalPriceTv.setText(String.valueOf(totalPrice));
                }else {
                    Toast.makeText(this, "The Quantity Equal 1", Toast.LENGTH_SHORT).show();
                }
            });

            plus=fastFoodModel.getQoantity();

            binding.nameTv.setText(fastFoodModel.getName());
            binding.detilsTv.setText(fastFoodModel.getDetails());
            binding.totalPriceTv.setText(String.valueOf(totalPrice));

            byte [] ph=dataBase.getFastFoodDataBase().get(index).getImage();
            Bitmap bitmap=getImage(ph);
            binding.imageViewItem.setImageBitmap(bitmap);
            if (fastFoodModel.getTotalPrice()/fastFoodModel.getPrice()==2 && fastFoodModel.getQoantity()==1){
                fastFoodModel.setQoantity(2);
                binding.counterTv.setText(String.valueOf(fastFoodModel.getQoantity()));
            }

             binding.goToCartBt.setOnClickListener(v -> {
                 if (isLogin) {
                     String name = fastFoodModel.getName();
                     String det = fastFoodModel.getDetails();
                     int qua = plus;
                     int id = fastFoodModel.getId();
                     byte[] image = fastFoodModel.getImage();
                     CartModel cartModel = new CartModel(name, fastFoodModel.getPrice(), totalPrice, qua, det, image);
                     cartModel.setNumberCart(id);
                     cartModel.setUserId(idUser);
                     dataBase.insertCart(cartModel);
                     Toast.makeText(this, ""+idUser, Toast.LENGTH_SHORT).show();
                     if (isLike) {
                         LikeModel likeModel = new LikeModel(name, fastFoodModel.getPrice(), det, image);
                         likeModel.setId(id);
                         dataBase.insertLike(likeModel);
                     }
                     finish();
                 }else {
                     new AlertDialog.Builder(this)
                             .setTitle("You Dont Have Account !")
                             .setMessage("Do You Login in Account | Register Account ?")
                             .setPositiveButton("Regester", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialog, int which) {
                                     Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                                     startActivity(intent);
                                 }
                             })
                             .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialog, int which) {
                                 }
                             })
                             .show();
                 }

             });



        }else if (typeFood.equals("Fruites")){
            FruitsModel fastFoodModel=dataBase.getFruiteDataBase().get(index);
            binding.likeItemImg.setOnClickListener(v -> {
                onClick++;
                if (onClick%2!=0) {
                    binding.likeItemImg.setImageResource(R.drawable.lovenotemptyfoodicon);
                    isLike=true;
                } else if (onClick%2==0) {
                    binding.likeItemImg.setImageResource(R.drawable.loveemptyfoodicon);
                    isLike=false;
                }
            });
            binding.counterTv.setText(String.valueOf(plus));
            totalPrice=fastFoodModel.getPrice();
            binding.plusImg.setOnClickListener(v -> {
                plus++;
                totalPrice=totalPrice+fastFoodModel.getPrice();
                binding.counterTv.setText(String.valueOf(plus));
                binding.totalPriceTv.setText(String.valueOf(totalPrice));
            });
            binding.minasImg.setOnClickListener(v -> {
                if (plus>1) {
                    plus = plus - 1;
                    binding.counterTv.setText(String.valueOf(plus));
                    totalPrice-=fastFoodModel.getPrice();
                    binding.totalPriceTv.setText(String.valueOf(totalPrice));
                }else {
                    Toast.makeText(this, "The Quantity Equal 1", Toast.LENGTH_SHORT).show();
                }
            });

            plus=fastFoodModel.getQoantity();

            binding.nameTv.setText(fastFoodModel.getName());
            binding.detilsTv.setText(fastFoodModel.getDetails());
            binding.totalPriceTv.setText(String.valueOf(totalPrice));

            if (fastFoodModel.getTotalPrice()/fastFoodModel.getPrice()==2 && fastFoodModel.getQoantity()==1){
                fastFoodModel.setQoantity(2);
                binding.counterTv.setText(String.valueOf(fastFoodModel.getQoantity()));
            }

            byte [] ph=dataBase.getFruiteDataBase().get(index).getImage();
            Bitmap bitmap=getImage(ph);
            binding.imageViewItem.setImageBitmap(bitmap);

            binding.goToCartBt.setOnClickListener(v -> {
                if (isLogin) {
                    String name = fastFoodModel.getName();
                    String det = fastFoodModel.getDetails();
                    int qua = plus;
                    int id = fastFoodModel.getId();
                    byte[] image = fastFoodModel.getImage();
                    CartModel cartModel = new CartModel(name, fastFoodModel.getPrice(), totalPrice, qua, det, image);
                    cartModel.setId(id);
                    cartModel.setUserId(idUser);
                    dataBase.insertCart(cartModel);
                    if (isLike) {
                        LikeModel likeModel = new LikeModel(name, fastFoodModel.getPrice(), det, image);
                        likeModel.setId(id);
                        dataBase.insertLike(likeModel);
                    }
                    finish();
                }else {
                    new AlertDialog.Builder(this)
                            .setTitle("You Dont Have Account !")
                            .setMessage("Do You Login in Account | Register Account ?")
                            .setPositiveButton("Regester", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .show();
                }

            });

        }else if (typeFood.equals("Drinks")){
            DrinkModel fastFoodModel=dataBase.getDrinksDataBase().get(index);
            binding.likeItemImg.setOnClickListener(v -> {
                onClick++;
                if (onClick%2!=0) {
                    binding.likeItemImg.setImageResource(R.drawable.lovenotemptyfoodicon);
                    isLike=true;
                } else if (onClick%2==0) {
                    binding.likeItemImg.setImageResource(R.drawable.loveemptyfoodicon);
                    isLike=false;
                }
            });
            binding.counterTv.setText(String.valueOf(plus));
            totalPrice=fastFoodModel.getPrice();
            fastFoodModel.setTotalPrice(fastFoodModel.getPrice());
            binding.plusImg.setOnClickListener(v -> {
                totalPrice=totalPrice+fastFoodModel.getPrice();
                if (fastFoodModel.getQoantity()==1){
                    fastFoodModel.setQoantity(2);
                    binding.counterTv.setText(String.valueOf(fastFoodModel.getQoantity()));
                }
                fastFoodModel.setTotalPrice(fastFoodModel.getTotalPrice()+fastFoodModel.getPrice());
                fastFoodModel.setQoantity(fastFoodModel.getQoantity()+1);
                binding.counterTv.setText(String.valueOf(fastFoodModel.getQoantity()));
                binding.totalPriceTv.setText(String.valueOf(fastFoodModel.getTotalPrice()));
            });
            binding.minasImg.setOnClickListener(v -> {
                if (plus>1) {
                    plus = plus - 1;
                    binding.counterTv.setText(String.valueOf(plus));
                    totalPrice-=fastFoodModel.getPrice();
                    binding.totalPriceTv.setText(String.valueOf(totalPrice));
                }else {
                    Toast.makeText(this, "The Quantity Equal 1", Toast.LENGTH_SHORT).show();
                }
            });

            plus=fastFoodModel.getQoantity();

            if (fastFoodModel.getTotalPrice()/fastFoodModel.getPrice()==2 && fastFoodModel.getQoantity()==1){
                fastFoodModel.setQoantity(2);
                binding.counterTv.setText(String.valueOf(fastFoodModel.getQoantity()));
            }

            binding.nameTv.setText(fastFoodModel.getName());
            binding.detilsTv.setText(fastFoodModel.getDetails());
            binding.totalPriceTv.setText(String.valueOf(totalPrice));

            byte [] ph=dataBase.getDrinksDataBase().get(index).getImage();
            Bitmap bitmap=getImage(ph);
            binding.imageViewItem.setImageBitmap(bitmap);

            binding.goToCartBt.setOnClickListener(v -> {
                if (isLogin) {
                    String name = fastFoodModel.getName();
                    String det = fastFoodModel.getDetails();
                    int qua = plus;
                    int id = fastFoodModel.getId();
                    byte[] image = fastFoodModel.getImage();
                    CartModel cartModel = new CartModel(name, fastFoodModel.getPrice(), totalPrice, qua, det, image);
                    cartModel.setId(id);
                    dataBase.insertCart(cartModel);
                    if (isLike) {
                        LikeModel likeModel = new LikeModel(name, fastFoodModel.getPrice(), det, image);
                        likeModel.setId(id);
                        cartModel.setUserId(idUser);
                        dataBase.insertLike(likeModel);
                    }
                    finish();
                }else {
                    new AlertDialog.Builder(this)
                            .setTitle("You Dont Have Account !")
                            .setMessage("Do You Login in Account | Register Account ?")
                            .setPositiveButton("Regester", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .show();
                }
            });
        }
    }
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}