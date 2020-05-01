package com.example.youtubedemo.Repositories;

import android.content.Context;
import android.preference.PreferenceManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.youtubedemo.Models.VideoInfo;
import com.example.youtubedemo.VideoListener;
import com.example.youtubedemo.VolleySingleton;
import com.example.youtubedemo.YouTubeConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VideoRepository {
    private Context context;
    private VideoListener videoListener;
    private ArrayList<VideoInfo> arrayList;
    private String playlist_Id;

    public VideoRepository(Context context, String playlist_Id, VideoListener videoListener) {
        this.context = context;
        this.videoListener = videoListener;
        this.playlist_Id = playlist_Id;
    }

    public void getVideoList() {
        //String playlist_id = PreferenceManager.getDefaultSharedPreferences(context).getString("PLAYLIST_ID", null);
        String json_url = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults="+ YouTubeConfig.MAX_RESULTS_PER_PAGE+"&playlistId="+playlist_Id+"&key="+YouTubeConfig.getApiKey();

        arrayList = new ArrayList<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, json_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray;
                try {
                    jsonArray = response.getJSONArray("items");
                    int i;
                    for(i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                        String title, description, thumbnail;
                        JSONObject jsonObject2 = jsonObject.getJSONObject("snippet");
                        title = jsonObject2.getString("title");
                        description = jsonObject2.getString("description");

                        JSONObject jsonObject3 = jsonObject2.getJSONObject("resourceId");
                        String videoId = jsonObject3.getString("videoId");

                        JSONObject jsonObject4 = jsonObject2.getJSONObject("thumbnails");
                        JSONObject jsonObject5 = jsonObject4.getJSONObject("medium");
                        thumbnail = jsonObject5.getString("url");

                        VideoInfo videoInfo = new VideoInfo(title, description, videoId, thumbnail);
                        arrayList.add(videoInfo);
                        if (videoListener != null){
                            videoListener.onDataReceived(arrayList);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                videoListener.onError(error);
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
