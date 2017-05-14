package com.pulkit.demon.chemistandbloodbanklocator.JanAushadi;

/**
 * Created by Demon on 08-05-2017.
 */


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pulkit.demon.chemistandbloodbanklocator.R;
import com.pulkit.demon.chemistandbloodbanklocator.adapter.CustomFragmentPageAdapter;


public class JanAushadiMapFragment extends Fragment {

    private static final String TAG = JanAushadiMapFragment.class.getSimpleName();

    private TabLayout tabLayout;
    private ViewPager viewPager;


    public JanAushadiMapFragment() {
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jan_aushadi_map, container, false);

        tabLayout = (TabLayout)view.findViewById(R.id.tabs1);
        viewPager = (ViewPager)view.findViewById(R.id.view_pager1);

        viewPager.setAdapter(new JanAushadiFragmentPageAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}

