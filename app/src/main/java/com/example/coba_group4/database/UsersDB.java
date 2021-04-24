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

    public boolean addOne(String fname, String mname, String lname, String username, String email, String password, String profession, String idnum)
    {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("fname", fname);
        cv.put("mname", mname);
        cv.put("lname", lname);
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        cv.put("profession", profession);
        cv.put("idnum", idnum);

        long insert = db.insert("users", null, cv);
        if (insert > 0)
        {
            return true;
        }
        return false;
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

    public Boolean checkIdnum (String idnum)
    {
        String regex = "^[0-9]{10}$";
        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where idnum = ?", new String[] {idnum});
        if (!idnum.matches(regex))
        {
            return false;
        }
        else {
            return true;
        }
    }
}
