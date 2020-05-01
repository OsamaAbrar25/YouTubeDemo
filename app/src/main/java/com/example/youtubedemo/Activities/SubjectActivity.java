package com.example.youtubedemo.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.youtubedemo.Adapters.SubjectAdapter;
import com.example.youtubedemo.Models.ClassInfo;
import com.example.youtubedemo.R;

import java.util.ArrayList;

public class SubjectActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ArrayList<String> subjectList = new ArrayList<>();
    ArrayList<String> playlistId;
    ArrayList<String> thumbnailList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        recyclerView = findViewById(R.id.recyclerView_subject);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplication(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        subjectList = getIntent().getStringArrayListExtra("subjectList");
        playlistId = getIntent().getStringArrayListExtra("playlistId");
        thumbnailList = getIntent().getStringArrayListExtra("thumbnailList");
        SubjectAdapter subjectAdapter = new SubjectAdapter(subjectList, playlistId, thumbnailList);
        recyclerView.setAdapter(subjectAdapter);

    }
}
