package com.example.youtubedemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlaylistRepository {
    private Context context;
    private PlaylistListener playlistListener;
    private ArrayList<PlaylistInfo> arrayList;

    public PlaylistRepository(Context context, PlaylistListener playlistListener) {
        this.context = context;
        this.playlistListener = playlistListener;
    }

    void getPlaylist() {
        String json_url = "https://www.googleapis.com/youtube/v3/playlists?part=snippet&channelId="+YouTubeConfig.getChannelId()+"&key="+YouTubeConfig.getApiKey();

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
                        String playlistId = jsonObject.getString("id");


                        String title, description;
                        JSONObject jsonObject2 = jsonObject.getJSONObject("snippet");
                        title = jsonObject2.getString("title");
                        description = jsonObject2.getString("description");
                        PlaylistInfo playlistInfo = new PlaylistInfo(playlistId, title, description);
                        arrayList.add(playlistInfo);
                        if (playlistListener != null){
                            playlistListener.onDataReceived(arrayList);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                playlistListener.onError(error);
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
