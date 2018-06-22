package com.padcmyanmar.ted_talks_app.events;

import com.padcmyanmar.ted_talks_app.data.vos.TedTalksVO;

import java.util.List;

public class SuccessGetTalksEvent {
    private List<TedTalksVO> talksList;

    public SuccessGetTalksEvent(List<TedTalksVO> talksList) {
        this.talksList = talksList;
    }

    public List<TedTalksVO> getTalksList() {
        return talksList;
    }
}
