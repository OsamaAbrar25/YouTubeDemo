package com.example.youtubedemo.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubedemo.Activities.VideoActivity;
import com.example.youtubedemo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectHolder> {
    private ArrayList<String> subjectList;
    private ArrayList<String> playlistid;
    private ArrayList<String> thumbnailList;

    public SubjectAdapter(ArrayList<String> subjectList, ArrayList<String> playlistid, ArrayList<String> thumbnailList) {
        this.subjectList = subjectList;
        this.playlistid = playlistid;
        this.thumbnailList = thumbnailList;
    }

    @NonNull
    @Override
    public SubjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.subject_list, parent, false);
        return new SubjectHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectHolder holder, int position) {
        holder.textView_subject.setText(subjectList.get(position));
        Picasso.get()
                .load(thumbnailList.get(position))
                //.transform(new CropSquareTransformation())
                .placeholder(R.drawable.books)
                .resize(500, 250)
                .into(holder.imageView_subjectView);

    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public class SubjectHolder extends RecyclerView.ViewHolder {
        ImageView imageView_subjectView;
        TextView textView_subject;
        public SubjectHolder(@NonNull final View itemView) {
            super(itemView);
            imageView_subjectView = itemView.findViewById(R.id.imageView_subject);
            textView_subject = itemView.findViewById(R.id.textView_subject);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), VideoActivity.class);
                    intent.putExtra("playlistId", playlistid.get(getAdapterPosition()));
                    itemView.getContext().startActivity(intent);
                }
            });

        }
    }
}
