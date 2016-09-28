package com.austinnightingale.android.drywalltally.job.summary.optionadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.austinnightingale.android.drywalltally.R;
import com.austinnightingale.android.drywalltally.db.Job;
import com.austinnightingale.android.drywalltally.job.Report;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

public class OptionsAdapter extends RecyclerView.Adapter<OptionsViewHolder> implements Action1<Job> {

    List<String[]> options;

    public OptionsAdapter() {
        options = new ArrayList<>();
    }

    @Override
    public OptionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_upcharge, parent, false);
        return new OptionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OptionsViewHolder holder, int position) {
        holder.bindOption(options.get(position));
    }

    @Override
    public int getItemCount() {
        return options == null ? 0 : options.size();
    }

    @Override
    public void call(Job job) {
        options = Report.getOptionValueLabelPairs(job);
        notifyDataSetChanged();
    }

}
