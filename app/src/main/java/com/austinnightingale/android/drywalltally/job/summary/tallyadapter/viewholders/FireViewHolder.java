package com.austinnightingale.android.drywalltally.job.summary.tallyadapter.viewholders;

import android.view.View;
import android.widget.TextView;

import com.austinnightingale.android.drywalltally.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Austin on 9/26/2016.
 */

public class FireViewHolder extends TallyViewHolder {

    @BindView(R.id.fire_8_total)
    TextView fire8Total;
    @BindView(R.id.fire_9_total)
    TextView fire9Total;
    @BindView(R.id.fire_10_total)
    TextView fire10Total;
    @BindView(R.id.fire_12_total)
    TextView fire12Total;
    @BindView(R.id.fire_14_total)
    TextView fire14Total;
    @BindView(R.id.fire_16_total)
    TextView fire16Total;

    public FireViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Integer[] values) {
        fire8Total.setText(String.valueOf(values[0]));
        fire9Total.setText(String.valueOf(values[1]));
        fire10Total.setText(String.valueOf(values[2]));
        fire12Total.setText(String.valueOf(values[3]));
        fire14Total.setText(String.valueOf(values[4]));
        fire16Total.setText(String.valueOf(values[5]));
    }
}
