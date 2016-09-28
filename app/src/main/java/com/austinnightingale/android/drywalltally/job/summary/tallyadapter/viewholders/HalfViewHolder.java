package com.austinnightingale.android.drywalltally.job.summary.tallyadapter.viewholders;

import android.view.View;
import android.widget.TextView;

import com.austinnightingale.android.drywalltally.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HalfViewHolder extends TallyViewHolder {

    @BindView(R.id.half_8_total)
    TextView half8Total;
    @BindView(R.id.half_9_total)
    TextView half9Total;
    @BindView(R.id.half_10_total)
    TextView half10Total;
    @BindView(R.id.half_12_total)
    TextView half12Total;
    @BindView(R.id.half_14_total)
    TextView half14Total;
    @BindView(R.id.half_16_total)
    TextView half16Total;


    public HalfViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Integer[] values) {
        half8Total.setText(String.valueOf(values[0]));
        half9Total.setText(String.valueOf(values[1]));
        half10Total.setText(String.valueOf(values[2]));
        half12Total.setText(String.valueOf(values[3]));
        half14Total.setText(String.valueOf(values[4]));
        half16Total.setText(String.valueOf(values[5]));
    }
}
