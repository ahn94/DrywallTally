package com.austinnightingale.drywalltally.jobs;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.austinnightingale.drywalltally.R;
import com.austinnightingale.drywalltally.db.DAO;
import com.austinnightingale.drywalltally.db.Job;
import com.austinnightingale.drywalltally.db.TallyArea;
import com.austinnightingale.drywalltally.job.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;


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
        dao.obsTallyListByJobId(job.jobID())
                .subscribe(new Subscriber<List<TallyArea>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<TallyArea> tallyAreas) {
                        String formated = Utils.jobTotalFtString(tallyAreas) + " Sq. Ft.";
                        footage.setText(formated);
                    }
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
