package com.pulkit.demon.chemistandbloodbanklocator.fragment.CategoryList;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;
import com.pulkit.demon.chemistandbloodbanklocator.R;
import com.pulkit.demon.chemistandbloodbanklocator.TrackGPS;
import com.pulkit.demon.chemistandbloodbanklocator.fragment.MapsActivity;
import com.pulkit.demon.chemistandbloodbanklocator.fragment.ParseJSON;
import com.twotoasters.jazzylistview.recyclerview.JazzyRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

public class ListChemist extends Fragment {
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;
    public static final String JSON_URL = "http://192.168.219.1/android_table1_api/getloc.php";
    private View rootView;
    private FragmentManager fragmentManager;
    SwipeRefreshLayout mSwipeRefreshLayout;
   public ListChemist()
   {

   }

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
  //              Movie movie = movieList.get(position);
/*
                Toast.makeText(getActivity().getApplicationContext()," is selected!", Toast.LENGTH_SHORT).show();
                Fragment fragment=new PlaylistFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container_wrapper, fragment);
                fragmentTransaction.addToBackStack(ListChemist.class.getSimpleName());
                fragmentTransaction.commitAllowingStateLoss();
  */            //  movie.getTitle() +
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
                getActivity().finish();
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

double longit;
        double lati;

        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ParseJSON pj = new ParseJSON(response);
                        pj.parseJSON();


                        for (int i = 1; i < pj.location.length(); i++) {
                            String name = ParseJSON.names[i];
                            String add = ParseJSON.address[i];
                            String lat = ParseJSON.lat[i];
                            String longi = ParseJSON.longi[i];
                            if ((!lat.equals("")) && (!lat.equals("null")) && (!longi.equals("")) && (!longi.equals("null"))) {
                                Movie movie;

                                LatLng l1;
                                LatLng l2;
                                double lat1 = Double.parseDouble(lat);
                                double longi1 = Double.parseDouble(longi);

                                     l1 = new LatLng(lat1, longi1);

                                        movie = new Movie(name, add, 1, l1);
                                        movieList.add(movie);
                                        mAdapter.notifyDataSetChanged();

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

}
