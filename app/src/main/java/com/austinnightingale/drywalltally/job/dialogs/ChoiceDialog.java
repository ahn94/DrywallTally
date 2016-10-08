package com.austinnightingale.drywalltally.job.dialogs;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.afollestad.materialdialogs.MaterialDialog;
import com.austinnightingale.drywalltally.db.Job;


public class ChoiceDialog extends DialogFragment {
    public static final String TITLE = "title";
    public static final String RES = "resId";
    private InputListener callback;

    String title = "title not available";
    int resId;
    String[] values;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle arguments = getArguments();

        if (!arguments.containsKey(Job.COLUMN_NAME)) {
            throw new NullPointerException("Calling fragment must add column name using Job.COLUMN_NAME key");
        } else if (!arguments.containsKey(InputDialog.TITLE)) {
            throw new NullPointerException("Calling fragment must add title for dialog using NumberInputDialog.TITLE key");
        } else if (!arguments.containsKey(RES)) {
            throw new NullPointerException("No resId array specified in arguments");
        }

        resId = arguments.getInt(RES);
        values = getResources().getStringArray(resId);
        title = arguments.getString(InputDialog.TITLE);

        try {
            callback = (InputListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling fragment must implement InputListener Interface");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Context context = getActivity();
        return new MaterialDialog.Builder(context)
                .title(title)
                .items(resId)
                .itemsCallbackSingleChoice(0, (materialDialog, view, i, charSequence) -> true)
                .onPositive((materialDialog, dialogAction) -> {
                    String column = getArguments().getString(Job.COLUMN_NAME);
                    ContentValues content = new ContentValues();
                    content.put(column, values[materialDialog.getSelectedIndex()]);
                    callback.setInputResults(content);
                })
                .positiveText("Set")
                .negativeText("Cancel")
                .build();
    }
}
