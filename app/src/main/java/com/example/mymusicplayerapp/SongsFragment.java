package com.example.mymusicplayerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.mymusicplayerapp.MainActivity.musicFiles;


public class SongsFragment extends Fragment {

    static MusicRecyclerAdapter adapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_songs, container, false);

        recyclerView = view.findViewById(R.id.recyclerView_songs);
        recyclerView.setHasFixedSize(true);
        if (!(musicFiles.size() < 1)) {
            adapter = new MusicRecyclerAdapter(getContext(), musicFiles);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        }

        return view;
    }
}