package com.padcmyanmar.ted_talks_app.data.vos;

import com.google.gson.annotations.SerializedName;

public class TedSearchVO {
    @SerializedName("search_result_id")
    private int searchId;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("result_type")
    private String resultType;
    @SerializedName("result_id")
    private int resultId;

    public int getSearchId() {
        return searchId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getResultType() {
        return resultType;
    }

    public int getResultId() {
        return resultId;
    }


}
