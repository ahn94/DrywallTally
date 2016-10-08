package com.austinnightingale.drywalltally.job.summary.jobsummary;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.austinnightingale.drywalltally.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JobSummaryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_value)
     TextView textValue;
    @BindView(R.id.text_label)
     TextView textLabel;

    JobSummaryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bindOption(String[] option) {
        textValue.setText(option[0]);
        textLabel.setText(option[1]);
    }
}
