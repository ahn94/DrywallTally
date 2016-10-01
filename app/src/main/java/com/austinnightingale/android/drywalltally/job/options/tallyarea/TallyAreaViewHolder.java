package com.austinnightingale.android.drywalltally.job.options.tallyarea;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.austinnightingale.android.drywalltally.R;
import com.austinnightingale.android.drywalltally.db.TallyArea;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TallyAreaViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.vocab)
    TextView textView;
    private TallyCallback callback;
    private TallyArea mTallyArea;


    public TallyAreaViewHolder(View itemView, TallyCallback callback) {
        super(itemView);
        this.callback = callback;
        ButterKnife.bind(this, itemView);
    }

    public void bind(TallyArea tallyArea) {
        mTallyArea = tallyArea;
        textView.setText(tallyArea.areaName());
    }

    @OnClick(R.id.view_height_upcharge)
    public void clicked() {
        Log.d("tag", "onClick: clicked");
        callback.openTallyForArea(mTallyArea.Id());
    }
}
