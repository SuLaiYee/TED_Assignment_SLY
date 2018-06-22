package com.padcmyanmar.ted_talks_app.network.responses;

import com.google.gson.annotations.SerializedName;
import com.padcmyanmar.ted_talks_app.data.vos.TedTalksVO;

import java.util.List;

public class GetTalksResponse {
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("apiVersion")
    private String apiVersion;
    @SerializedName("page")
    private int page;
    @SerializedName("ted_talks")
    private List<TedTalksVO> talks;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public int getPage() {
        return page;
    }

    public List<TedTalksVO> getTalks() {
        return talks;
    }

    public boolean isResponseOK() {
        return (code == 200 && talks != null);
    }
}
