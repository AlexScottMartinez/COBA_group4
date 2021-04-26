package com.example.coba_group4.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coba_group4.occurence.Occurrence;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        db.close();
        if (insert > 1)
        {
            return true;
        }
        return false;

    }

    public ArrayList<Occurrence> getAllOccurrences()
    {
        SQLiteDatabase db = database.getReadableDatabase();
        ArrayList<Occurrence> occurrences = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from occurrences", new String[] {});
        if (cursor.getCount() > 0)
        {
            while (cursor.moveToNext()) {
                Occurrence occurrence = new Occurrence();
                occurrence.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                occurrence.setCity(cursor.getString(cursor.getColumnIndex("city")));
                occurrence.setState(cursor.getString(cursor.getColumnIndex("state")));
                occurrence.setZipCode(cursor.getInt(cursor.getColumnIndex("zip")));
                occurrence.setType(cursor.getString(cursor.getColumnIndex("type")));
                long dateTime = cursor.getLong(cursor.getColumnIndex("time"));
                occurrence.setSubmittedTime(new Date(dateTime));
                occurrence.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                occurrences.add(occurrence);
            }
        }
        db.close();
        return occurrences;
    }
}