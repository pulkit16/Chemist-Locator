package com.pulkit.demon.chemistandbloodbanklocator.JanAushadi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Demon on 21-04-2017.
 */

public class ParseJSON {
    public static String[] ids;
    public static String[] names;
    public static String[] person;
    public static String[] lat;
    public static String[] longi;


    //public static final String JSON_ARRAY = "delhi";
    public static final String KEY_ID = "ID";
    public static final String KEY_NAME = "Address";
    public static final String KEY_PERSON = "Contact_Person";
    public static final String KEY_LATITUDE = "latitude";
    public static final String KEY_LONGITUDE = "longitude";

    public JSONArray location = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    public void parseJSON(String JSON_ARRAY){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            location = jsonObject.getJSONArray(JSON_ARRAY);

            ids = new String[location.length()];
            names = new String[location.length()];
            person = new String[location.length()];
            lat = new String[location.length()];
            longi = new String[location.length()];
            for(int i=0;i<location.length();i++){
                JSONObject jo = location.getJSONObject(i);
               // ids[i] = jo.getString(KEY_ID);
                names[i] = jo.getString(KEY_NAME);
                person[i] = jo.getString(KEY_PERSON);
                lat[i]=jo.getString(KEY_LATITUDE);
                longi[i]=jo.getString(KEY_LONGITUDE);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}