package com.pulkit.demon.chemistandbloodbanklocator.fragment;

/**
 * Created by Demon on 05-05-2017.
 */

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.util.Log;
import android.view.InflateException;
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
import com.pulkit.demon.chemistandbloodbanklocator.MusicActivity;
import com.pulkit.demon.chemistandbloodbanklocator.R;
import com.pulkit.demon.chemistandbloodbanklocator.TrackGPS;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChemistMap extends Fragment implements OnMapReadyCallback {
    private View rootView;
    private static final String TAG = Myfragment.class.getSimpleName();
    GoogleMap myMap;
    MapView mMapView;
    public static final String JSON_URL = "http://192.168.219.1/android_table1_api/getloc.php";
    private TrackGPS gps;
    double lati;
    double longit;
    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gps=new TrackGPS(getActivity());

        if(gps.canGetLocation()) {
            longit = gps.getLongitude();
            lati = gps.getLatitude();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            rootView = inflater.inflate(R.layout.activity_myfragment, container, false);




            MapsInitializer.initialize(this.getActivity());
            mMapView = (MapView) rootView.findViewById(R.id.map);
            mMapView.onCreate(savedInstanceState);
            mMapView.getMapAsync(this);

        } catch (InflateException e) {
            Log.e(TAG, "Inflate exception");
        }
        return rootView;
    }


    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        gps.stopUsingGPS();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
                        Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
    private void showJSON(String json,GoogleMap mMap){
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        LatLng sydney;
        for(int i=1;i<pj.location.length();i++) {
            String name = ParseJSON.names[i];
            String add = ParseJSON.address[i];
            String lat = ParseJSON.lat[i];
            String longi = ParseJSON.longi[i];
            if(lat!="null"&& longi!="null")
            {
                double lt = Double.parseDouble(lat);
                double lng = Double.parseDouble(longi);

                sydney = new LatLng(lt, lng);

                mMap.addMarker(new MarkerOptions().position(sydney).title(name).snippet(add).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic)));
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        {
            myMap=googleMap;

            sendRequest(myMap);

            LatLng s = new LatLng(lati, longit);
            myMap.addMarker(new MarkerOptions().position(s).title("current location"));
            myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s,12));
            myMap.getUiSettings().setZoomControlsEnabled(true);
            myMap.getUiSettings().setMyLocationButtonEnabled(true);
            myMap.getUiSettings().isMapToolbarEnabled();


        }

    }

}