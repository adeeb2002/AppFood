package com.example.food.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.food.Models.DrinkModel;
import com.example.food.Models.FastFoodModel;
import com.example.food.Models.FruitsModel;
import com.example.food.Models.Restoraunt;
import com.example.food.TableDataBase.DataBase;
import com.example.food.databinding.ActivityAddItemBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AddItem extends AppCompatActivity {

    ActivityAddItemBinding binding;
    DataBase dataBase=new DataBase(this);
    Uri uri=null;
    InputStream iStream = null;
    byte[] inputData;
    String type=null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         uri=data.getData();
        //binding.imageView.setImageURI(uri);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        type=getIntent().getStringExtra("type");
        if (type.equals("FastFood")) {

            binding.addImageBt.setOnClickListener(v -> {

                ImagePicker.with(this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            });



//            binding.viewImageBt.setOnClickListener(v -> {
//                byte [] ph=dataBase.getFastFoodDataBase().get(0).getImage();
//                Bitmap bitmap=getImage(ph);
//                binding.imageView.setImageBitmap(bitmap);
//            });


            binding.addItem.setOnClickListener(v -> {
                String name = binding.nameItem.getText().toString().trim();
                String detilse = binding.detilsItem.getText().toString().trim();
                String priceTx = binding.priceItem.getText().toString().trim();
                double price = Double.parseDouble(priceTx);

                try {
                    iStream = getContentResolver().openInputStream(uri);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                try {
                    inputData = getBytes(iStream);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                dataBase.insertFastFood(new FastFoodModel(name, price,price, detilse, inputData));
                finish();
            });
        } else if (type.equals("Fruite")) {
            binding.addImageBt.setOnClickListener(v -> {

                ImagePicker.with(this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            });


            binding.addItem.setOnClickListener(v -> {
                String name = binding.nameItem.getText().toString().trim();
                String detilse = binding.detilsItem.getText().toString().trim();
                String priceTx = binding.priceItem.getText().toString().trim();
                double price = Double.parseDouble(priceTx);

                try {
                    iStream = getContentResolver().openInputStream(uri);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                try {
                    inputData = getBytes(iStream);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                dataBase.insertFruits(new FruitsModel(name, price,price, detilse, inputData));
                finish();
            });
        } else if (type.equals("Drink")) {
            binding.addImageBt.setOnClickListener(v -> {

                ImagePicker.with(this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            });


            binding.addItem.setOnClickListener(v -> {
                String name = binding.nameItem.getText().toString().trim();
                String detilse = binding.detilsItem.getText().toString().trim();
                String priceTx = binding.priceItem.getText().toString().trim();
                double price = Double.parseDouble(priceTx);


                try {
                    iStream = getContentResolver().openInputStream(uri);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                try {
                    inputData = getBytes(iStream);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                dataBase.insertDrinks(new DrinkModel(name, price,price, detilse, inputData));
                finish();
            });
        } else if (type.equals("Restorant")) {
            binding.addImageBt.setOnClickListener(v -> {

                ImagePicker.with(this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            });

            binding.addItem.setOnClickListener(v -> {
                String name = binding.nameItem.getText().toString().trim();
                String detilse = binding.detilsItem.getText().toString().trim();
                binding.priceItem.setVisibility(View.GONE);


                try {
                    iStream = getContentResolver().openInputStream(uri);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                try {
                    inputData = getBytes(iStream);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                dataBase.insertRestoraunt(new Restoraunt(name,detilse, inputData));
                finish();
            });

        }

    }

    private byte[] getBytes(InputStream iStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = iStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }


    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}