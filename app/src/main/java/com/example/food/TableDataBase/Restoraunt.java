package com.example.food.TableDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Restoraunt extends DataBase {
    public Restoraunt(@Nullable Context context) {
        super(context);
    }

    public long insertRestoraunt(com.example.food.Models.Restoraunt restoraunt){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBase.Restaurant_Name,restoraunt.getName());
        contentValues.put(DataBase.Restaurant_ditales,restoraunt.getDetails());
        contentValues.put(DataBase.Restaurant_Img,restoraunt.getImage());
        return database.insert(DataBase.Restaurant_TABLE,null,contentValues);
    }

    public ArrayList<com.example.food.Models.Restoraunt> getRestorauntDataBase(){
        SQLiteDatabase database=getReadableDatabase();
        ArrayList<com.example.food.Models.Restoraunt> restoraunts=new ArrayList<>();
        Cursor cursor=database.rawQuery("Select * from "+DataBase.Restaurant_TABLE,null);
        while (cursor.moveToNext()){
            String id=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Restaurant_Id));
            String name=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Restaurant_Name));
            String detels=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Restaurant_ditales));
            byte[] image=cursor.getBlob(cursor.getColumnIndexOrThrow(DataBase.Restaurant_Img));
            com.example.food.Models.Restoraunt likeModel=new com.example.food.Models.Restoraunt(name,detels,image);
            likeModel.setId(Integer.parseInt(id));
            restoraunts.add(likeModel);
        }
        return restoraunts;
    }

    public int deleteRestorauntDataBase(String id){
        SQLiteDatabase database=getWritableDatabase();
        return database.delete(DataBase.Restaurant_TABLE,DataBase.Restaurant_Id+" =? ",new String[]{id});
    }

}
