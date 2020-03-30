package com.example.youtubedemo;

import com.android.volley.VolleyError;

import java.util.ArrayList;

public interface PlaylistListener {
    void onDataReceived(ArrayList<PlaylistInfo> arrayList);
    void onError(VolleyError error);
}
