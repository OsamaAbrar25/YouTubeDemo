package com.example.youtubedemo.Adapters;

import android.content.Context;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubedemo.R;
import com.example.youtubedemo.Models.VideoInfo;
import com.google.android.youtube.player.YouTubePlayer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder>{

    private ArrayList<VideoInfo> arrayList;
    private String video_id;
    private Context context;
    private YouTubePlayer youTubePlayer;
    private YouTubePlayer.OnInitializedListener onInitializedListener;


    public VideoAdapter(ArrayList<VideoInfo> arrayList, Context context, YouTubePlayer youTubePlayer, YouTubePlayer.OnInitializedListener onInitializedListener) {
        this.arrayList = arrayList;
        this.context = context;
        this.youTubePlayer = youTubePlayer;
        this.onInitializedListener = onInitializedListener;
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.videos_list, parent, false);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, final int position) {
        holder.title_view2.setText(arrayList.get(position).getTitle());
        holder.desc_view2.setText(arrayList.get(position).getDescription());
        Picasso.get()
                .load(arrayList.get(position).getThumbnail())
                .into(holder.thumbnailView);

        /*holder.thumbnailView.initialize(YouTubeConfig.getApiKey(), new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(arrayList.get(position).getVideoId());
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        youTubeThumbnailLoader.release();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });*/

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder{

        private TextView title_view2, desc_view2;
        private ImageView thumbnailView;
        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            title_view2 = itemView.findViewById(R.id.title_view2);
            desc_view2 = itemView.findViewById(R.id.desc_view2);

            thumbnailView = itemView.findViewById(R.id.thumbnail_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    video_id = arrayList.get(getAdapterPosition()).getVideoId();
                    //PreferenceManager.getDefaultSharedPreferences(context).edit().putString("VIDEO_ID", video_id).apply();
                    Toast.makeText(context, ""+getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    youTubePlayer.loadVideo(video_id);
                }
            });


        }
    }
}
