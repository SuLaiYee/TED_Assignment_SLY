package com.padcmyanmar.ted_talks_app.data.vos;

import com.google.gson.annotations.SerializedName;

public class SegmentsVO {
    @SerializedName("segment_id")
    private int segmentId;
    @SerializedName("title")
    private String segmentTitle;
    @SerializedName("imageUrl")
    private String imageUrl;

    public String getSegmentTitle() {
        return segmentTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getSegmentId() {
        return segmentId;
    }
}
