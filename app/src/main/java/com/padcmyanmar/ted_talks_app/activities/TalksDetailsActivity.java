package com.padcmyanmar.ted_talks_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.padcmyanmar.ted_talks_app.R;
import com.padcmyanmar.ted_talks_app.adapters.NextTalksAdapter;
import com.padcmyanmar.ted_talks_app.data.models.TedTalksModel;
import com.padcmyanmar.ted_talks_app.data.vos.TedTalksVO;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TalksDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_talks_scroll_detail)
    ImageView ivTalksMainDetail;

    @BindView(R.id.tv_talker_detail_name)
    TextView tvTalkerName;
    @BindView(R.id.tv_talks_details_content)
    TextView tvTalksContent;
    @BindView(R.id.tv_time)
    TextView tvTalksTime;
    @BindView(R.id.tv_talks_detail)
    TextView tvTalksDetails;
    //    @BindView(R.id.tv_speaker)
//    TextView tvPoster;
    @BindView(R.id.iv_profile)
    ImageView ivProfile;
    @BindView(R.id.tv_profile_name)
    TextView tvTalkerProfileName;
    @BindView(R.id.tv_profile_job)
    TextView tvTalkerJob;
    @BindView(R.id.tv_profile_description)
    TextView tvTalkerDescription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talks_detail);

        ButterKnife.bind(this, this);

        String talkID = getIntent().getStringExtra("TalkID");
        Log.d("NewsDetailActivity", "newsId: " + talkID);

        TedTalksVO talks = TedTalksModel.getObjectReference().getTedTalksById(talkID);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RecyclerView rvNextList = findViewById(R.id.rv_next_list);
        rvNextList.setAdapter(new NextTalksAdapter());
        rvNextList.setLayoutManager(new LinearLayoutManager(getApplicationContext()
                , LinearLayoutManager.VERTICAL, false));

        tvTalkerName.setText(talks.getSpeaker().getName());
        tvTalksContent.setText(talks.getTitle());
        //String time = Util.getHourMinuteSecond(Double.parseDouble(String.valueOf(tedTalks.getTalkDurationSecs())));

        Long milliseconds = Long.valueOf(talks.getDurationInSecs());
        Log.d("MM", "MM"+ milliseconds);
        long hour = TimeUnit.MILLISECONDS.toHours(milliseconds);
        long minute = TimeUnit.MILLISECONDS.toMinutes(milliseconds) - TimeUnit.HOURS.toMinutes(
                        TimeUnit.MILLISECONDS.toHours(milliseconds));
        long sec = TimeUnit.MILLISECONDS.toSeconds(milliseconds) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(milliseconds));
        tvTalksTime.setText(hour+" : "+minute+" : "+ sec);
        tvTalksDetails.setText(talks.getDescription());
        //tv.setVisibility(View.GONE);
        tvTalkerProfileName.setText(talks.getSpeaker().getName());
        tvTalkerJob.setVisibility(View.INVISIBLE);
        tvTalkerDescription.setVisibility(View.GONE);


        // To do image
        Glide.with(ivTalksMainDetail.getContext())
                .load(talks.getImageUrl()) // Remote URL of image.
                .into(ivTalksMainDetail);
    }
}
