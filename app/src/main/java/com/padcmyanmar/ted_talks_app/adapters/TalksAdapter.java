package com.padcmyanmar.ted_talks_app.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.ted_talks_app.R;
import com.padcmyanmar.ted_talks_app.delegates.TalksDelegates;
import com.padcmyanmar.ted_talks_app.viewholders.TalksViewHolder;

public class TalksAdapter extends RecyclerView.Adapter{

    private TalksDelegates mTalksDelegate;
    public TalksAdapter(TalksDelegates talksDelegate){
        mTalksDelegate = talksDelegate;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.viewholder_talks, parent,false);
        return new TalksViewHolder(view,mTalksDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }


}
