package com.austinnightingale.drywalltally.job.summary.tallysummary;

import android.view.View;
import android.widget.TextView;

import com.austinnightingale.drywalltally.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Austin on 9/26/2016.
 */

public class MoldViewHolder extends TallyViewHolder {

    @BindView(R.id.mold_8_total)
    TextView mold8Total;
    @BindView(R.id.mold_12_total)
    TextView mold12Total;


    public MoldViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Integer[] values) {
        mold8Total.setText(String.valueOf(values[0]));
        mold12Total.setText(String.valueOf(values[1]));

    }
}
