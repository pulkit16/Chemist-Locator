package com.pulkit.demon.chemistandbloodbanklocator.Pro;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.pulkit.demon.chemistandbloodbanklocator.JanAushadi.CategoryList.ListChemist;
import com.pulkit.demon.chemistandbloodbanklocator.R;
import com.pulkit.demon.chemistandbloodbanklocator.app.AppController;
import com.pulkit.demon.chemistandbloodbanklocator.fragment.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Demon on 13-05-2017.
 */

public class ProLogin extends Fragment {

    //private  final String TAG = getActivity().getSimpleName();
    private Button btnLic;
    private static final String JSON_URL= "http://192.168.219.1/android_table1_api/getloc.php";
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;
    private String lic;
    private EditText inputlic;
    public ProLogin() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prologin, container, false);

        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        inputlic = (EditText) getActivity().findViewById(R.id.licno);
        btnLic = (Button) getActivity().findViewById(R.id.btnlic);
// Progress dialog
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);

        // SQLite database handler
        db = new SQLiteHandler(getActivity().getApplicationContext());

        // Session manager
        session = new SessionManager(getActivity().getApplicationContext());

        /* Check if user is already logged                                      in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(getActivity(), MapsActivity.class);
            startActivity(intent);
            getActivity().finish();
        }*/

        // Login button Click Event
        btnLic.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                lic = inputlic.getText().toString().trim();

                // Check for empty data in the form
                if (!lic.isEmpty()) {
                    // login user
                    checkLogin(lic);
                } else {
                    // Prompt user to enter credentials

                    Toast.makeText(getActivity().getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }
            }

        });


    }
    private void checkLogin(final String lic) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Logging in ...");
        showDialog();


        StringRequest strReq = new StringRequest(JSON_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //  Log.d(getActivity().getSi, "Login Response: " + response.toString());
                hideDialog();
                try{
                    ParseJSON pj = new ParseJSON(response);
                pj.parseJSON();
                int i;
                String f;
                    for( i=0;i<pj.location.length();i++) {

                      f= ParseJSON.f2[i];
                    if(f.equals(lic)||f==lic)
                    {


                        String name = ParseJSON.names[i];
                        String add = ParseJSON.address[i];
                        String lat = ParseJSON.lat[i];
                        String longi = ParseJSON.longi[i];
                        int id= ParseJSON.ids[i];

                        // Inserting row in users table
                        db.addUser(id, f, name, add, lat, longi);
                        // Launch main activity
                        Toast.makeText(getActivity().getApplicationContext(), "Json : " +Integer.toString(id)+f+name+add+lat+longi, Toast.LENGTH_LONG).show();

    //                    Fragment fragment = new AddViewFragment();

                        Intent intent=new Intent(getActivity(),ProActivity1.class);
                        startActivity(intent);
                        getActivity().finish();
                    }

                    }
                } catch (Exception e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getActivity().getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.e(, "Login Error: " + error.getMessage());
                Toast.makeText(getActivity().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) ;

        // Adding request to request queue

        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        // if (!pDialog.isShowing())
        //   pDialog.show();
    }

    private void hideDialog() {
        //if (pDialog.isShowing())
        //  pDialog.dismiss();
    }

}
