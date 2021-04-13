package com.example.coba_group4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper
{
    public static final String DBNAME = "user.db";

    public Database(@Nullable Context context)
    {

        super(context, "user.db", null, 1);
    }

    // This is called the first time a database is accessed
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTERGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_USERNAME + " TEXT, " + COLUMN_CUSTOMER_EMAIL + " TEXT, " + COLUMN_CUSTOMER_PASSWORD + " TEXT)";

        db.execSQL("create Table users(name TEXT primary key, username TEXT, email TEXT, password TEXT)");
    }
    // This is called if the database version number changes. It prevents previous users apps from breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop Table if exists users");
    }

    public boolean addOne(String name, String username, String email, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", name);
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);

        long insert = db.insert("users", null, cv);
        if (insert == 1)
        {
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean checkUsername (String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ?", new String[] {username});
        if (cursor.getCount() > 0)
        {
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean checkUsernamePassword (String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ? and password = ?", new String[] {username, password});
        if (cursor.getCount() > 0)
        {
            return  true;
        }
        else {
            return false;
        }
    }
}
