package com.pulkit.demon.chemistandbloodbanklocator.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pulkit.demon.chemistandbloodbanklocator.fragment.ChemistMap;
import com.pulkit.demon.chemistandbloodbanklocator.fragment.PlaylistFragment;
import com.pulkit.demon.chemistandbloodbanklocator.fragment.CMapFragment;
import com.pulkit.demon.chemistandbloodbanklocator.fragment.Spin;
import com.pulkit.demon.chemistandbloodbanklocator.list.ListChemist;

public class CustomFragmentPageAdapter extends FragmentPagerAdapter{

    private static final String TAG = CustomFragmentPageAdapter.class.getSimpleName();

    private static final int FRAGMENT_COUNT = 3;

    public CustomFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ChemistMap();
            case 1:
                return new ListChemist();
            case 2:return new Spin();
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
                return "Chemist Map";
            case 1:
                return "Chemist List";
            case 2:
                return "Chemist Category";
        }
        return null;
    }
}
