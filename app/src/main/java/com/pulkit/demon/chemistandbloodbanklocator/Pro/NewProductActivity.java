package com.pulkit.demon.chemistandbloodbanklocator.Pro;

/**
 * Created by Demon on 13-05-2017.
 */


        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

        import org.apache.http.NameValuePair;
        import org.apache.http.message.BasicNameValuePair;
        import org.json.JSONException;
        import org.json.JSONObject;

        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.AsyncTask;
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
        import com.pulkit.demon.chemistandbloodbanklocator.R;
        import com.pulkit.demon.chemistandbloodbanklocator.app.AppController;

public class NewProductActivity extends Fragment {

    // Progress Dialog
    private ProgressDialog pDialog;
    EditText inputName;
    EditText inputPrice;
    EditText inputDesc;

    SQLiteHandler db;

    // url to create new product
    private static String JSON_URL = "http://192.168.219.1/product/addproduct.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_products, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        db = new SQLiteHandler(getActivity().getApplicationContext());

        // Edit Text
        inputName = (EditText) getActivity().findViewById(R.id.inputName);
        inputPrice = (EditText) getActivity().findViewById(R.id.inputPrice);
        inputDesc = (EditText) getActivity().findViewById(R.id.inputDesc);

        // Create button
        Button btnCreateProduct = (Button) getActivity().findViewById(R.id.btnCreateProduct);

        // button click event
        btnCreateProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                String name = inputName.getText().toString();
                String price = inputPrice.getText().toString();
                String desc = inputDesc.getText().toString();

                addProduct(name, price, desc);

            }
        });
    }

    /**
     * Background Async Task to Create new product
     */

    private void addProduct(final String name, final String price, final String desc) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

//        pDialog.setMessage("Adding Product");
        showDialog();


        StringRequest strReq = new StringRequest(Request.Method.POST, JSON_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //  Log.d(getActivity().getSi, "Login Response: " + response.toString());
                hideDialog();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = new AllProductsActivity();
                fragmentTransaction.replace(R.id.main_container_wrapper, fragment);
                fragmentTransaction.commit();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.e(, "Login Error: " + error.getMessage());
                Toast.makeText(getActivity().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            protected Map<String, String> getParams() {
                // Posting params to register url
                int i = db.getName();
                String s = Integer.toString(i);
                Map<String, String> params = new HashMap<String, String>();
                params.put("tname", s);
                params.put("name", name);
                params.put("price", price);
                params.put("description", desc);

                return params;
            }

        };

        // Adding request to request queue

        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
//        if (!pDialog.isShowing())
  //          pDialog.show();
    }

    private void hideDialog() {
    //    if (pDialog.isShowing())
     //       pDialog.dismiss();
    }
}


