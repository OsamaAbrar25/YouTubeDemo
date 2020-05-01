package com.example.youtubedemo.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ClassInfo implements Parcelable {
    private String className;
    private ArrayList<String> subjectName;
    private ArrayList<String> playlistId;
    private ArrayList<String> thumbnailList;


    public ClassInfo(String className, ArrayList<String> subjectName, ArrayList<String> playlistId, ArrayList<String> thumbnailList) {
        this.className = className;
        this.subjectName = (ArrayList<String>) subjectName.clone();
        this.playlistId = (ArrayList<String>) playlistId.clone();
        this.thumbnailList = (ArrayList<String>) thumbnailList.clone();
    }

    protected ClassInfo(Parcel in) {
        className = in.readString();
        subjectName = in.createStringArrayList();
        playlistId = in.createStringArrayList();
        thumbnailList = in.createStringArrayList();
    }


    public static final Creator<ClassInfo> CREATOR = new Creator<ClassInfo>() {
        @Override
        public ClassInfo createFromParcel(Parcel in) {
            return new ClassInfo(in);
        }

        @Override
        public ClassInfo[] newArray(int size) {
            return new ClassInfo[size];
        }
    };

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ArrayList<String> getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(ArrayList<String> subjectName) {
        this.subjectName = subjectName;
    }

    public ArrayList<String> getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(ArrayList<String> playlistId) {
        this.playlistId = playlistId;
    }

    public ArrayList<String> getThumbnailList() {
        return thumbnailList;
    }

    public void setThumbnailList(ArrayList<String> thumbnailList) {
        this.thumbnailList = thumbnailList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(className);
        dest.writeStringList(subjectName);
        dest.writeStringList(playlistId);
        dest.writeStringList(thumbnailList);
    }
}
