package com.example.youtubedemo.Repositories;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.youtubedemo.Models.ClassInfo;
import com.example.youtubedemo.Models.PlaylistInfo;
import com.example.youtubedemo.PlaylistListener;
import com.example.youtubedemo.VolleySingleton;
import com.example.youtubedemo.YouTubeConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlaylistRepository {
    private Context context;
    private PlaylistListener playlistListener;
    ArrayList<PlaylistInfo> arrayList;
    private ArrayList<String> subjectName = new ArrayList<>();
    private ArrayList<String> playlistIdList = new ArrayList<>();
    private ArrayList<ClassInfo> arrayList2 = new ArrayList<>();
    private ArrayList<String> thumbnailList = new ArrayList<>();
    ArrayList<String> discarded = new ArrayList<>();

    public PlaylistRepository(Context context, PlaylistListener playlistListener) {
        this.context = context;
        this.playlistListener = playlistListener;
    }

    public void getPlaylist() {
        String json_url = "https://www.googleapis.com/youtube/v3/playlists?part=snippet&channelId="+ YouTubeConfig.getChannelId()+"&key="+YouTubeConfig.getApiKey();

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

                        String[] separated = title.split(" ", 2);

                        String temp = separated[0];
                        if (discarded.contains(temp)) {
                            continue;
                        }
                        discarded.add(temp);
                        int x;
                        for (x=0;x<jsonArray.length();x++) {
                            JSONObject j = (JSONObject) jsonArray.get(x);
                            String playlistId2 = j.getString("id");
                            JSONObject j2 = j.getJSONObject("snippet");
                            String title2 = j2.getString("title");
                            JSONObject t = j2.getJSONObject("thumbnails");
                            JSONObject quality = t.getJSONObject("medium");
                            String thumbnail = quality.getString("url");

                            String[] separated2 = title2.split(" ", 2);
                            if (temp.equals(separated2[0])) {
                                subjectName.add(separated2[1]);
                                playlistIdList.add(playlistId2);
                                thumbnailList.add(thumbnail);

                            }
                        }


                        ClassInfo classInfo = new ClassInfo(temp, subjectName, playlistIdList, thumbnailList);
                        arrayList2.add(classInfo);



                        /*PlaylistInfo playlistInfo = new PlaylistInfo(playlistId, title, description);
                        arrayList.add(playlistInfo);*/
                        if (playlistListener != null){
                            playlistListener.onDataReceived(arrayList2);
                        }
                        subjectName.clear();
                        playlistIdList.clear();
                        thumbnailList.clear();
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
