package com.austinnightingale.drywalltally.job.summary.tallysummary;

import android.view.View;
import android.widget.TextView;

import com.austinnightingale.drywalltally.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Austin on 9/26/2016.
 */

public class HalfStretchViewHolder extends TallyViewHolder {
    
    @BindView(R.id.half_s12_total)
     TextView halfS12;
    @BindView(R.id.half_s14_total)
     TextView halfS14;
    @BindView(R.id.half_s16_total)
     TextView halfS16;

    public HalfStretchViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Integer[] values) {
        halfS12.setText(String.valueOf(values[0]));
        halfS14.setText(String.valueOf(values[1]));
        halfS16.setText(String.valueOf(values[2]));
    }
}
