package com.example.youtubedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class Screen2Activity extends YouTubeBaseActivity {

    private RecyclerView recyclerView2;
    private RecyclerView.LayoutManager layoutManager;
    private VideoAdapter videoAdapter;
    private VideoRepository videoRepository;
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    private String video_id;
    private YouTubePlayer youTubePlayer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        youTubePlayerView = findViewById(R.id.playerview);
        recyclerView2 = findViewById(R.id.recycler_view2);
        layoutManager = new LinearLayoutManager(getParent());
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setHasFixedSize(true);

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {


                video_id = PreferenceManager.getDefaultSharedPreferences(getApplication()).getString("VIDEO_ID", null);
                youTubePlayer1=youTubePlayer;

            }
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        play();


        videoRepository = new VideoRepository(getApplication(), new VideoListener() {
            @Override
            public void onDataReceived(ArrayList<VideoInfo> arrayList) {
                videoAdapter = new VideoAdapter(arrayList, getApplication(), youTubePlayer1,onInitializedListener);
                recyclerView2.setAdapter(videoAdapter);



            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(Screen2Activity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        videoRepository.getVideoList();

    }
    public void play() {
        youTubePlayerView.initialize(YouTubeConfig.getApiKey(), onInitializedListener);
    }
}
