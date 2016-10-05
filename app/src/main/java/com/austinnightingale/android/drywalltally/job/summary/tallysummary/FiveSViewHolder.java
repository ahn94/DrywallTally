package com.austinnightingale.android.drywalltally.job.summary.tallysummary;

import android.view.View;
import android.widget.TextView;

import com.austinnightingale.android.drywalltally.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Austin on 9/26/2016.
 */

public class FiveSViewHolder extends TallyViewHolder {

        @BindView(R.id.fiveE_s12_total)
        TextView fiveEs12Total;


    public FiveSViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Integer[] values) {
        fiveEs12Total.setText(String.valueOf(values[0]));
    }

}
