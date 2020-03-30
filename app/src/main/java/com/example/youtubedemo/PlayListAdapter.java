package com.example.youtubedemo;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.PlayListHolder> {

    private ArrayList<PlaylistInfo> arrayList;
    private Context context;


    public PlayListAdapter(ArrayList<PlaylistInfo> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public PlayListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.playlist_list, parent, false);
        return new PlayListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayListHolder holder, int position) {
        holder.title_view.setText(arrayList.get(position).getTitle());
        holder.desc_view.setText(arrayList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class PlayListHolder extends RecyclerView.ViewHolder{

        TextView title_view, desc_view;
        public PlayListHolder(@NonNull final View itemView) {
            super(itemView);
            title_view = itemView.findViewById(R.id.title_view);
            desc_view = itemView.findViewById(R.id.desc_view);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String playlistId = arrayList.get(getAdapterPosition()).getPlaylistId();
                    PreferenceManager.getDefaultSharedPreferences(context).edit().putString("PLAYLIST_ID",playlistId).apply();
                    Intent intent = new Intent(itemView.getContext(), Screen2Activity.class);
                    itemView.getContext().startActivity(intent);

                }
            });
        }
    }
}
