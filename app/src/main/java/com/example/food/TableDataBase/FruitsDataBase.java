package com.example.food.TableDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.food.Models.FruitsModel;

import java.util.ArrayList;

public class FruitsDataBase extends DataBase {
    public FruitsDataBase(@Nullable Context context) {
        super(context);
    }

    public long insertFruits(FruitsModel fruitsModel){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBase.Fruite_Name,fruitsModel.getName());
        contentValues.put(DataBase.Fruite_Price,fruitsModel.getPrice());
        contentValues.put(DataBase.Fruite_ditales,fruitsModel.getDetails());
        contentValues.put(DataBase.Fruite_Img,fruitsModel.getImage());
        return database.insert(DataBase.Fruite_TABLE,null,contentValues);
    }

    public ArrayList<FruitsModel> getFruiteDataBase(){
        SQLiteDatabase database=getReadableDatabase();
        ArrayList<FruitsModel> fruitsModels=new ArrayList<>();
        Cursor cursor=database.rawQuery("Select * from "+DataBase.Fruite_TABLE,null);
        while (cursor.moveToNext()){
            String id=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Fruite_Id));
            String name=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Fruite_Name));
            String price=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Fruite_Price));
            String detels=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Fruite_ditales));
            byte[] image=cursor.getBlob(cursor.getColumnIndexOrThrow(DataBase.Fruite_Img));
            double prices=Double.parseDouble(price);
//            FruitsModel fruitsModel=new FruitsModel(name,prices,detels,image);
//            fruitsModel.setId(Integer.parseInt(id));
//            fruitsModels.add(fruitsModel);
        }
        return fruitsModels;
    }

    public int deleteFruitsDataBase(String id){
        SQLiteDatabase database=getWritableDatabase();
        return database.delete(DataBase.Fruite_TABLE,DataBase.Fruite_Id+" =? ",new String[]{id});
    }

}
