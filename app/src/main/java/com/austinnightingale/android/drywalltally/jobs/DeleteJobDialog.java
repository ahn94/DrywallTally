package com.austinnightingale.android.drywalltally.jobs;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.austinnightingale.android.drywalltally.TallyApplication;
import com.austinnightingale.android.drywalltally.db.HeightCharge;
import com.austinnightingale.android.drywalltally.db.Job;
import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Inject;

public class DeleteJobDialog extends DialogFragment {

    public static DeleteJobDialog newInstance(int jobId, String jobName) {
        Bundle bundle = new Bundle();
        bundle.putInt(Job.ID, jobId);
        bundle.putString(Job.JOB_NAME, jobName);
        DeleteJobDialog deleteJobDialog = new DeleteJobDialog();
        deleteJobDialog.setArguments(bundle);
        return deleteJobDialog;
    }

    @Inject
    BriteDatabase db;

    private int jobID;
    private String jobName;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((TallyApplication) getActivity().getApplication()).getComponent().inject(this);
        jobID = getArguments().getInt(Job.ID);
        jobName = getArguments().getString(Job.JOB_NAME);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Context context = getActivity();
        return new MaterialDialog.Builder(context)
                .title("Alert!!")
                .content("Delete " + jobName + "?")
                .positiveText("Delete")
                .negativeText("Cancel")
                .onPositive((materialDialog, dialogAction) -> {
                    db.delete(Job.TABLE, Job.ID + " = ?", String.valueOf(jobID));
                    db.delete(HeightCharge.TABLE, HeightCharge.JOB_ID + " = ?", String.valueOf(jobID));
                })
                .build();
    }
}
