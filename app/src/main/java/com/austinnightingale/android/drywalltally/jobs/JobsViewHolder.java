package com.austinnightingale.android.drywalltally.jobs;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.austinnightingale.android.drywalltally.R;
import com.austinnightingale.android.drywalltally.db.DAO;
import com.austinnightingale.android.drywalltally.db.Job;
import com.austinnightingale.android.drywalltally.job.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class JobsViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener, RecyclerView.OnLongClickListener {

    JobsCallBack jobsCallBack;
    DAO dao;
    @BindView(R.id.job_name)
    TextView jobName;
    @BindView(R.id.job_date)
    TextView jobDate;
    @BindView(R.id.square_footage)
    TextView footage;


    private Job mJob;

    public JobsViewHolder(View itemView, JobsCallBack jobsCallBack, DAO doa) {
        super(itemView);
        this.dao = doa;
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        this.jobsCallBack = jobsCallBack;
    }

    public void bindName(Job job) {
        mJob = job;
        jobName.setText(mJob.jobName());
        jobDate.setText(DateFormat.get(mJob.createdOn()));
        dao.getTallyAreaListForJobId(mJob.jobID())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tallyAreas -> {
                    String formated = Utils.jobTotalFtString(tallyAreas) + " Sq. Ft.";
                    footage.setText(formated);
                });

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
