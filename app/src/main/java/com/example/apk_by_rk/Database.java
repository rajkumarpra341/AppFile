package com.example.apk_by_rk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
           String qry1 = " create table users(name text , phone text ,pass text) ";
           sqLiteDatabase.execSQL(qry1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void register(String name , String phone , String pass)
    {
        ContentValues cv = new ContentValues();
        cv.put("name" , name);
        cv.put("phone" ,phone);
        cv.put("pass" , pass);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null ,cv);
        db.close();
    }

    public int login(String phone , String pass)
    {
        int result = 0 ;
        String str[] = new String[2] ;
        str[0] = phone ;
        str[1] = pass ;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where phone = ? and pass = ?",str);
        if(c.moveToFirst())
        {
            result = 1 ;
        }
        return result;
    }

}
