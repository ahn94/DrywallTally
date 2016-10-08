package com.austinnightingale.android.drywalltally.job.options.tallyarea;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.austinnightingale.android.drywalltally.R;
import com.austinnightingale.android.drywalltally.db.TallyArea;

import java.util.List;

import rx.functions.Action1;


public class TallyAreaAdapter extends RecyclerView.Adapter<TallyAreaViewHolder> implements Action1<List<TallyArea>>{

    private List<TallyArea> mTallyAreas;
    private TallyCallback callback;

    public TallyAreaAdapter(TallyCallback callback) {
        this.callback = callback;
    }


    @Override
    public TallyAreaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_jobs_list, parent, false);
        return new TallyAreaViewHolder(view, callback);
    }

    @Override
    public void onBindViewHolder(TallyAreaViewHolder holder, int position) {
        holder.bind(mTallyAreas.get(position));
    }

    @Override
    public int getItemCount() {
        return mTallyAreas == null ? 0 : mTallyAreas.size();
    }

    @Override
    public void call(List<TallyArea> tallyAreas) {
        mTallyAreas = tallyAreas;
        notifyDataSetChanged();
    }

}
