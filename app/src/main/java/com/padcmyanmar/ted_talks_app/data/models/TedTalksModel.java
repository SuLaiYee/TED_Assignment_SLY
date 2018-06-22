package com.padcmyanmar.ted_talks_app.data.models;

import android.util.Log;

import com.padcmyanmar.ted_talks_app.data.vos.TedTalksVO;
import com.padcmyanmar.ted_talks_app.events.SuccessGetTalksEvent;
import com.padcmyanmar.ted_talks_app.network.HttpUrlConnectionDataAgentImpl;
import com.padcmyanmar.ted_talks_app.network.RetrofitDataAgentImpl;
import com.padcmyanmar.ted_talks_app.network.TalksDataAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

public class TedTalksModel {
    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    static private TedTalksModel objInstance;

    private TalksDataAgent mTedTalkDataAgent;

    private Map<String, TedTalksVO> mTedTalksMap;

    private TedTalksModel() {
        // mTedTalkDataAgent = HttpUrlConnectionDataAgentImpl.getObjectReference();
        //mTedTalkDataAgent = OkHttpDataAgentImpl.getObjectReference();
        mTedTalkDataAgent = RetrofitDataAgentImpl.getObjectReference();
        mTedTalksMap = new HashMap<>();
        EventBus.getDefault().register(this);
    }

    public static TedTalksModel getObjectReference() {
        if (objInstance == null) {
            objInstance = new TedTalksModel();
        }
        return objInstance;
    }

    public void loadTalkList() {
        Log.d("Hello", "loadNewsList call");
        mTedTalkDataAgent.loadTalkList(1, DUMMY_ACCESS_TOKEN);
    }

    public void loadTalkPlayList() {

        mTedTalkDataAgent.loadTalkPlayList(1, DUMMY_ACCESS_TOKEN);
    }

    public void loadTadPodCasts() {

        mTedTalkDataAgent.loadTalkPodCasts(1, DUMMY_ACCESS_TOKEN);
    }



    public TedTalksVO getTedTalksById(String talkId) {

        return mTedTalksMap.get(talkId);

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetTedTalks(SuccessGetTalksEvent event) {

        for (TedTalksVO talks : event.getTalksList()) {

            mTedTalksMap.put(talks.getTalkId(), talks);
        }
    }
}
