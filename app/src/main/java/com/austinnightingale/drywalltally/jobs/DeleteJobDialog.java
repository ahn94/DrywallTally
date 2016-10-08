package com.austinnightingale.drywalltally.jobs;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.afollestad.materialdialogs.MaterialDialog;
import com.austinnightingale.drywalltally.TallyApplication;
import com.austinnightingale.drywalltally.db.DAO;
import com.austinnightingale.drywalltally.db.Job;

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
    DAO dao;

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
                .onPositive((materialDialog, dialogAction) -> dao.deleteJobInfo(jobID))
                .build();
    }
}
