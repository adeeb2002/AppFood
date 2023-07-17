package com.example.food.TableDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.food.Models.DrinkModel;

import java.util.ArrayList;

public class DrinksDataBase extends DataBase {

    public DrinksDataBase(@Nullable Context context) {
        super(context);
    }

    public long insertDrinks(DrinkModel drinkModel){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBase.Drinks_Name,drinkModel.getName());
        contentValues.put(DataBase.Drinks_Price,drinkModel.getPrice());
        contentValues.put(DataBase.Drinks_ditales,drinkModel.getDetails());
        contentValues.put(DataBase.Drinks_Img,drinkModel.getImage());
        return database.insert(DataBase.Drinks_TABLE,null,contentValues);
    }

    public ArrayList<DrinkModel> getDrinksDataBase(){
        SQLiteDatabase database=getReadableDatabase();
        ArrayList<DrinkModel> drinkModels=new ArrayList<>();
        Cursor cursor=database.rawQuery("Select * from "+DataBase.Drinks_TABLE,null);
        while (cursor.moveToNext()){
            String id=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Drinks_Id));
            String name=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Drinks_Name));
            String price=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Drinks_Price));
            String detels=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Drinks_ditales));
            byte[] image=cursor.getBlob(cursor.getColumnIndexOrThrow(DataBase.Drinks_Img));
            double prices=Double.parseDouble(price);
//            DrinkModel drinkModel=new DrinkModel(name,prices,detels,image);
//            drinkModel.setId(Integer.parseInt(id));
//            drinkModels.add(drinkModel);
        }
        return drinkModels;
    }

    public int deleteDrinksDataBase(String id){
        SQLiteDatabase database=getWritableDatabase();
        return database.delete(DataBase.Drinks_TABLE,DataBase.Drinks_Id+" =? ",new String[]{id});
    }

}
