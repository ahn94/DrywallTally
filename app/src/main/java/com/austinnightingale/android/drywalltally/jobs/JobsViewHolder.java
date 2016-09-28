package com.austinnightingale.android.drywalltally.jobs;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.austinnightingale.android.drywalltally.R;
import com.austinnightingale.android.drywalltally.job.Utils;
import com.austinnightingale.android.drywalltally.db.Job;


import butterknife.BindView;
import butterknife.ButterKnife;


public class JobsViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener, RecyclerView.OnLongClickListener{

    JobsCallBack jobsCallBack;

    @BindView(R.id.job_name)
    TextView jobName;
    @BindView(R.id.job_date)
    TextView jobDate;
    @BindView(R.id.square_footage)
    TextView footage;


    private Job mJob;

    public JobsViewHolder(View itemView, JobsCallBack jobsCallBack) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        this.jobsCallBack = jobsCallBack;
    }

    public void bindName(Job name) {
        mJob = name;
        jobName.setText(mJob.jobName());
        jobDate.setText(DateFormat.get(mJob.createdOn()));
        footage.setText(Utils.printTotalSqFt(mJob) + " Sq. Ft.");
    }


    @Override
    public void onClick(View view) {
        jobsCallBack.openJobActivity(mJob.jobID(), mJob.jobName());
    }

    @Override
    public boolean onLongClick(View view) {
        jobsCallBack.openFragment(DeleteJobDialog.newInstance(mJob.jobID(), mJob.jobName()));
        return true;
    }
}
