package com.example.youtubedemo;

import com.android.volley.VolleyError;
import com.example.youtubedemo.Models.ClassInfo;

import java.util.ArrayList;

public interface PlaylistListener {
    void onDataReceived(ArrayList<ClassInfo> arrayList);
    void onError(VolleyError error);
}
