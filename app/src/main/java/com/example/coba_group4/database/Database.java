package com.example.coba_group4.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper
{
    private static final String DBNAME = "coba.db";

    private static Database dbInstance = null;

    public static synchronized Database getInstance(Context context)
    {
        if(dbInstance == null)
        {
            dbInstance = new Database(context);
        }
        return dbInstance;
    }

    protected Database(@Nullable Context context)
    {
        super(context, DBNAME, null, 1);
    }


    // This is called the first time a database is accessed
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTERGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_USERNAME + " TEXT, " + COLUMN_CUSTOMER_EMAIL + " TEXT, " + COLUMN_CUSTOMER_PASSWORD + " TEXT)";

        db.execSQL("create Table users      (id INTEGER primary key autoincrement, fname TEXT, mname TEXT, lname TEXT, username TEXT, email TEXT, password TEXT, profession TEXT, idnum TEXT)");
        db.execSQL("create Table occurrences (id INTEGER primary key, address TEXT, city TEXT, state TEXT, zip INTEGER, type TEXT, time INTEGER, description TEXT)");
    }
    // This is called if the database version number changes. It prevents previous users apps from breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop Table if exists users");
        db.execSQL("drop Table if exists occurrences");
    }


}
