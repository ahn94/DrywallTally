package com.austinnightingale.android.drywalltally.job.options.finish;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.austinnightingale.android.drywalltally.R;
import com.austinnightingale.android.drywalltally.db.HeightCharge;
import com.austinnightingale.android.drywalltally.job.dialogs.RemoveChargeListener;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnLongClick;


public class HeightChargeViewHolder extends RecyclerView.ViewHolder {

    RemoveChargeListener mCallback;
    @BindView(R.id.vocab)
    TextView textView;
    HeightCharge mCharge;

    boolean mAllowRemoval;

    public HeightChargeViewHolder(View itemView, RemoveChargeListener callback, boolean allowRemoval) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mCallback = callback;
        mAllowRemoval = allowRemoval;
    }

    public void bind(HeightCharge charge) {
        Log.d("holder", charge + " added to recycleView");
        mCharge = charge;
        textView.setText(charge.heightCharge());
    }

    @OnLongClick(R.id.view_height_upcharge)
    public boolean removeChargeClick() {
        if (mAllowRemoval) {
            mCallback.openRemoveChargeDialog(mCharge);
        }
        return false;
    }
}
