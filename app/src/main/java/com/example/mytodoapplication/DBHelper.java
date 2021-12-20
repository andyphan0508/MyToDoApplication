package com.example.mytodoapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public DBHelper(@Nullable Context context) {
        super(context, "Login.db", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Users (username TEXT PRIMARY KEY, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Users");
    }

    // Data insert check
    public Boolean insertData (String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("Username", username);
        contentValue.put("Password", password);
        long result = db.insert("Users", null, contentValue);
        return result != -1;
    }
    // Check username from DB
    public Boolean checkUsr (String username) {
        SQLiteDatabase db = this.getWritableDatabase();

        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery(
                "SELECT * FROM Users WHERE username = ?",
                new String[] {username});

        return cursor.getCount() > 0;
    }

    public Boolean checkUsrPwd (String username, String password) {
        SQLiteDatabase db = this. getWritableDatabase();

        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery(
                "SELECT * FROM Users WHERE username = ? AND password = ?",
                new String[]{username, password});

        return cursor.getCount() > 0;
    }

}
