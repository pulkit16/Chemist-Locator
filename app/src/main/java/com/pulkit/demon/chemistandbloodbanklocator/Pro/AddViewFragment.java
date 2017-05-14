package com.pulkit.demon.chemistandbloodbanklocator.Pro;

/**
 * Created by Demon on 13-05-2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pulkit.demon.chemistandbloodbanklocator.R;

import java.util.HashMap;
import java.util.Map;

public class AddViewFragment extends Fragment{

    private SQLiteHandler db;
    Button btnViewProducts;
    Button btnNewProduct;
    private View rootView;
    private static String JSON_URL="http://192.168.219.1/product/new.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_viewaddproduct, container, false);

        return rootView;
    }// Buttons

    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    btnViewProducts = (Button) getActivity().findViewById(R.id.btnview);
        btnNewProduct = (Button) getActivity().findViewById(R.id.btnadd);
        db = new SQLiteHandler(getActivity().getApplicationContext());
        // view products click event
        btnViewProducts.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching All products Activity
//                Intent i = new Intent(getActivity().getApplicationContext(), AllProductsActivity.class);
  //              startActivity(i);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = new AllProductsActivity();
                fragmentTransaction.replace(R.id.main_container_wrapper, fragment);
                fragmentTransaction.commit();

            }
        });

        // view products click event
        btnNewProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching create new product activity
               // Intent i = new Intent(getActivity().getApplicationContext(), NewProductActivity.class);
               // startActivity(i);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = new NewProductActivity();
                fragmentTransaction.replace(R.id.main_container_wrapper, fragment);
                fragmentTransaction.commit();


            }
        });

        StringRequest stringRequest = new StringRequest(Request.Method.POST,JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(), "dfff", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                int i=db.getName();
                String s= Integer.toString(i);
                Map<String, String> params = new HashMap<String, String>();
                params.put("tname", s);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }
}
