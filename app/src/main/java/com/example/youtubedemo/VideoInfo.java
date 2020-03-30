package com.example.youtubedemo;

public class VideoInfo {
    private String videoId;
    private String title;
    private String description;
    private String thumbnail;

    public VideoInfo(String title, String description, String videoId, String thumbnail) {
        this.title = title;
        this.description = description;
        this.videoId = videoId;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
