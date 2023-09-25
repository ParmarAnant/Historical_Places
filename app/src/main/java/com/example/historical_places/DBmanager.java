package com.example.historical_places;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBmanager extends SQLiteOpenHelper {

    private static final String DB_NAME = "trialReviewDBTwo";
    private static final int DB_VERSION = 1;

    public DBmanager(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table trialReviewTable(id integer primary key autoincrement," +
                "userName text," +
                "name text," +
                "rating text," +
                "review text);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists trialReviewTable");
        onCreate(db);
    }

    public boolean insert_data(String userName, String name, String rating, String review) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("userName",userName);
        cv.put("name",name);
        cv.put("rating",rating);
        cv.put("review",review);

        long r = db.insert("trialReviewTable", null, cv);
        if (r>0) return true;
        else return false;
    }

    // fetch data from database
    public Cursor view_data(String place_name) {
        SQLiteDatabase db = this.getReadableDatabase();
        //String query = "select * from trialReviewTable where name = ''";
        String[] arr_str = new String[]{place_name};
        Cursor cursor = db.rawQuery("select * from trialReviewTable where name in (?)", arr_str);
        return cursor;
    }
}
