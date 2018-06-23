package com.padcmyanmar.ted_talks_app.viewholders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.ted_talks_app.R;
import com.padcmyanmar.ted_talks_app.data.vos.TedTalksVO;
import com.padcmyanmar.ted_talks_app.delegates.TalksDelegates;
import com.padcmyanmar.ted_talks_app.utils.MilliSecToMinSec;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TalksViewHolder extends RecyclerView.ViewHolder {
    private TedTalksVO mTalks;
    private TalksDelegates mTalksDelegates;

    @BindView(R.id.iv_talks)
    ImageView ivTalks;

    @BindView(R.id.tv_talker_name)
    TextView tvTalkerName;

    @BindView(R.id.tv_talks_content)
    TextView tvTalkerContent;

    @BindView(R.id.tv_time)
    TextView tvTalksTime;

    public TalksViewHolder(View itemView, TalksDelegates talksDelegates) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        mTalksDelegates = talksDelegates;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTalksDelegates.onTapTalks(mTalks);
            }
        });
        // ivTalks = itemView.findViewById(R.id.iv_talks);

    }

    public void setTalksData(TedTalksVO talks) {
        mTalks = talks;
        tvTalkerName.setText(talks.getSpeaker().getName());
        tvTalkerContent.setText(talks.getTitle());

        String dateFormatted = MilliSecToMinSec.getHourMinuteSecond(Long.parseLong(String.valueOf(mTalks.getDurationInSecs())));

        tvTalksTime.setText(dateFormatted);
        Glide.with(ivTalks.getContext())
                .load(talks.getImageUrl()) // Remote URL of image.
                .into(ivTalks);


    }

}
