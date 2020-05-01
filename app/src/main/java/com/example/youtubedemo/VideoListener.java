package com.example.youtubedemo;

import com.android.volley.VolleyError;
import com.example.youtubedemo.Models.VideoInfo;

import java.util.ArrayList;

public interface VideoListener {
    void onDataReceived(ArrayList<VideoInfo> arrayList);
    void onError(VolleyError error);
}
