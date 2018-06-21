package com.padcmyanmar.ted_talks_app.data.models;

import com.padcmyanmar.ted_talks_app.network.HttpUrlConnectionDataAgentImpl;
import com.padcmyanmar.ted_talks_app.network.TalksDataAgent;

public class TedTalksModel {
    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    static private TedTalksModel objInstance;
    private TalksDataAgent mTedTalkDataAgent;

    private TedTalksModel() {
        mTedTalkDataAgent = HttpUrlConnectionDataAgentImpl.getObjectReference();
    }

    public static TedTalksModel getObjectReference() {
        if (objInstance == null) {
            objInstance = new TedTalksModel();
        }
        return objInstance;
    }

    public void loadTalkList() {
        mTedTalkDataAgent.loadTalkList(1, DUMMY_ACCESS_TOKEN);
    }

    public void loadTalkPlayList() {

        mTedTalkDataAgent.loadTalkPlayList(1, DUMMY_ACCESS_TOKEN);
    }

    public void loadTadPodCasts() {

        mTedTalkDataAgent.loadTalkPodCasts(1, DUMMY_ACCESS_TOKEN);
    }
}
