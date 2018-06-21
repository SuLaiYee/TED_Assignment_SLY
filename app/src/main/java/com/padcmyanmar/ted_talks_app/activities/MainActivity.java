package com.padcmyanmar.ted_talks_app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.padcmyanmar.ted_talks_app.R;
import com.padcmyanmar.ted_talks_app.adapters.TalksAdapter;
import com.padcmyanmar.ted_talks_app.data.models.TedTalksModel;
import com.padcmyanmar.ted_talks_app.data.vos.TedTalksVO;
import com.padcmyanmar.ted_talks_app.delegates.TalksDelegates;

public class MainActivity extends BaseActivity implements TalksDelegates{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rvTalks = findViewById(R.id.rv_talks);
        TalksAdapter talksAdapter = new TalksAdapter(this);
        rvTalks.setAdapter(talksAdapter);
        rvTalks.setLayoutManager(new LinearLayoutManager(getApplicationContext()
                ,LinearLayoutManager.VERTICAL,false));
        TedTalksModel.getObjectReference().loadTalkList();
        TedTalksModel.getObjectReference().loadTalkPlayList();
        TedTalksModel.getObjectReference().loadTadPodCasts();


    }

    @Override
    public void onTapTalks(TedTalksVO talks) {
        Intent intent = new Intent(getApplicationContext(),TalksDetailsActivity.class);
        startActivity(intent);
    }
}
