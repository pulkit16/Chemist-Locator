package com.pulkit.demon.chemistandbloodbanklocator.fragment;

import android.content.Intent;
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

import com.pulkit.demon.chemistandbloodbanklocator.R;
import com.pulkit.demon.chemistandbloodbanklocator.fragment.CategoryList.ListChemist;
import com.pulkit.demon.chemistandbloodbanklocator.login.activity.RegisterActivity;

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
                if(item=="Kanpur Nagar")
                {
                Fragment fragment = new ListChemist();
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
    categories.add("Kanpur");
    categories.add("Kanpur Nagar");
    categories.add("Computers");
    categories.add("Education");
    categories.add("Personal");
    categories.add("Travel");

    // Creating adapter for spinner
    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);

    // Drop down layout style - list view with radio button
     dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // attaching data adapter to spinner
    spinner.setAdapter(dataAdapter);


    }
    }


