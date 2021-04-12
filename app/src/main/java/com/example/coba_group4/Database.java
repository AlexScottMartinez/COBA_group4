package com.example.coba_group4;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper
{
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_USERNAME = "CUSTOMER_USERNAME";
    public static final String COLUMN_CUSTOMER_EMAIL = "CUSTOMER_EMAIL";
    public static final String COLUMN_CUSTOMER_PASSWORD = "CUSTOMER_PASSWORD";

    public Database(@Nullable Context context)
    {
        super(context, "customer.db", null, 1);
    }

    // This is called the first time a database is accessed
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTERGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_USERNAME + " TEXT, " + COLUMN_CUSTOMER_EMAIL + " TEXT, " + COLUMN_CUSTOMER_PASSWORD + " TEXT) ";

        db.execSQL(createTableStatement);
    }
    // This is called if the database version number changes. It prevents previous users apps from breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    public boolean addOne(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER_NAME, user.getName());
        cv.put(COLUMN_CUSTOMER_USERNAME, user.getUsername());
        cv.put(COLUMN_CUSTOMER_EMAIL, user.getEmail());
        cv.put(COLUMN_CUSTOMER_PASSWORD, user.getPassword());

        long insert = db.insert(CUSTOMER_TABLE, null, cv);
        if (insert == 1)
        {
            return false;
        }
        else {
            return true;
        }
    }
}
