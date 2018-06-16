package com.padcmyanmar.ted_talks_app.network;

public interface TalksDataAgent {

    void loadTalkList(int page, String accessToken);

    void loadTalkPlayList(int page, String accessToken);

    void loadTalkPodCasts(int page, String accessToken);

}
