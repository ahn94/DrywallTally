package com.austinnightingale.android.drywalltally.jobs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.austinnightingale.android.drywalltally.R;
import com.austinnightingale.android.drywalltally.db.Job;

import java.util.List;

import rx.functions.Action1;

public class JobsRecycleAdapter extends RecyclerView.Adapter<JobsViewHolder> implements Action1<List<Job>>{

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    List<Job> jobs;

    JobsCallBack jobsCallBack;

    public JobsRecycleAdapter(List<Job> jobs, JobsCallBack jobsCallBack) {
        this.jobs = jobs;
        this.jobsCallBack = jobsCallBack;
    }

    @Override
    public JobsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_jobs_list, parent, false);
        return new JobsViewHolder(view, jobsCallBack);
    }

    @Override
    public void onBindViewHolder(JobsViewHolder holder, int position) {
        holder.bindName(jobs.get(position));
    }

    @Override
    public int getItemCount() {
        return jobs == null ? 0 : jobs.size();
    }

    @Override
    public void call(List<Job> jobs) {
        this.jobs = jobs;
        notifyDataSetChanged();
    }

}