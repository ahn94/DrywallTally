package com.austinnightingale.drywalltally.job.options.tallyarea;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.austinnightingale.drywalltally.R;
import com.austinnightingale.drywalltally.db.TallyArea;
import com.austinnightingale.drywalltally.job.Utils;

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
    private Context mContext;


    public TallyAreaViewHolder(View itemView, TallyCallback callback) {
        super(itemView);
        this.callback = callback;
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
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
        PopupMenu menu = new PopupMenu(mContext, itemView);
        menu.inflate(R.menu.modify);
        menu.show();
        menu.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.item_rec_edit:
                    callback.showEditNameDialog(TallyArea.TABLE, 
                            TallyArea.AREA_NAME, 
                            mContext.getString(R.string.dialog_name_change_title, mTallyArea.areaName()),
                            mTallyArea.Id()
                    );
                    return true;
                case R.id.item_rec_remove:
                    callback.showRemoveDialog(mTallyArea.Id(), mTallyArea.areaName());
                    return true;
                default:
                    return false;
            }
        });
        return true;
    }
}
