package com.pulkit.demon.chemistandbloodbanklocator.list;

/**
 * Created by Demon on 13-05-2017.
 */

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.pulkit.demon.chemistandbloodbanklocator.Pro.SQLiteHandler;
import com.pulkit.demon.chemistandbloodbanklocator.R;
import com.pulkit.demon.chemistandbloodbanklocator.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AllProductsActivity extends ListFragment {

    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object

    ArrayList<HashMap<String, String>> productsList;

    // url to get all products list
    private static String JSON_URL = "http://192.168.219.1/product/allproducts.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_PID = "id";
    private static final String TAG_NAME = "name";
    private View rootView;
    private static int id;
    // products JSONArray
    JSONArray products = null;
    private TextView name;
    private TextView add;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.all_products, container, false);

        return rootView;
    }// Buttons

    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        // Hashmap for ListView
        productsList = new ArrayList<HashMap<String, String>>();
      name=(TextView) getActivity().findViewById(R.id.textView2);
      add=(TextView) getActivity().findViewById(R.id.textView4);
       Bundle bundle=getArguments();
        id=bundle.getInt("id");
        name.setText(bundle.getString("name"));
        add.setText(bundle.getString("add"));

        // Loading products in Background Thread
        showProduct();
        // Get listview
        ListView lv = getListView();

        // on seleting single product
        // launching Edit Product Screen
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                /* getting values from selected ListItem
                String pid = ((TextView) view.findViewById(R.id.pid)).getText()
                        .toString();

                // Starting new intent
                Intent in = new Intent(getApplicationContext(),
                        EditProductActivity.class);
                // sending pid to next activity
                in.putExtra(TAG_PID, pid);

                // starting new activity and expecting some response back
                startActivityForResult(in, 100);
                */
            }
        });

    }

    // Response from Edit Product Activity
  /*
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if result code 100
        if (resultCode == 100) {
            // if result code 100 is received
            // means user edited/deleted product
            // reload this screen again
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

    }
*/

    /**
     * Background Async Task to Load all product by making HTTP Request
     */


    private void showProduct() {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

//        pDialog.setMessage("Adding Product");
        showDialog();


        StringRequest strReq = new StringRequest(Request.Method.POST, JSON_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //  Log.d(getActivity().getSi, "Login Response: " + response.toString());
                try {
                    hideDialog();
                    JSONObject jsonObject;
                    jsonObject = new JSONObject(response);
                    String s = Integer.toString(id);
                    //  products = jsonObject.getJSONArray(s);
                    //int success = jsonObject.getInt(TAG_SUCCESS);

                    //if (success == 1) {
                        // products found
                        // Getting Array of Products
                        products = jsonObject.getJSONArray("products");
                    HashMap<String, String> map;
                        if(jsonObject.getInt("success")==1) {
                            // looping through All Products
                            for (int j = 0; j < products.length(); j++) {
                                JSONObject c = products.getJSONObject(j);

                                // Storing each json item in variable
                                String id = c.getString(TAG_PID);
                                String name = c.getString(TAG_NAME);

                                // creating new HashMap
                                map = new HashMap<String, String>();

                                // adding each child node to HashMap key => value
                                map.put(TAG_PID, id);
                                map.put(TAG_NAME, name);

                                // adding HashList to ArrayList
                                productsList.add(map);
                                //      }
                            }
                            //else {
                            // no products found
                            // Launch Add New product Activity
                      /*  Intent i = new Intent(getApplicationContext(),
                                NewProductActivity.class);
                        // Closing all previous activities
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                   */
                            // }

                            ListAdapter adapter = new SimpleAdapter(
                                    getActivity(), productsList,
                                    R.layout.list_item, new String[]{TAG_PID,
                                    TAG_NAME},
                                    new int[]{R.id.pid, R.id.name});
                            // updating listview
                            setListAdapter(adapter);
                        }
                        else{
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Data not available", Toast.LENGTH_LONG).show();

                        }


                } catch (JSONException E) {

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
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url

                String s = Integer.toString(id);
                Map<String, String> params = new HashMap<String, String>();
                params.put("tname", s);
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
/*

















    class LoadAllProducts extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AllProductsActivity.this);
            pDialog.setMessage("Loading products. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
























        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    products = json.getJSONArray(TAG_PRODUCTS);

                    // looping through All Products
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);

                        // Storing each json item in variable
                        String id = c.getString(TAG_PID);
                        String name = c.getString(TAG_NAME);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_PID, id);
                        map.put(TAG_NAME, name);

                        // adding HashList to ArrayList
                        productsList.add(map);
                    }
                } else {
                    // no products found
                    // Launch Add New product Activity
                    Intent i = new Intent(getApplicationContext(),
                            NewProductActivity.class);
                    // Closing all previous activities
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {

                    ListAdapter adapter = new SimpleAdapter(
                            AllProductsActivity.this, productsList,
                            R.layout.list_item, new String[] { TAG_PID,
                            TAG_NAME},
                            new int[] { R.id.pid, R.id.name });
                    // updating listview
                    setListAdapter(adapter);
                }
            });

        }

    }
}*/