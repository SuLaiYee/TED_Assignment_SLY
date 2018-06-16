package com.padcmyanmar.ted_talks_app.data.vos;

import java.util.List;

public class TedTalksVO {
    private String talkId;
    private String title;
    private SpeakerVO speaker;
    private String imageUrl;
    private int durationInSecs;
    private String description;
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
