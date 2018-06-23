package com.padcmyanmar.ted_talks_app.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.ted_talks_app.R;
import com.padcmyanmar.ted_talks_app.data.vos.TedTalksVO;
import com.padcmyanmar.ted_talks_app.delegates.TalksDelegates;
import com.padcmyanmar.ted_talks_app.viewholders.TalksViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TalksAdapter extends RecyclerView.Adapter<TalksViewHolder> {

    private TalksDelegates mTalksDelegate;

    private List<TedTalksVO> mTalksList;

    public TalksAdapter(TalksDelegates talksDelegate) {
        mTalksDelegate = talksDelegate;
        mTalksList = new ArrayList<>();
    }

    @NonNull
    @Override
    public TalksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.viewholder_talks, parent, false);
        return new TalksViewHolder(view, mTalksDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull TalksViewHolder holder, int position) {
        holder.setTalksData(mTalksList.get(position));
    }

    @Override
    public int getItemCount() {
        return mTalksList.size();
    }

    public void setTalksList(List<TedTalksVO> talksList) {
        mTalksList = talksList;
        notifyDataSetChanged();
    }
}
