package com.austinnightingale.drywalltally.job.dialogs;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;
import com.austinnightingale.drywalltally.db.Job;


public class InputDialog extends DialogFragment {
    public static final String TITLE = "title";
    public static final String INPUT = "input";
    public static final String ALLOW_EMPTY_INPUT = "disableOnEmpty";


    public static InputDialog newInstance(String column, String title, int inputType, boolean allowEmptyInput,  Fragment targetFragment) {
        Bundle args = new Bundle();
        args.putString(Job.COLUMN_NAME, column);
        args.putString(InputDialog.TITLE, title);
        args.putInt(InputDialog.INPUT, inputType);
        args.putBoolean(ALLOW_EMPTY_INPUT, allowEmptyInput);

        InputDialog inputDialog = new InputDialog();
        inputDialog.setArguments(args);
        inputDialog.setTargetFragment(targetFragment, 0);

        return inputDialog;
    }


    private InputListener callback;
    String title = "title not available";
    String column = "column not available";
    int inputType;
    boolean allowEmptyInput;

    public InputDialog() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle args = getArguments();

        if (!args.containsKey(Job.COLUMN_NAME)) {
            throw new NullPointerException("Args must include target fragment");
        } else if (!args.containsKey(TITLE)) {
            throw new NullPointerException("Args must include title for input");
        } else if (!args.containsKey(INPUT)) {
            throw new NullPointerException("Args must include input type");
        } else if (!args.containsKey(ALLOW_EMPTY_INPUT)) {
            throw new NullPointerException("Args must have disable on empty flag");
        } else {
            title = args.getString(InputDialog.TITLE);
            column = args.getString(Job.COLUMN_NAME);
            inputType = args.getInt(INPUT);
            allowEmptyInput = args.getBoolean(ALLOW_EMPTY_INPUT);
        }

        try {
            callback = (InputListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling fragment must implement NumberPickerListener Interface");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Context context = getActivity();
        return new MaterialDialog.Builder(context)
                .title(title)
                .inputType(inputType)
                .input(null, null, allowEmptyInput, (materialDialog, charSequence) -> {
                    ContentValues content = new ContentValues();
                    content.put(column, charSequence.toString());
                    callback.setInputResults(content);
                })
                .negativeText("Cancel")
                .onNegative((materialDialog, dialogAction) -> dismiss())
                .build();
    }
}
