package com.padcmyanmar.ted_talks_app.data.vos;

import java.util.List;

public class TedPlayListsVO {
    private int playlistId;
    private String title;
    private String imageUrl;
    private int totalTalks;

    public int getPlaylistId() {
        return playlistId;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getTotalTalks() {
        return totalTalks;
    }

    public String getDescription() {
        return description;
    }

    public List<TedTalksVO> getTalksInPlayList() {
        return talksInPlayList;
    }

    private String description;
    private List<TedTalksVO> talksInPlayList;

}
