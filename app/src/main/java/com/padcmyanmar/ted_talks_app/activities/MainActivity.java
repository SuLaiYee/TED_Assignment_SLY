package com.padcmyanmar.ted_talks_app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.padcmyanmar.ted_talks_app.R;
import com.padcmyanmar.ted_talks_app.adapters.TalksAdapter;
import com.padcmyanmar.ted_talks_app.data.models.TedTalksModel;
import com.padcmyanmar.ted_talks_app.data.vos.TedTalksVO;
import com.padcmyanmar.ted_talks_app.delegates.TalksDelegates;
import com.padcmyanmar.ted_talks_app.events.SuccessGetTalksEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity implements TalksDelegates {

    private TalksAdapter mTalksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rvTalks = findViewById(R.id.rv_talks);
        mTalksAdapter = new TalksAdapter(this);
        rvTalks.setAdapter(mTalksAdapter);
        rvTalks.setLayoutManager(new LinearLayoutManager(getApplicationContext()
                , LinearLayoutManager.VERTICAL, false));
        TedTalksModel.getObjectReference().loadTalkList();
//      TedTalksModel.getObjectReference().loadTalkPlayList();
//      TedTalksModel.getObjectReference().loadTadPodCasts();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onTapTalks(TedTalksVO talks) {
        Intent intent = new Intent(getApplicationContext(), TalksDetailsActivity.class);
        intent.putExtra("TalkID", talks.getTalkId());
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetTalks(SuccessGetTalksEvent event) {
        Log.d("onSuccessGetTalks", "onSuccessGetTalks: " + event.getTalksList().size());
        mTalksAdapter.setTalksList(event.getTalksList());
    }

}
