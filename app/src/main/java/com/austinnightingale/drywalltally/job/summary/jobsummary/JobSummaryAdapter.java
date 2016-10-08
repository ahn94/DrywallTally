package com.austinnightingale.drywalltally.job.summary.jobsummary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.austinnightingale.drywalltally.R;
import com.austinnightingale.drywalltally.db.Job;
import com.austinnightingale.drywalltally.job.Report;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

public class JobSummaryAdapter extends RecyclerView.Adapter<JobSummaryViewHolder> implements Action1<Job> {

    List<String[]> options;
    int type;

    public JobSummaryAdapter(int type) {
        this.type = type;
        options = new ArrayList<>();
    }

    @Override
    public JobSummaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_upcharge, parent, false);
        return new JobSummaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JobSummaryViewHolder holder, int position) {
        holder.bindOption(options.get(position));
    }

    @Override
    public int getItemCount() {
        return options == null ? 0 : options.size();
    }

    @Override
    public void call(Job job) {
        if (type == 1) {
            options = Report.getOptionValueLabelPairs(job);
        } else {
            options = Report.getExtraValueLabelPairs(job);
        }
        notifyDataSetChanged();
    }

}
