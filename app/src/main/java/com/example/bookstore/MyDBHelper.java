package com.example.bookstore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bookstore.Models.cartModel;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "BookStoreDB";
    public static final String TABLE_NAME = "CartData";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE2_NAME = "signUpData";

    public MyDBHelper(Context context ){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"(bookTitle TEXT primary key , bookAuthor TEXT, bookCost TEXT, quantity INTEGER, bookImage INTEGER) ");
        sqLiteDatabase.execSQL("create table "+TABLE2_NAME+"(name TEXT, email TEXT primary key, password TEXT)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME);
        sqLiteDatabase.execSQL("drop table if exists "+TABLE2_NAME);
    }



    public boolean insertData(String name, String email, String password){
        SQLiteDatabase myDatabase = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("email",email);
        cv.put("password",password);

        long result = myDatabase.insert(TABLE2_NAME,null,cv);

        if(result == -1){
            return false;
        }else {
            return true;
        }
    }

    public void addDataToCart(String title ,String author, String cost, int quantity , int image){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("bookTitle",title);
        cv.put("bookAuthor",author);
        cv.put("bookCost",cost);
        cv.put("quantity",quantity);
        cv.put("bookImage",image);



        db.insert(TABLE_NAME,null,cv);
    }

    public void removeFromCart(String title){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(TABLE_NAME,"bookTitle = "+ '"'+title+'"',null);
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
    }


    public ArrayList<cartModel> fetchCart(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);

        ArrayList<cartModel> cartArrayList = new ArrayList<>();
        while (cursor.moveToNext()){

            cartModel cm = new cartModel(cursor.getString(0),cursor.getString(1), cursor.getString(2),cursor.getInt(3),cursor.getInt(4) );
            cartArrayList.add(cm);
        }
        return cartArrayList;
    }


    public boolean checkEmail(String email){
        SQLiteDatabase myDatabase = this.getReadableDatabase();

        Cursor cursor = myDatabase.rawQuery("Select * from "+TABLE2_NAME+" where email = ?", new String[]{email});

        if(cursor.getCount() >0){
            return true;
        }else {
            return false;
        }


    }

    public boolean checkEmailPassword( String email, String password ){

        SQLiteDatabase myDatabase = this.getReadableDatabase();

        Cursor cursor = myDatabase.rawQuery("Select * from "+TABLE2_NAME+" where email = ? and password = ?",new String[]{email, password});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }




    public Cursor fetchname(String email){
        SQLiteDatabase myDatabase = this.getReadableDatabase();

        Cursor cursor = myDatabase.rawQuery("Select name, email from "+TABLE2_NAME+" where email = ?",new String[]{email});

        return cursor;

    }



}

