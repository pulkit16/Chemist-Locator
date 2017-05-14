package com.pulkit.demon.chemistandbloodbanklocator.JanAushadi;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.pulkit.demon.chemistandbloodbanklocator.JanAushadi.CategoryList.ListChemist;
import com.pulkit.demon.chemistandbloodbanklocator.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Demon on 12-05-2017.
 */

public class Spin extends Fragment
{
    public Spin() {
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.janaushadi_spin, container, false);


        return view;
    }
    public void onStart() {
        super.onStart();

        addItemsOnSpinner();
    }public void addItemsOnSpinner(){


    Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinner);

    // Spinner click listener
       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                String item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
              if(item=="Delhi"||item=="Haryana")
              {
                  Bundle bundl = new Bundle();
                  if(item=="Delhi") {

                      bundl.putString("Item", "http://192.168.219.1/android_delhi/getloc.php");
                      bundl.putString("Table", "delhi");
                      Toast.makeText(parent.getContext(), "Selected:1 " + item, Toast.LENGTH_LONG).show();
                  }
                  else {
                      bundl.putString("Item", "http://192.168.219.1/android_haryana/getloc.php");
                      bundl.putString("Table", "haryana");
                  }


                  Fragment fragment = new ListChemist();
                  fragment.setArguments(bundl);

                  FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                  FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                  fragmentTransaction.replace(R.id.main_container_wrapper, fragment);
                  fragmentTransaction.addToBackStack(null);
                  fragmentTransaction.commit();

              }
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }    });

        // Spinner Drop down elements
    List<String> categories = new ArrayList<String>();
    categories.add("Select anyone");
    categories.add("Delhi");
    categories.add("Haryana");
    categories.add("Punjab");
    categories.add("UttarPradesh");


    // Creating adapter for spinner
    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);

    // Drop down layout style - list view with radio button
     dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // attaching data adapter to spinner
    spinner.setAdapter(dataAdapter);


    }
    }


