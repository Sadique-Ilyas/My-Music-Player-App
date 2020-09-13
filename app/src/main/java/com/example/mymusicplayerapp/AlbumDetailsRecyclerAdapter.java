package com.example.mymusicplayerapp;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AlbumDetailsRecyclerAdapter extends RecyclerView.Adapter<AlbumDetailsRecyclerAdapter.MyAlbumViewHolder> {

    public static ArrayList<MusicFiles> albumSongs;
    private Context context;

    public AlbumDetailsRecyclerAdapter(Context context, ArrayList<MusicFiles> albumSongs) {
        this.context = context;
        this.albumSongs = albumSongs;
    }

    @NonNull
    @Override
    public MyAlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.music_item_layout, parent, false);
        return new MyAlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAlbumViewHolder holder, final int position) {
        holder.albumName.setText(albumSongs.get(position).getTitle());
        byte[] image = getAlbumArt(albumSongs.get(position).getPath());
        if (image != null) {
            Glide.with(context).asBitmap()
                    .load(image)
                    .into(holder.albumImage);
        } else {
            Glide.with(context)
                    .load(R.drawable.album_art)
                    .into(holder.albumImage);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayerActivity.class);
                intent.putExtra("sender", "albumDetails");
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumSongs.size();
    }

    private byte[] getAlbumArt(String uri) {
        try {
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(uri);
            byte[] art = retriever.getEmbeddedPicture();
            retriever.release();
            return art;
        } catch (IllegalArgumentException e) {
        }
        return null;
    }

    public class MyAlbumViewHolder extends RecyclerView.ViewHolder {

        ImageView albumImage;
        TextView albumName;

        public MyAlbumViewHolder(@NonNull View itemView) {
            super(itemView);

            albumImage = itemView.findViewById(R.id.music_img);
            albumName = itemView.findViewById(R.id.music_file_name);
        }
    }
}

