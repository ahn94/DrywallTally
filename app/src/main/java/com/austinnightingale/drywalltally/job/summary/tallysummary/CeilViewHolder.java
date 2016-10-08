package com.austinnightingale.drywalltally.job.summary.tallysummary;

import android.view.View;
import android.widget.TextView;

import com.austinnightingale.drywalltally.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CeilViewHolder extends TallyViewHolder {
        @BindView(R.id.ceil_8_total)
    TextView ceil8Total;
    @BindView(R.id.ceil_9_total)
    TextView ceil9Total;
    @BindView(R.id.ceil_10_total)
    TextView ceil10Total;
    @BindView(R.id.ceil_12_total)
    TextView ceil12Total;
    @BindView(R.id.ceil_14_total)
    TextView ceil14Total;
    @BindView(R.id.ceil_16_total)
    TextView ceil16Total;

    public CeilViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Integer[] values) {
        ceil8Total.setText(String.valueOf(values[0]));
        ceil9Total.setText(String.valueOf(values[1]));
        ceil10Total.setText(String.valueOf(values[2]));
        ceil12Total.setText(String.valueOf(values[3]));
        ceil14Total.setText(String.valueOf(values[4]));
        ceil16Total.setText(String.valueOf(values[5]));
    }
}
