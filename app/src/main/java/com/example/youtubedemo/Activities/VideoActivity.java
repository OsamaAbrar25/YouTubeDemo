package com.example.youtubedemo.Activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.youtubedemo.R;
import com.example.youtubedemo.Adapters.VideoAdapter;
import com.example.youtubedemo.Models.VideoInfo;
import com.example.youtubedemo.VideoListener;
import com.example.youtubedemo.Repositories.VideoRepository;
import com.example.youtubedemo.YouTubeConfig;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class VideoActivity extends YouTubeBaseActivity {

    private RecyclerView recyclerView2;
    private RecyclerView.LayoutManager layoutManager;
    private VideoAdapter videoAdapter;
    private VideoRepository videoRepository;
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    private String video_id;
    String playlistId;
    private YouTubePlayer youTubePlayer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        youTubePlayerView = findViewById(R.id.playerview);
        recyclerView2 = findViewById(R.id.recycler_view2);
        layoutManager = new LinearLayoutManager(getParent());
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setHasFixedSize(true);

        playlistId = getIntent().getStringExtra("playlistId");

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {


                //video_id = PreferenceManager.getDefaultSharedPreferences(getApplication()).getString("VIDEO_ID", null);
                youTubePlayer1=youTubePlayer;

            }
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        play();


        videoRepository = new VideoRepository(getApplication(), playlistId,  new VideoListener() {
            @Override
            public void onDataReceived(ArrayList<VideoInfo> arrayList) {
                videoAdapter = new VideoAdapter(arrayList, getApplication(), youTubePlayer1,onInitializedListener);
                recyclerView2.setAdapter(videoAdapter);

            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(VideoActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        videoRepository.getVideoList();

    }
    public void play() {
        youTubePlayerView.initialize(YouTubeConfig.getApiKey(), onInitializedListener);
    }
}
