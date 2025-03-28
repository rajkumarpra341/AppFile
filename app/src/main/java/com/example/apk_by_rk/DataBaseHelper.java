package com.example.apk_by_rk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String Database_name = " Student.db";
    public static final String Table_name = " Student_table";
    public static final String col_id = "Id";
    public static final String col_name = "name";
    public static final String col_marks = "marks";

    public DataBaseHelper(@Nullable Context context) {
        super(context, Database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("create table"+Table_name+ "(Id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT , Marks INTEGERS)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

           sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + Table_name);
           onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name , String marks)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_name, name);
        cv.put(col_marks , marks);
        long result = db.insert(Table_name ,null,cv);
        if(result == -1)
        {
            return false ;
        }
        else {
            return true ;
        }
    }
    public Cursor ShowData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor  = db.rawQuery("select * from"+Table_name , null);
        return cursor ;
    }
    public boolean updateData( String id , String name , String marks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_id,id);
        cv.put(col_name,name);
        cv.put(col_marks ,marks);
        db.update(Table_name ,cv , "Id = ?" , new String[] {id});
        return true ;
    }

    public Integer delete(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
      return   db.delete(Table_name, "Id = ?" , new String[] {id});
    }
}
