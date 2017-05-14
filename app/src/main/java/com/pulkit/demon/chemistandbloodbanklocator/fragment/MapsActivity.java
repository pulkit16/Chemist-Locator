package com.pulkit.demon.chemistandbloodbanklocator.fragment;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pulkit.demon.chemistandbloodbanklocator.R;
import com.pulkit.demon.chemistandbloodbanklocator.TrackGPS;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public static final String JSON_URL = "http://192.168.219.1/android_table1_api/getloc.php";
    private TrackGPS gps;
    double lati;
    double longit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        gps=new TrackGPS(MapsActivity.this);

        mapFragment.getMapAsync(this);

        if(gps.canGetLocation()) {
            longit = gps.getLongitude();
            lati = gps.getLatitude();
        }


    }

    private void sendRequest(final GoogleMap mMap){

        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response,mMap);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MapsActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void showJSON(String json,GoogleMap mMap){
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        LatLng sydney;
        for(int i=1;i<100;i++) {
            String name = ParseJSON.names[i];
            String add = ParseJSON.address[i];
            String lat = ParseJSON.lat[i];
            String longi = ParseJSON.longi[i];
            if(lat!="null"&& longi!="null")
            {
                double lt = Double.parseDouble(lat);
                double lng = Double.parseDouble(longi);

                sydney = new LatLng(lt, lng);

                mMap.addMarker(new MarkerOptions().position(sydney).title(name + add));
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
            sendRequest(mMap);
        // Add a marker in Sydney and move the camera


                LatLng s = new LatLng(lati, longit);
                mMap.addMarker(new MarkerOptions().position(s).title("current location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(s));
        Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();


    }
    protected void onDestroy() {
        super.onDestroy();
        gps.stopUsingGPS();
    }
    private void loc(View view)
    {
        LatLng s = new LatLng(lati, longit);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(s));

    }
}
