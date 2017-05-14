package com.pulkit.demon.chemistandbloodbanklocator.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pulkit.demon.chemistandbloodbanklocator.R;

public class CMapFragment extends Fragment {

    public CMapFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cmap, container, false);

        getActivity().setTitle("Map");
     /*   RecyclerView songRecyclerView = (RecyclerView)view.findViewById(R.id.song_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        songRecyclerView.setLayoutManager(linearLayoutManager);
        songRecyclerView.setHasFixedSize(true);

        SongAdapter mAdapter = new SongAdapter(getActivity(), getTestData());
        songRecyclerView.setAdapter(mAdapter);
        */return view;
    }

   /* public List<SongObject> getTestData() {
        List<SongObject> recentSongs = new ArrayList<SongObject>();
        recentSongs.add(new SongObject("Adele", "Someone Like You", ""));
        recentSongs.add(new SongObject("Adele", "Someone Like You", ""));
        recentSongs.add(new SongObject("Adele", "Someone Like You", ""));
        recentSongs.add(new SongObject("Adele", "Someone Like You", ""));
        recentSongs.add(new SongObject("Adele", "Someone Like You", ""));
        recentSongs.add(new SongObject("Adele", "Someone Like You", ""));
        return recentSongs;
    }*/
}
