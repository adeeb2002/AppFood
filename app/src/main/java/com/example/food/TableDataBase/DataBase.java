package com.example.food.TableDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.food.Models.CartModel;
import com.example.food.Models.DrinkModel;
import com.example.food.Models.FastFoodModel;
import com.example.food.Models.FruitsModel;
import com.example.food.Models.LikeModel;
import com.example.food.Models.Restoraunt;
import com.example.food.Models.UserModel;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    public static final String NAME_DATA_BASE="FoodApp";


    //  Table Fast Food

    public static final String FAST_FOOD_TABLE="FastFoodTable";
    public static final String FAST_FOOD_Id="FAST_FOOD_Id";
    public static final String FAST_FOOD_Name="FAST_FOOD_Name";
    public static final String FAST_FOOD_Price="FAST_FOOD_Price";
    public static final String FAST_FOOD_Total_Price="FAST_FOOD_Total_Price";
    public static final String FAST_FOOD_ditales="FAST_FOOD_ditales";
    public static final String FAST_FOOD_IsLike="FAST_FOOD_IsLike";
    public static final String FAST_FOOD_Img="FAST_FOOD_Img";


    //  Table Fruite

    public static final String Fruite_TABLE="FruiteTable";
    public static final String Fruite_Id="FruiteId";
    public static final String Fruite_Name="FruiteName";
    public static final String Fruite_Price="FruitePrice";
    public static final String Fruite_Total_Price="FruiteTotalPrice";
    public static final String Fruite_ditales="FruiteDitales";
    public static final String Fruite_IsLike="FruiteIsLike";
    public static final String Fruite_Img="FruiteImg";

    //  Table Drinks

    public static final String Drinks_TABLE="DrinkTable";
    public static final String Drinks_Id="DrinkId";
    public static final String Drinks_Name="DrinkName";
    public static final String Drinks_Price="DrinkPrice";
    public static final String Drinks_Total_Price="DrinkTotalPrice";
    public static final String Drinks_ditales="DrinkDitales";
    public static final String Drinks_IsLike="DrinkIsLIke";
    public static final String Drinks_Img="DrinkImg";

    //  Table Restaurants

    public static final String Restaurant_TABLE="RestaurantTable";
    public static final String Restaurant_Id="RestaurantId";
    public static final String Restaurant_Name="RestaurantName";
    public static final String Restaurant_ditales="RestaurantDitales";
    public static final String Restaurant_Img="RestaurantImg";

    //  Table Like

    public static final String Like_TABLE="LikeTable";
    public static final String Like_Id="LikeId";
    public static final String Like_Name="LikeName";
    public static final String Like_Price="LikePrice";
    public static final String Like_Detels="LikeDetels";
    public static final String Like_Img="LikeImg";

    //  Table Cart

    public static final String Cart_TABLE="CartTable";
    public static final String Cart_Number="CartNumber";
    public static final String Cart_Id="CartId";
    public static final String Cart_Name="CartName";
    public static final String Cart_Price="CartPrice";
    public static final String Cart_Total_Price="CartTotalPrice";
    public static final String Cart_ditales="CartDitales";
    public static final String Cart_Quantity="CartQuantity";
    public static final String Cart_Img="CartImg";
    public static final String Cart_User_ID="CartUserId";

    // Table User

    public static final String User_TABLE="UserTable";
    public static final String User_Id="UserId";
    public static final String User_UserName="UserName";
    public static final String User_Password="UserPassword";
    public static final String User_Email="UserEmail";
    public static final String User_Phone="UserPhone";
    public static final String User_Country="UserCountry";
    public static final String User_Image="UserImage";


    public DataBase(@Nullable Context context) {
        super(context, NAME_DATA_BASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+FAST_FOOD_TABLE+" ( "+FAST_FOOD_Id+" Integer primary key autoincrement , "+FAST_FOOD_Name+" text unique , "+FAST_FOOD_Price+" DECIMAL(7,2), "+FAST_FOOD_Total_Price+" DECIMAL(8,2) , "+FAST_FOOD_ditales+" text ,"+FAST_FOOD_IsLike+" BOOLEAN , "+FAST_FOOD_Img+" blob  ) ");
        db.execSQL("create table "+Fruite_TABLE+" ( "+Fruite_Id+" Integer primary key autoincrement , "+Fruite_Name+" text unique , "+Fruite_Price+" DECIMAL(7,2) , "+Fruite_Total_Price+" DECIMAL(8,2) , "+Fruite_ditales+" text ,"+Fruite_IsLike+" BOOLEAN, "+Fruite_Img+" blob  ) ");
        db.execSQL("create table "+Drinks_TABLE+" ( "+Drinks_Id+" Integer primary key autoincrement , "+Drinks_Name+" text unique , "+Drinks_Price+" DECIMAL(7,2) , "+Drinks_Total_Price+" DECIMAL(8,2) , "+Drinks_ditales+" text ,"+Drinks_IsLike+" BOOLEAN, "+Drinks_Img+" blob  ) ");
        db.execSQL("create table "+Like_TABLE+" ( "+Like_Id+" Integer primary key autoincrement , "+Like_Name+" text unique , "+Like_Price+" DECIMAL(7,2) , "+Like_Detels+" text , "+Like_Img+" blob  ) ");
        db.execSQL("create table "+Cart_TABLE+" ( "+Cart_Id+" Integer primary key autoincrement , "+Cart_Number+" Integer , "+Cart_Name+" text , "+Cart_Price+" DECIMAL(7,2) ,"+Cart_Total_Price+" DECIMAL(7,2) , "+Cart_ditales+" text ,"+Cart_Quantity+" DECIMAL(8,2), "+Cart_Img+" blob ,  "+Cart_User_ID+" Integer REFERENCES "+User_TABLE+" ("+User_Id+") ) ");
        db.execSQL("create table "+Restaurant_TABLE+" ( "+Restaurant_Id+" Integer primary key autoincrement , "+Restaurant_Name+" text unique ,  "+Restaurant_ditales+" text , "+Restaurant_Img+" blob  ) ");
        db.execSQL("create table "+User_TABLE+" ( "+User_Id+" Integer primary key autoincrement , "+User_UserName+" text unique , "+User_Password+" text ,"+User_Email+" text unique , "+User_Phone+" text unique , "+User_Country+" text , "+User_Image+" blob ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table "+FAST_FOOD_TABLE);
        db.execSQL("drop table "+Fruite_TABLE);
        db.execSQL("drop table "+Drinks_TABLE);
        db.execSQL("drop table "+Like_TABLE);
        db.execSQL("drop table "+Cart_TABLE);
        db.execSQL("drop table "+Restaurant_TABLE);
        db.execSQL("drop table "+User_TABLE);
        onCreate(db);
    }


    //  FastFood

    public long insertFastFood(FastFoodModel fastFoodModel){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBase.FAST_FOOD_Name,fastFoodModel.getName());
        contentValues.put(DataBase.FAST_FOOD_Price,fastFoodModel.getPrice());
        contentValues.put(DataBase.FAST_FOOD_Total_Price,fastFoodModel.getTotalPrice());
        contentValues.put(DataBase.FAST_FOOD_ditales,fastFoodModel.getDetails());
        contentValues.put(DataBase.FAST_FOOD_Img,fastFoodModel.getImage());
        return database.insert(DataBase.FAST_FOOD_TABLE,null,contentValues);
    }

    public ArrayList<FastFoodModel> getFastFoodDataBase(){
        SQLiteDatabase database=getReadableDatabase();
        ArrayList<FastFoodModel> fastFoodModels=new ArrayList<>();
        Cursor cursor=database.rawQuery("Select * from "+DataBase.FAST_FOOD_TABLE,null);
        while (cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndexOrThrow(DataBase.FAST_FOOD_Id));
            String name=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.FAST_FOOD_Name));
            String price=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.FAST_FOOD_Price));
            String totalPrice=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.FAST_FOOD_Total_Price));
            String detels=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.FAST_FOOD_ditales));
            String isLike=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.FAST_FOOD_IsLike));
            byte[] image=cursor.getBlob(cursor.getColumnIndexOrThrow(DataBase.FAST_FOOD_Img));
            double prices=Double.parseDouble(price);
            double totalPrices=Double.parseDouble(totalPrice);
            FastFoodModel fastFoodModel=new FastFoodModel(name,prices,totalPrices,detels,image);
            fastFoodModel.setId(id);
            fastFoodModel.setLike(Boolean.parseBoolean(isLike));
            fastFoodModels.add(fastFoodModel);
        }
        return fastFoodModels;
    }

    public long updateFastFoodDataBase(FastFoodModel fastFoodModel){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(FAST_FOOD_Name,fastFoodModel.getName());
        contentValues.put(FAST_FOOD_ditales,fastFoodModel.getDetails());
        contentValues.put(FAST_FOOD_Price,fastFoodModel.getPrice());
        contentValues.put(FAST_FOOD_Total_Price,fastFoodModel.getTotalPrice());
        contentValues.put(FAST_FOOD_Img,fastFoodModel.getImage());
        return database.update(FAST_FOOD_TABLE,contentValues,FAST_FOOD_Id+" =? ",new String[]{String.valueOf(fastFoodModel.getId())});
    }

    public int deleteFastFoodDataBase(String id){
        SQLiteDatabase database=getWritableDatabase();
        return database.delete(DataBase.FAST_FOOD_TABLE,DataBase.FAST_FOOD_Id+" =? ",new String[]{id});
    }


    //  Cart

    public long insertCart(CartModel cartModel){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBase.Cart_Number,cartModel.getNumberCart());
        contentValues.put(DataBase.Cart_Name,cartModel.getName());
        contentValues.put(DataBase.Cart_Price,cartModel.getPrice());
        contentValues.put(DataBase.Cart_Total_Price,cartModel.getTotalPrice());
        contentValues.put(DataBase.Cart_ditales,cartModel.getDetails());
        contentValues.put(DataBase.Cart_Quantity,cartModel.getQoantity());
        contentValues.put(DataBase.Cart_Img,cartModel.getImage());
        contentValues.put(DataBase.Cart_User_ID,cartModel.getUserId());
        return database.insert(DataBase.Cart_TABLE,null,contentValues);
    }

    public ArrayList<CartModel> getCartDataBase(){
        SQLiteDatabase database=getReadableDatabase();
        ArrayList<CartModel> cartModels=new ArrayList<>();
        Cursor cursor=database.rawQuery("Select * from "+DataBase.Cart_TABLE,null);
        while (cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndexOrThrow(DataBase.Cart_Id));
            int numberCart=cursor.getInt(cursor.getColumnIndexOrThrow(DataBase.Cart_Number));
            String name=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Cart_Name));
            String price=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Cart_Price));
            String TotalPrice=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Cart_Total_Price));
            String detels=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Cart_ditales));
            int quantity=cursor.getInt(cursor.getColumnIndexOrThrow(DataBase.Cart_Quantity));
            byte[] image=cursor.getBlob(cursor.getColumnIndexOrThrow(DataBase.Cart_Img));
            int idUser=cursor.getInt(cursor.getColumnIndexOrThrow(DataBase.Cart_User_ID));
            double prices=Double.parseDouble(price);
            double totalPrices=Double.parseDouble(TotalPrice);
            CartModel fastFoodModel=new CartModel(name,prices,totalPrices,quantity,detels,image);
            fastFoodModel.setId(id);
            fastFoodModel.setUserId(idUser);
            fastFoodModel.setNumberCart(id);
            cartModels.add(fastFoodModel);
        }
        return cartModels;
    }

    public long updateCartDataBase(CartModel fastFoodModel){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Cart_Name,fastFoodModel.getName());
        contentValues.put(Cart_ditales,fastFoodModel.getDetails());
        contentValues.put(Cart_Price,fastFoodModel.getPrice());
        contentValues.put(Cart_Total_Price,fastFoodModel.getTotalPrice());
        contentValues.put(Cart_Quantity,fastFoodModel.getQoantity());
        contentValues.put(Cart_Img,fastFoodModel.getImage());
        contentValues.put(Cart_User_ID,fastFoodModel.getUserId());
        return database.update(Cart_TABLE,contentValues,Cart_Id+" =? ",new String[]{String.valueOf(fastFoodModel.getId())});
    }

    public int deleteCartDataBase(String id){
        SQLiteDatabase database=getWritableDatabase();
        return database.delete(Cart_TABLE,DataBase.Cart_Id+" = ? ",new String[]{id});
    }


    //  Drinks


    public long insertDrinks(DrinkModel drinkModel){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBase.Drinks_Name,drinkModel.getName());
        contentValues.put(DataBase.Drinks_Price,drinkModel.getPrice());
        contentValues.put(DataBase.Drinks_Total_Price,drinkModel.getTotalPrice());
        contentValues.put(DataBase.Drinks_ditales,drinkModel.getDetails());
        contentValues.put(DataBase.Drinks_Img,drinkModel.getImage());
        contentValues.put(DataBase.Drinks_IsLike,drinkModel.isLike());
        return database.insert(DataBase.Drinks_TABLE,null,contentValues);
    }

    public ArrayList<DrinkModel> getDrinksDataBase(){
        SQLiteDatabase database=getReadableDatabase();
        ArrayList<DrinkModel> drinkModels=new ArrayList<>();
        Cursor cursor=database.rawQuery("Select * from "+DataBase.Drinks_TABLE,null);
        while (cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndexOrThrow(DataBase.Drinks_Id));
            String name=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Drinks_Name));
            String price=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Drinks_Price));
            String totalPrice=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Drinks_Total_Price));
            String detels=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Drinks_ditales));
            String isLike=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Drinks_IsLike));
            byte[] image=cursor.getBlob(cursor.getColumnIndexOrThrow(DataBase.Drinks_Img));
            double prices=Double.parseDouble(price);
            double totalPrices=Double.parseDouble(totalPrice);
            DrinkModel drinkModel=new DrinkModel(name,prices,totalPrices,detels,image);
            drinkModel.setId(id);
            drinkModel.setLike(Boolean.parseBoolean(isLike));
            drinkModels.add(drinkModel);
        }
        return drinkModels;
    }

    public long updateDrinkDataBase(DrinkModel fastFoodModel){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Drinks_Name,fastFoodModel.getName());
        contentValues.put(Drinks_ditales,fastFoodModel.getDetails());
        contentValues.put(Drinks_Price,fastFoodModel.getPrice());
        contentValues.put(Drinks_Total_Price,fastFoodModel.getTotalPrice());
        contentValues.put(Drinks_Img,fastFoodModel.getImage());
        return database.update(Drinks_TABLE,contentValues,Drinks_Id+" =? ",new String[]{String.valueOf(fastFoodModel.getId())});
    }

    public int deleteDrinksDataBase(String id){
        SQLiteDatabase database=getWritableDatabase();
        return database.delete(DataBase.Drinks_TABLE,DataBase.Drinks_Id+" =? ",new String[]{id});
    }


    //  Fruits

    public long insertFruits(FruitsModel fruitsModel){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBase.Fruite_Name,fruitsModel.getName());
        contentValues.put(DataBase.Fruite_Price,fruitsModel.getPrice());
        contentValues.put(DataBase.Fruite_Total_Price,fruitsModel.getTotalPrice());
        contentValues.put(DataBase.Fruite_ditales,fruitsModel.getDetails());
        contentValues.put(DataBase.Fruite_Img,fruitsModel.getImage());
        contentValues.put(DataBase.Fruite_IsLike,fruitsModel.isLike());
        return database.insert(DataBase.Fruite_TABLE,null,contentValues);
    }

    public ArrayList<FruitsModel> getFruiteDataBase(){
        SQLiteDatabase database=getReadableDatabase();
        ArrayList<FruitsModel> fruitsModels=new ArrayList<>();
        Cursor cursor=database.rawQuery("Select * from "+DataBase.Fruite_TABLE,null);
        while (cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndexOrThrow(DataBase.Fruite_Id));
            String name=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Fruite_Name));
            String price=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Fruite_Price));
            String totalPrice=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Fruite_Total_Price));
            String detels=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Fruite_ditales));
            String isLike=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Fruite_IsLike));
            byte[] image=cursor.getBlob(cursor.getColumnIndexOrThrow(DataBase.Fruite_Img));
            double prices=Double.parseDouble(price);
            double totalPrices=Double.parseDouble(price);
            FruitsModel fruitsModel=new FruitsModel(name,prices,totalPrices,detels,image);
            fruitsModel.setId(id);
            fruitsModel.setLike(Boolean.parseBoolean(isLike));
            fruitsModels.add(fruitsModel);
        }
        return fruitsModels;
    }

    public long updateFruitsDataBase(FruitsModel fastFoodModel){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Fruite_Name,fastFoodModel.getName());
        contentValues.put(Fruite_ditales,fastFoodModel.getDetails());
        contentValues.put(Fruite_Price,fastFoodModel.getPrice());
        contentValues.put(Fruite_Total_Price,fastFoodModel.getTotalPrice());
        contentValues.put(Fruite_Img,fastFoodModel.getImage());
        return database.update(Fruite_TABLE,contentValues,Fruite_Id+" =? ",new String[]{String.valueOf(fastFoodModel.getId())});
    }

    public int deleteFruitsDataBase(String id){
        SQLiteDatabase database=getWritableDatabase();
        return database.delete(DataBase.Fruite_TABLE,DataBase.Fruite_Id+" =? ",new String[]{id});
    }


    //  Like


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
            int id=cursor.getInt(cursor.getColumnIndexOrThrow(DataBase.Like_Id));
            String name=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Like_Name));
            String price=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Like_Price));
            String detels=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Like_Detels));
            byte[] image =cursor.getBlob(cursor.getColumnIndexOrThrow(DataBase.Like_Img));
            double prices=Double.parseDouble(price);
            LikeModel likeModel=new LikeModel(name,prices,detels,image);
            likeModel.setId(id);
            likeModels.add(likeModel);
        }
        return likeModels;
    }

    public int deleteLikeDataBase(String id){
        SQLiteDatabase database=getWritableDatabase();
        return database.delete(DataBase.Like_TABLE,DataBase.Like_Id+" =? ",new String[]{id});
    }


    // Restoraunt

    public long insertRestoraunt(com.example.food.Models.Restoraunt restoraunt){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBase.Restaurant_Name,restoraunt.getName());
        contentValues.put(DataBase.Restaurant_ditales,restoraunt.getDetails());
        contentValues.put(DataBase.Restaurant_Img,restoraunt.getImage());
        return database.insert(DataBase.Restaurant_TABLE,null,contentValues);
    }

    public ArrayList<Restoraunt> getRestorauntDataBase(){
        SQLiteDatabase database=getReadableDatabase();
        ArrayList<Restoraunt> restoraunts=new ArrayList<>();
        Cursor cursor=database.rawQuery("select * from "+Restaurant_TABLE,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(Restaurant_Id));
            String name=cursor.getString(cursor.getColumnIndexOrThrow(Restaurant_Name));
            String details=cursor.getString(cursor.getColumnIndexOrThrow(Restaurant_ditales));
            byte[] image=cursor.getBlob(cursor.getColumnIndexOrThrow(Restaurant_Img));
            Restoraunt restoraunt=new Restoraunt(name,details,image);
            restoraunt.setId(id);
            restoraunts.add(restoraunt);
        }
        return restoraunts;
    }

    public int deleteRestorauntDataBase(String id){
        SQLiteDatabase database=getWritableDatabase();
        return database.delete(DataBase.Restaurant_TABLE,DataBase.Restaurant_Id+" =? ",new String[]{id});
    }

    // User

    public long insertUser(UserModel userModel){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(User_UserName,userModel.getUserName());
        contentValues.put(User_Password,userModel.getPassowrd());
        return database.insert(User_TABLE,null,contentValues);
    }

    public long insertUserFull(UserModel userModel){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(User_UserName,userModel.getUserName());
        contentValues.put(User_Password,userModel.getPassowrd());
        contentValues.put(User_Email,userModel.getEmail());
        contentValues.put(User_Phone,userModel.getPhone());
        contentValues.put(User_Country,userModel.getCountry());
        contentValues.put(User_Image,userModel.getImageUser());
        return database.insert(User_TABLE,null,contentValues);
    }

    public ArrayList<UserModel> getUserInDataBase(){
        SQLiteDatabase database=getReadableDatabase();
        ArrayList<UserModel> userModels=new ArrayList<>();
        Cursor cursor=database.rawQuery("Select * from "+User_TABLE,null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow(User_Id));
            String userName = cursor.getString(cursor.getColumnIndexOrThrow(User_UserName));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(User_Password));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(User_Email));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(User_Phone));
            String country = cursor.getString(cursor.getColumnIndexOrThrow(User_Country));
            byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow(User_Image));
            UserModel userModel = new UserModel(userName, password,email,phone,country,image);
            userModel.setIdUser(Integer.parseInt(id));
            userModels.add(userModel);
        }
        return userModels;
    }

    public int deleteUserDataBase(String id){
        SQLiteDatabase database=getWritableDatabase();
        return database.delete(DataBase.User_TABLE,DataBase.User_Id+" =? ",new String[]{id});
    }
    public long updateUser(UserModel user){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(User_UserName,user.getUserName());
        contentValues.put(User_Password,user.getPassowrd());
        contentValues.put(User_Phone,user.getPhone());
        contentValues.put(User_Email,user.getEmail());
        contentValues.put(User_Country,user.getCountry());
        contentValues.put(User_Image,user.getImageUser());
        return database.update(User_TABLE,contentValues,User_Id+" = ? ",new String[]{String.valueOf(user.getIdUser())});
    }

}
