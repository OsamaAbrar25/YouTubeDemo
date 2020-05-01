package com.example.youtubedemo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubedemo.Activities.SubjectActivity;
import com.example.youtubedemo.Models.ClassInfo;
import com.example.youtubedemo.R;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.PlayListHolder> {

    private ArrayList<ClassInfo> arrayList;
    private Context context;


    public ClassAdapter(ArrayList<ClassInfo> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public PlayListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.class_list, parent, false);
        return new PlayListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayListHolder holder, int position) {
        holder.title_view.setText(arrayList.get(position).getClassName());
        //holder.desc_view.setText(arrayList.get(position).getDescription());
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
            //desc_view = itemView.findViewById(R.id.desc_view);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //String playlistId = arrayList.get(getAdapterPosition()).getPlaylistId();
                    //PreferenceManager.getDefaultSharedPreferences(context).edit().putString("PLAYLIST_ID",playlistId).apply();
                    Intent intent = new Intent(itemView.getContext(), SubjectActivity.class);
                    intent.putStringArrayListExtra("subjectList", arrayList.get(getAdapterPosition()).getSubjectName());
                   // String pl = arrayList.get(getAdapterPosition()).getPlaylistId();
                    intent.putStringArrayListExtra("playlistId", arrayList.get(getAdapterPosition()).getPlaylistId());
                    intent.putStringArrayListExtra("thumbnailList", arrayList.get(getAdapterPosition()).getThumbnailList());
                    itemView.getContext().startActivity(intent);


                }
            });
        }
    }
}
