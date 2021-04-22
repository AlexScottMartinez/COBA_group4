package com.example.coba_group4.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UsersDB {

    private Database database;

    public UsersDB()
    {
        this.database = Database.getInstance(null);
    }

    public boolean addOne(String name, String username, String email, String password)
    {
        SQLiteDatabase db = database.getWritableDatabase();
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
        SQLiteDatabase db = database.getWritableDatabase();
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
        SQLiteDatabase db = database.getWritableDatabase();
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
