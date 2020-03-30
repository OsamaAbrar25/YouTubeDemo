package com.example.youtubedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.VolleyError;

import java.util.ArrayList;

public class Screen1Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PlayListAdapter playListAdapter;
    private PlaylistRepository playlistRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1);

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getParent());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        playlistRepository = new PlaylistRepository(getApplication(), new PlaylistListener() {
            @Override
            public void onDataReceived(ArrayList<PlaylistInfo> arrayList) {
                playListAdapter = new PlayListAdapter(arrayList, getApplication());
                recyclerView.setAdapter(playListAdapter);
            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(Screen1Activity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        playlistRepository.getPlaylist();
    }
}
