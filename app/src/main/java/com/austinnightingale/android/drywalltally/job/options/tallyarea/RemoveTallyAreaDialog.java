package com.austinnightingale.android.drywalltally.job.options.tallyarea;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.afollestad.materialdialogs.MaterialDialog;
import com.austinnightingale.android.drywalltally.TallyApplication;
import com.austinnightingale.android.drywalltally.db.Job;
import com.austinnightingale.android.drywalltally.db.TallyArea;
import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Inject;


public class RemoveTallyAreaDialog extends DialogFragment {

    public static RemoveTallyAreaDialog newInstance(int tallyID, String areaName) {
        Bundle bundle = new Bundle();
        bundle.putInt(TallyArea.ID, tallyID);
        bundle.putString(TallyArea.AREA_NAME, areaName);
        RemoveTallyAreaDialog removeTallyAreaDialog = new RemoveTallyAreaDialog();
        removeTallyAreaDialog.setArguments(bundle);
        return removeTallyAreaDialog;
    }

    @Inject
    BriteDatabase db;

    private int areaId;
    private String areaName;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((TallyApplication) getActivity().getApplication()).getComponent().inject(this);
        areaId = getArguments().getInt(Job.ID);
        areaName = getArguments().getString(Job.JOB_NAME);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Context context = getActivity();
        return new MaterialDialog.Builder(context)
                .title("Alert!!")
                .content("Delete " + areaName + " Job Area?")
                .positiveText("Delete")
                .negativeText("Cancel")
                .onPositive((materialDialog, dialogAction) -> {
                    db.delete(TallyArea.TABLE, TallyArea.ID + " = ?", String.valueOf(areaId));
                })
                .build();
    }
}
