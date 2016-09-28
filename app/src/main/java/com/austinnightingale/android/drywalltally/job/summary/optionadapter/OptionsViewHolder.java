package com.austinnightingale.android.drywalltally.job.summary.optionadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.austinnightingale.android.drywalltally.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OptionsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_value)
     TextView textValue;
    @BindView(R.id.text_label)
     TextView textLabel;

    OptionsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bindOption(String[] option) {
        textValue.setText(option[0]);
        textLabel.setText(option[1]);
    }
}
