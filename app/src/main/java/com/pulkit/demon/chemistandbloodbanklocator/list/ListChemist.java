package com.pulkit.demon.chemistandbloodbanklocator.list;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pulkit.demon.chemistandbloodbanklocator.MainActivity;
import com.pulkit.demon.chemistandbloodbanklocator.R;
import com.pulkit.demon.chemistandbloodbanklocator.TrackGPS;
import com.pulkit.demon.chemistandbloodbanklocator.fragment.ChemistMap;
import com.pulkit.demon.chemistandbloodbanklocator.fragment.ChemistMapFragment;
import com.pulkit.demon.chemistandbloodbanklocator.fragment.MapsActivity;
import com.pulkit.demon.chemistandbloodbanklocator.fragment.ParseJSON;
import com.pulkit.demon.chemistandbloodbanklocator.fragment.PlaylistFragment;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.twotoasters.jazzylistview.recyclerview.JazzyRecyclerViewScrollListener;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.round;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
public class ListChemist extends Fragment {
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;
    public static final String JSON_URL = "http://192.168.219.1/android_table1_api/getloc.php";
    private View rootView;
    private FragmentManager fragmentManager;
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            rootView = inflater.inflate(R.layout.activity_list, container, false);


        } catch (InflateException e) {
            Log.e(ListChemist.class.getSimpleName(), "Inflate exception");
        }
        return rootView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        mSwipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.activity_main_swipe_refresh_layout);
        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        Toolbar toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
        //getView().setSupportActionBar(toolbar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        mAdapter = new MoviesAdapter(movieList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        JazzyRecyclerViewScrollListener jazzyScrollListener = new JazzyRecyclerViewScrollListener();
        recyclerView.setOnScrollListener(jazzyScrollListener);
        jazzyScrollListener.setTransitionEffect(11);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Movie movie = movieList.get(position);
/*
                Toast.makeText(getActivity().getApplicationContext()," is selected!", Toast.LENGTH_SHORT).show();
                Fragment fragment=new PlaylistFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container_wrapper, fragment);
                fragmentTransaction.addToBackStack(ListChemist.class.getSimpleName());
                fragmentTransaction.commitAllowingStateLoss();
              //  movie.getTitle() +


                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
                getActivity().finish();
  */
                int id=movie.getId();
               Bundle bundl = new Bundle();
                bundl.putInt("id", id);
                bundl.putDouble("lat",movie.getLocation().latitude);
                bundl.putDouble("longi",movie.getLocation().longitude);
                bundl.putString("name",movie.getTitle());
                bundl.putString("add",movie.getGenre());
                bundl.putString("dis",movie.getYear());
               FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ChemistDetail dv = new ChemistDetail();
                dv.setArguments(bundl);
                fragmentTransaction.replace(R.id.main_container_wrapper, dv);
                fragmentTransaction.commit();

            }

            @Override
            public void onLongClick(View view, int position) {
                /*Fragment fragment=new ChemistMap();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container_wrapper, fragment);
                fragmentTransaction.addToBackStack(ListChemist.class.getSimpleName());
                fragmentTransaction.commitAllowingStateLoss();
*/ Toast.makeText(getActivity().getApplicationContext()," is selected!", Toast.LENGTH_SHORT).show();


            }
        }));
/*        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                                     @Override
                                                     public void onRefresh() {

                                                     }
                                                 });
*/
        prepareMovieData();
    }

    private void prepareMovieData() {


        TrackGPS gps = new TrackGPS(getActivity());
        double longit;
        double lati;
        if (gps.canGetLocation()) {
            longit = gps.getLongitude();
            lati = gps.getLatitude();
        }

        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ParseJSON pj = new ParseJSON(response);
                        pj.parseJSON();

                        TrackGPS gps = new TrackGPS(getActivity());
                        double longit;
                        double lati;
                        if (gps.canGetLocation()) {
                            longit = gps.getLongitude();
                            lati = gps.getLatitude();
                        } else {
                            longit = 0;
                            lati = 0;
                        }
                        for (int i = 1; i < pj.location.length(); i++) {
                            String name = ParseJSON.names[i];
                            String add = ParseJSON.address[i];
                            String lat = ParseJSON.lat[i];
                            String longi = ParseJSON.longi[i];
                            int id= ParseJSON.ids[i];
                            if ((!lat.equals("")) && (!lat.equals("null")) && (!longi.equals("")) && (!longi.equals("null"))) {
                                Movie movie;
                                double d;
                                LatLng l1;
                                LatLng l2;
                                double lat1 = Double.parseDouble(lat);
                                double longi1 = Double.parseDouble(longi);

                                     l1 = new LatLng(lat1, longi1);
                                     l2 = new LatLng(lati, longit);
                                     d = getDistance(l1, l2);
                                    if(d<15) {
                                        movie = new Movie(name, add, d, l1,id);
                                        movieList.add(movie);
                                        mAdapter.notifyDataSetChanged();
                                    }
                            }
                        }

                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

        mAdapter.notifyDataSetChanged();
    }
    public double getDistance(LatLng LatLng1, LatLng LatLng2) {
    double distance = 0;
    Location locationA = new Location("A");
    locationA.setLatitude(LatLng1.latitude);
    locationA.setLongitude(LatLng1.longitude);
    Location locationB = new Location("B");
    locationB.setLatitude(LatLng2.latitude);
    locationB.setLongitude(LatLng2.longitude);
    distance =locationA.distanceTo(locationB);
        distance=distance/1000;
        //String d=Double.toString(Math.round(distance*100)/100);


    return distance;
}

}
