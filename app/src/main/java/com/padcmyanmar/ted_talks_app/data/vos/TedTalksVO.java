package com.padcmyanmar.ted_talks_app.data.vos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TedTalksVO {
    @SerializedName("talk_id")
    private String talkId;

    @SerializedName("title")
    private String title;

    @SerializedName("speaker")
    private SpeakerVO speaker;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("durationInSec")
    private int durationInSecs;

    @SerializedName("description")
    private String description;

    @SerializedName("tag")
    private List<TagVO> tags;

    public String getTitle() {
        return title;
    }

    public SpeakerVO getSpeaker() {
        return speaker;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getDurationInSecs() {
        return durationInSecs;
    }

    public String getDescription() {
        return description;
    }

    public List<TagVO> getTags() {
        return tags;
    }

    public String getTalkId() {
        return talkId;
    }
}
