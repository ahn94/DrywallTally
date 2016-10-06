package com.austinnightingale.android.drywalltally.job.options.tallyarea;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.InputType;

import com.afollestad.materialdialogs.MaterialDialog;
import com.austinnightingale.android.drywalltally.TallyApplication;
import com.austinnightingale.android.drywalltally.db.DAO;
import com.austinnightingale.android.drywalltally.db.TallyArea;
import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Inject;

/**
 * Created by Austin on 9/30/2016.
 */

public class NewJobAreaDialog extends DialogFragment {

    public static NewJobAreaDialog newInstance(int jobId) {
        Bundle bundle = new Bundle();
        bundle.putInt(TallyArea.JOB_ID, jobId);
        NewJobAreaDialog newJobAreaDialog = new NewJobAreaDialog();
        newJobAreaDialog.setArguments(bundle);
        return newJobAreaDialog;
    }

    @Inject
    BriteDatabase db;
    @Inject
    DAO dao;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (!getArguments().containsKey(TallyArea.JOB_ID)) {
            throw new IllegalArgumentException("doesn't contain job id.");
        }

        ((TallyApplication) getActivity().getApplication()).getComponent().inject(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Context context = getActivity();

        return new MaterialDialog.Builder(context)
                .title("Set Job Area Name")
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input(null, null, false, (materialDialog, charSequence) -> {
                })
                .positiveText("Create")
                .negativeText("Cancel")
                .onPositive((materialDialog, dialogAction) -> {
                    String name = materialDialog.getInputEditText().getText().toString();
                    dao.insertTallyArea(new TallyArea.ContentBuilder()
                            .jobName(name)
                            .jobId(getArguments().getInt(TallyArea.JOB_ID))
                            .build());
                }).build();
    }
}
