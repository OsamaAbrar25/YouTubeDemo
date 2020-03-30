package com.example.youtubedemo;

import com.android.volley.VolleyError;

import java.util.ArrayList;

public interface VideoListener {
    void onDataReceived(ArrayList<VideoInfo> arrayList);
    void onError(VolleyError error);
}
