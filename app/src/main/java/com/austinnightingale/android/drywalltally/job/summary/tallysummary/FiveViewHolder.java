package com.austinnightingale.android.drywalltally.job.summary.tallysummary;

import android.view.View;
import android.widget.TextView;

import com.austinnightingale.android.drywalltally.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Austin on 9/26/2016.
 */

public class FiveViewHolder extends TallyViewHolder {
    @BindView(R.id.fiveE_8_total)
    TextView fiveE8Total;
    @BindView(R.id.fiveE_9_total)
    TextView fiveE9Total;
    @BindView(R.id.fiveE_10_total)
    TextView fiveE10Total;
    @BindView(R.id.fiveE_12_total)
    TextView fiveE12Total;
    @BindView(R.id.fiveE_14_total)
    TextView fiveE14Total;

    public FiveViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Integer[] values) {
        fiveE8Total.setText(String.valueOf(values[0]));
        fiveE9Total.setText(String.valueOf(values[1]));
        fiveE10Total.setText(String.valueOf(values[2]));
        fiveE12Total.setText(String.valueOf(values[3]));
        fiveE14Total.setText(String.valueOf(values[4]));

    }
}
