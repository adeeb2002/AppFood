package com.example.food.TableDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.food.Models.FastFoodModel;

import java.util.ArrayList;

public class FastFoodDataBase extends DataBase {


    public FastFoodDataBase(@Nullable Context context) {
        super(context);
    }

    public long insertFastFood(FastFoodModel fastFoodModel){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBase.FAST_FOOD_Name,fastFoodModel.getName());
        contentValues.put(DataBase.FAST_FOOD_Price,fastFoodModel.getPrice());
        contentValues.put(DataBase.FAST_FOOD_ditales,fastFoodModel.getDetails());
        contentValues.put(DataBase.FAST_FOOD_Img,fastFoodModel.getImage());
        return database.insert(DataBase.FAST_FOOD_TABLE,null,contentValues);
    }

    public ArrayList<FastFoodModel> getFastFoodDataBase(){
        SQLiteDatabase database=getReadableDatabase();
        ArrayList<FastFoodModel> fastFoodModels=new ArrayList<>();
        Cursor cursor=database.rawQuery("Select * from "+DataBase.FAST_FOOD_TABLE,null);
        while (cursor.moveToNext()){
            String id=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.FAST_FOOD_Id));
            String name=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.FAST_FOOD_Name));
            String price=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.FAST_FOOD_Price));
            String detels=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.FAST_FOOD_ditales));
            byte[] image=cursor.getBlob(cursor.getColumnIndexOrThrow(DataBase.FAST_FOOD_Img));
            double prices=Double.parseDouble(price);
//            FastFoodModel fastFoodModel=new FastFoodModel(name,prices,detels,image);
//            fastFoodModel.setId(Integer.parseInt(id));
//            fastFoodModels.add(fastFoodModel);
        }
        return fastFoodModels;
    }

    public int deleteFastFoodDataBase(String id){
        SQLiteDatabase database=getWritableDatabase();
        return database.delete(DataBase.FAST_FOOD_TABLE,DataBase.FAST_FOOD_Id+" =? ",new String[]{id});
    }
}
