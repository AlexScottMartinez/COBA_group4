package com.example.coba_group4.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.coba_group4.occurence.Occurrence;

import org.jetbrains.annotations.NotNull;

public class OccurrenceDB {

    private Database database;

    public OccurrenceDB() {
        this.database = Database.getInstance(null);
    }

    public Boolean addOne(@NotNull Occurrence occurrence)
    {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("address", occurrence.getAddress());
        cv.put("city", occurrence.getCity());
        cv.put("state", occurrence.getState());
        cv.put("zip", occurrence.getZipCode());
        cv.put("type", occurrence.getType());
        cv.put("time", occurrence.getSubmittedTime().getTime());
        cv.put("description", occurrence.getDescription());

        long insert = db.insert("occurrences", null, cv);
        if (insert == 1)
        {
            return false;
        }
        return true;
    }
}