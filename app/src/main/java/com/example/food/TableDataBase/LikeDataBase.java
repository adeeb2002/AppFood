package com.example.food.TableDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.food.Models.LikeModel;

import java.util.ArrayList;

public class LikeDataBase extends DataBase {
    public LikeDataBase(@Nullable Context context) {
        super(context);
    }


    public long insertLike(LikeModel likeModel){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBase.Like_Name,likeModel.getName());
        contentValues.put(DataBase.Like_Price,likeModel.getPrice());
        contentValues.put(DataBase.Like_Detels,likeModel.getDetails());
        contentValues.put(DataBase.Like_Img,likeModel.getImage());
        return database.insert(DataBase.Like_TABLE,null,contentValues);
    }

    public ArrayList<LikeModel> getLikeDataBase(){
        SQLiteDatabase database=getReadableDatabase();
        ArrayList<LikeModel> likeModels=new ArrayList<>();
        Cursor cursor=database.rawQuery("Select * from "+DataBase.Like_TABLE,null);
        while (cursor.moveToNext()){
            String id=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Like_Id));
            String name=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Like_Name));
            String price=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Like_Price));
            String detels=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Like_Detels));
            byte[] image =cursor.getBlob(cursor.getColumnIndexOrThrow(DataBase.Like_Img));
            double prices=Double.parseDouble(price);
            LikeModel likeModel=new LikeModel(name,prices,detels,image);
            likeModel.setId(Integer.parseInt(id));
            likeModels.add(likeModel);
        }
        return likeModels;
    }

    public int deleteLikeDataBase(String id){
        SQLiteDatabase database=getWritableDatabase();
        return database.delete(DataBase.Like_TABLE,DataBase.Like_Id+" =? ",new String[]{id});
    }

}
