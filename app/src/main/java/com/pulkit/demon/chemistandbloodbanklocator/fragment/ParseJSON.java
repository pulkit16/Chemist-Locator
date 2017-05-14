package com.pulkit.demon.chemistandbloodbanklocator.fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by Demon on 21-04-2017.
 */

public class ParseJSON {
    public static int[] ids;
    public static String[] names;
    public static String[] address;
    public static String[] lat;
    public static String[] longi;


    public static final String JSON_ARRAY = "table1";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "chemist_name";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_LATITUDE = "latitude";
    public static final String KEY_LONGITUDE = "longitude";

    public JSONArray location = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    public void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            location = jsonObject.getJSONArray(JSON_ARRAY);

            ids = new int[location.length()];
            names = new String[location.length()];
            address = new String[location.length()];
            lat = new String[location.length()];
            longi = new String[location.length()];
            for(int i=0;i<location.length();i++){
                JSONObject jo = location.getJSONObject(i);
                ids[i] = jo.getInt(KEY_ID);
                names[i] = jo.getString(KEY_NAME);
                address[i] = jo.getString(KEY_ADDRESS);
                lat[i]=jo.getString(KEY_LATITUDE);
                longi[i]=jo.getString(KEY_LONGITUDE);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}