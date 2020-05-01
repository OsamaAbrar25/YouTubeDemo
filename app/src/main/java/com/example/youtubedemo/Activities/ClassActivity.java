package com.example.youtubedemo.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.youtubedemo.Adapters.ClassAdapter;
import com.example.youtubedemo.Models.ClassInfo;
import com.example.youtubedemo.PlaylistListener;
import com.example.youtubedemo.Repositories.PlaylistRepository;
import com.example.youtubedemo.R;

import java.util.ArrayList;

public class ClassActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ClassAdapter classAdapter;
    private PlaylistRepository playlistRepository;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        ClassActivity.this.overridePendingTransition(0, 19);
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getParent());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        playlistRepository = new PlaylistRepository(getApplication(), new PlaylistListener() {
            @Override
            public void onDataReceived(ArrayList<ClassInfo> arrayList) {
                ArrayList<String> h = arrayList.get(0).getSubjectName();
                classAdapter = new ClassAdapter(arrayList, getApplication());
                recyclerView.setAdapter(classAdapter);
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(ClassActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        playlistRepository.getPlaylist();
    }
}
