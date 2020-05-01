package com.example.youtubedemo.Models;

public class PlaylistInfo {
    private String playlistId;
    private String title;
    private String description;

    public PlaylistInfo(String playlistId, String title, String description) {
        this.playlistId = playlistId;
        this.title = title;
        this.description = description;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
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
}
