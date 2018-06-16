package com.padcmyanmar.ted_talks_app.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.padcmyanmar.ted_talks_app.delegates.TalksDelegates;

public class TalksViewHolder extends RecyclerView.ViewHolder {
    private TalksDelegates mTalksDelegates;
    public TalksViewHolder(View itemView, TalksDelegates talksDelegates) {
        super(itemView);
        mTalksDelegates = talksDelegates;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTalksDelegates.onTapTalks();
            }
        });
    }
}
