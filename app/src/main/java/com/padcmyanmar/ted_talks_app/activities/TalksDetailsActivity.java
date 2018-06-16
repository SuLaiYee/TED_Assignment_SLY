package com.padcmyanmar.ted_talks_app.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.padcmyanmar.ted_talks_app.R;
import com.padcmyanmar.ted_talks_app.adapters.NextTalksAdapter;

public class TalksDetailsActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    private void init(){
        setContentView(R.layout.activity_talks_detail);
        setUpRecyclerView();
    }

    private void setUpRecyclerView(){

        RecyclerView rvNextList =  findViewById(R.id.rv_next_list);
        rvNextList.setAdapter(new NextTalksAdapter());
        rvNextList.setLayoutManager(new LinearLayoutManager(getApplicationContext()
                ,LinearLayoutManager.VERTICAL,false));
    }
}
