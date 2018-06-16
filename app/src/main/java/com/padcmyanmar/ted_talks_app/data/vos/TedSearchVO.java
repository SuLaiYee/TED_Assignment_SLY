package com.padcmyanmar.ted_talks_app.data.vos;

public class TedSearchVO {
    private int searchId;
    private String title;
    private String description;

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

    private String imageUrl;
    private String resultType;
    private int resultId;
}
