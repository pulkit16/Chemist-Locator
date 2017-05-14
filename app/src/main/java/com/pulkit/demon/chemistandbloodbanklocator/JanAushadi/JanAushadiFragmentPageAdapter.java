package com.pulkit.demon.chemistandbloodbanklocator.JanAushadi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pulkit.demon.chemistandbloodbanklocator.adapter.CustomFragmentPageAdapter;
import com.pulkit.demon.chemistandbloodbanklocator.fragment.CMapFragment;
import com.pulkit.demon.chemistandbloodbanklocator.fragment.ChemistMap;

/**
 * Created by Demon on 08-05-2017.
 */


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pulkit.demon.chemistandbloodbanklocator.fragment.ChemistMap;
import com.pulkit.demon.chemistandbloodbanklocator.fragment.PlaylistFragment;
import com.pulkit.demon.chemistandbloodbanklocator.fragment.CMapFragment;
import com.pulkit.demon.chemistandbloodbanklocator.JanAushadi.list.ListChemist;

public class JanAushadiFragmentPageAdapter extends FragmentPagerAdapter {

        private static final String TAG = JanAushadiFragmentPageAdapter.class.getSimpleName();

        private static final int FRAGMENT_COUNT = 3;

        public JanAushadiFragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                   return new JanAushadi();
                case 1:
                return new ListChemist();
                case 2:
                    return new Spin();

            /*case 1:
                return new PlaylistFragment();
            case 2:
                return new AlbumFragment();
            case 3:
                return new ArtistFragment();*/
            }
            return null;
        }

        @Override
        public int getCount() {
            return FRAGMENT_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "JanAushadi Centres Map";
                case 1:
                    return "Centres List";
                case 2:
                    return "Centres Category";
            }
            return null;
        }
    }
