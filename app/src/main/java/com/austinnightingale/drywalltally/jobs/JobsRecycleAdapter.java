package com.austinnightingale.drywalltally.jobs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.austinnightingale.drywalltally.R;
import com.austinnightingale.drywalltally.db.DAO;
import com.austinnightingale.drywalltally.db.Job;

import java.util.List;

import rx.functions.Action1;

public class JobsRecycleAdapter extends RecyclerView.Adapter<JobsViewHolder> implements Action1<List<Job>>{

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    List<Job> jobs;

    private JobsCallBack jobsCallBack;
    private DAO dao;

    public JobsRecycleAdapter(List<Job> jobs, JobsCallBack jobsCallBack, DAO dao) {
        this.jobs = jobs;
        this.dao = dao;
        this.jobsCallBack = jobsCallBack;
    }

    @Override
    public JobsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_jobs_list, parent, false);
        return new JobsViewHolder(view, jobsCallBack, dao);
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