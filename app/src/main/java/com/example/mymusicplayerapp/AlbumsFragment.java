package com.example.mymusicplayerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.mymusicplayerapp.MainActivity.albumFiles;

public class AlbumsFragment extends Fragment {

    RecyclerView recyclerView;
    AlbumAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_albums, container, false);

        recyclerView = view.findViewById(R.id.recyclerView_albums);
        recyclerView.setHasFixedSize(true);

        if (albumFiles.size() != 0) {

            adapter = new AlbumAdapter(getContext(), albumFiles);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            recyclerView.setAdapter(adapter);
        }

        return view;
    }
}