package com.austinnightingale.drywalltally.jobs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.InputType;

import com.afollestad.materialdialogs.MaterialDialog;
import com.austinnightingale.drywalltally.TallyApplication;
import com.austinnightingale.drywalltally.db.Job;
import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Inject;

public class NewJobDialog extends DialogFragment {

    public static NewJobDialog newInstance() {
        return new NewJobDialog();
    }

    @Inject
    BriteDatabase db;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((TallyApplication) getActivity().getApplication()).getComponent().inject(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Context context = getActivity();

        return new MaterialDialog.Builder(context)
                .title("Set Job Name")
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input(null, null, false, (materialDialog, charSequence) -> {
                })
                .positiveText("Create")
                .negativeText("Cancel")
                .onPositive((materialDialog, dialogAction) -> {
                    String name = materialDialog.getInputEditText().getText().toString();
                    db.insert(Job.TABLE, new Job.ContentBuilder().jobName(name).build());
                }).build();

    }
}
