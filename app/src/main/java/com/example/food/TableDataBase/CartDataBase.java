package com.example.food.TableDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.food.Models.CartModel;

import java.util.ArrayList;

public class CartDataBase extends DataBase {
    public CartDataBase(@Nullable Context context) {
        super(context);
    }

    public long insertCart(CartModel cartModel){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBase.FAST_FOOD_Name,cartModel.getName());
        contentValues.put(DataBase.FAST_FOOD_Price,cartModel.getPrice());
        contentValues.put(DataBase.FAST_FOOD_ditales,cartModel.getDetails());
        contentValues.put(DataBase.Cart_Img,cartModel.getImage());
        return database.insert(DataBase.FAST_FOOD_TABLE,null,contentValues);
    }

    public ArrayList<CartModel> getCartDataBase(){
        SQLiteDatabase database=getReadableDatabase();
        ArrayList<CartModel> cartModels=new ArrayList<>();
        Cursor cursor=database.rawQuery("Select * from "+DataBase.Cart_TABLE,null);
        while (cursor.moveToNext()){
            String id=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Cart_Id));
            String name=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Cart_Name));
            String price=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Cart_Price));
            String detels=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Cart_ditales));
            byte[] image=cursor.getBlob(cursor.getColumnIndexOrThrow(DataBase.Cart_Img));
            double prices=Double.parseDouble(price);
            //CartModel fastFoodModel=new CartModel(name,prices,detels,image);
            //tModels.add(fastFoodModel);
        }
        return cartModels;
    }

    public int deleteCartDataBase(String id){
        SQLiteDatabase database=getWritableDatabase();
        return database.delete(DataBase.Cart_TABLE,DataBase.Cart_Id+" =? ",new String[]{id});
    }

}
