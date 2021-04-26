package com.example.coba_group4.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coba_group4.forum.Forum;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ForumDB {

    private Database database;

    public ForumDB() {
        this.database = Database.getInstance(null);
    }

    public Boolean addOne(@NotNull Forum forum)
    {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("user", forum.getUser());
        cv.put("title", forum.getTitle());
        cv.put("time", forum.getTime().getTime());
        cv.put("description", forum.getDescription());

        long insert = db.insert("forums", null, cv);
        db.close();
        if (insert > 1)
        {
            return true;
        }
        return false;

    }

    public ArrayList<Forum> getAllForums()
    {
        SQLiteDatabase db = database.getReadableDatabase();
        ArrayList<Forum> forumArrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from forums", new String[] {});
        if (cursor.getCount() > 0)
        {
            while (cursor.moveToNext()) {
                Forum forum = new Forum();
                forum.setUser(cursor.getString(cursor.getColumnIndex("user")));
                forum.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                long dateTime = cursor.getLong(cursor.getColumnIndex("time"));
                forum.setTime(new Date(dateTime));
                forum.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                forumArrayList.add(forum);
            }
        }
        db.close();
        return forumArrayList;
    }
}