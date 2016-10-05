package com.austinnightingale.android.drywalltally.job.summary.tallysummary;

import android.view.View;
import android.widget.TextView;

import com.austinnightingale.android.drywalltally.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Austin on 10/3/2016.
 */

public class LiteTotalViewHolder extends TallyViewHolder {
    @BindView(R.id.half_ceil_8_total)
    TextView liteTotal8Total;
    @BindView(R.id.half_ceil_9_total)
    TextView liteTotal9Total;
    @BindView(R.id.half_ceil_10_total)
    TextView liteTotal10Total;
    @BindView(R.id.half_ceil_12_total)
    TextView liteTotal12Total;
    @BindView(R.id.half_ceil_14_total)
    TextView liteTotal14Total;
    @BindView(R.id.half_ceil_16_total)
    TextView liteTotal16Total;

    public LiteTotalViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Integer[] values) {
        liteTotal8Total.setText(String.valueOf(values[0]));
        liteTotal9Total.setText(String.valueOf(values[1]));
        liteTotal10Total.setText(String.valueOf(values[2]));
        liteTotal12Total.setText(String.valueOf(values[3]));
        liteTotal14Total.setText(String.valueOf(values[4]));
        liteTotal16Total.setText(String.valueOf(values[5]));
    }
}
