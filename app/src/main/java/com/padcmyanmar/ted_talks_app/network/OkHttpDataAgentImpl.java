package com.padcmyanmar.ted_talks_app.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class OkHttpDataAgentImpl implements TalksDataAgent {

    private  static OkHttpDataAgentImpl objectReference;

    private OkHttpClient mOkHttpClient;

    private OkHttpDataAgentImpl(){
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
    }
    public static OkHttpDataAgentImpl getObjectReference() {
        if (objectReference == null) {
            objectReference = new OkHttpDataAgentImpl();
        }
        return objectReference;
    }

    @Override
    public void loadTalkList(int page, String accessToken) {

    }

    @Override
    public void loadTalkPlayList(int page, String accessToken) {

    }

    @Override
    public void loadTalkPodCasts(int page, String accessToken) {

    }
}
