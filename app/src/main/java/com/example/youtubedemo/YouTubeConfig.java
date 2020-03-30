package com.example.youtubedemo;

public class YouTubeConfig {
    private static String API_KEY = "AIzaSyB3k6aWccoD3sWBc9TZ_SKgdaQRtScKPIU";
    private static String CHANNEL_ID = "UC-ZiHA7zyNA_lOKo86VY6VA";
    public static String MAX_RESULTS_PER_PAGE = "50";

    public YouTubeConfig() {
    }

    public static String getApiKey() {
        return API_KEY;
    }

    public static String getChannelId() {
        return CHANNEL_ID;
    }
}
