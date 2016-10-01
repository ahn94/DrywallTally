package com.austinnightingale.android.drywalltally.job.options.tallyarea;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.austinnightingale.android.drywalltally.R;
import com.austinnightingale.android.drywalltally.db.TallyArea;
import com.austinnightingale.android.drywalltally.job.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;


public class TallyAreaViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.job_name)
    TextView name;
    @BindView(R.id.square_footage)
    TextView squareFootage;
    private TallyCallback callback;
    private TallyArea mTallyArea;


    public TallyAreaViewHolder(View itemView, TallyCallback callback) {
        super(itemView);
        this.callback = callback;
        ButterKnife.bind(this, itemView);
    }

    public void bind(TallyArea tallyArea) {
        mTallyArea = tallyArea;
        name.setText(tallyArea.areaName());
        squareFootage.setText(Utils.areaTotalSqFt(tallyArea) + " Sq. Ft.");
    }

    @OnClick(R.id.item_view)
    public void clicked() {
        callback.openTallyForArea(mTallyArea.Id());
    }

    @OnLongClick(R.id.item_view)
    public boolean longClicked() {
        callback.showDialog(mTallyArea.Id(), mTallyArea.areaName());
        return true;
    }
}
