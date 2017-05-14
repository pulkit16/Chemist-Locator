package com.pulkit.demon.chemistandbloodbanklocator.Pro;

/**
 * Created by Demon on 08-04-2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "android_ap.db";

    // Login table name
    private static final String TABLE_USER = "table1";

    // Login Table Columns names
    private static final String KEY_ID = "ID";
    private static final String KEY_F2 = "F2";
    private static final String KEY_NAME = "CHEMIST_NAME";
    private static final String KEY_ADDRESS = "ADDRESS";
    private static final String KEY_LATITUDE = "LATITUDE";
    private static final String KEY_LONGITUDE = "LONGITUDE";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_F2 + " TEXT,"+ KEY_NAME + " TEXT,"
                + KEY_ADDRESS + " TEXT ," + KEY_LATITUDE + " TEXT,"
                + KEY_LONGITUDE + " TEXT" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(int id, String f2, String chemist_name, String address,String lat,String longi) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, id); // Name
        values.put(KEY_F2, f2); // Email
        values.put(KEY_NAME, chemist_name); // Email
        values.put(KEY_ADDRESS, address); // Created At
        values.put(KEY_LATITUDE,lat);
        values.put(KEY_LONGITUDE,longi);

        // Inserting Row
        long flag = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public int getName() {
        String selectQuery = "SELECT  * FROM " + TABLE_USER;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int id;

        cursor.moveToLast();
        if (cursor.getCount() > 0) {


            id = cursor.getInt(0);
        }
        else
        {id=1;}

        cursor.close();
        db.close();
    return id;
    }
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("id", cursor.getString(0));
            user.put("f2", cursor.getString(1));
            user.put("chemist_name", cursor.getString(2));
            user.put("address", cursor.getString(3));
            user.put("lat", cursor.getString(4));
            user.put("longi", cursor.getString(5));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }

}