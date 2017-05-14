package com.pulkit.demon.chemistandbloodbanklocator.Pro;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pulkit.demon.chemistandbloodbanklocator.fragment.CMapFragment;
import com.pulkit.demon.chemistandbloodbanklocator.fragment.ChemistMap;
import com.pulkit.demon.chemistandbloodbanklocator.fragment.Spin;
import com.pulkit.demon.chemistandbloodbanklocator.list.ListChemist;

public class CustomFragmentPageAdapter extends FragmentPagerAdapter{

    private static final String TAG = CustomFragmentPageAdapter.class.getSimpleName();

    private static final int FRAGMENT_COUNT = 4;

    public CustomFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ProLogin();
            case 1:
                return new ProLogin();
            case 2:return new ProLogin();
            case 3:
                return new ProLogin();
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
                return "ChemistMap";
            case 1:
                return "List";
            case 2:
                return "Category";
            case 3:
                return "Artists";
        }
        return null;
    }
}
