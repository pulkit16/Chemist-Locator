package com.pulkit.demon.chemistandbloodbanklocator.login.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by Demon on 16-04-2017.
 */

public class SQLLOcation extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "android_api.db";

    // Login table name
    private static final String TABLE_USER = "table1";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_F2 = "F2";
    private static final String KEY_CHEMIST_NAME = "chemist_name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";

    public SQLLOcation(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOCATION_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_F2 + " TEXT,"
                + KEY_CHEMIST_NAME + " TEXT UNIQUE," + KEY_ADDRESS + " TEXT,"
                + KEY_LATITUDE + " DOUBLE" + KEY_LONGITUDE + " DOUBLE"+")";
        db.execSQL(CREATE_LOCATION_TABLE);

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

    public HashMap<String, String> getlocDetails() {
        HashMap<String, String> loc = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            loc.put("chemist_name", cursor.getString(3));
            loc.put("address", cursor.getString(4));
            loc.put("latitude", cursor.getString(5));
            loc.put("longitude", cursor.getString(6));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + loc.toString());

        return loc;
    }
    public void addUser(String chemist_name, String address, String id, String latitude,String longitude) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CHEMIST_NAME, chemist_name); // Name
        values.put(KEY_ADDRESS, address); // Email
        values.put(KEY_ID, id); // Email
        values.put(KEY_LATITUDE,latitude);
        values.put(KEY_LONGITUDE,longitude);
        // Inserting Row
        long id1 = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id1);
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