package com.austinnightingale.drywalltally.job.dialogs;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.InputType;

import com.afollestad.materialdialogs.MaterialDialog;
import com.austinnightingale.drywalltally.TallyApplication;
import com.austinnightingale.drywalltally.db.DAO;
import com.austinnightingale.drywalltally.db.Job;

import javax.inject.Inject;

/**
 * Created by Austin on 10/26/2016.
 */

public class EditNameDialog extends DialogFragment {

    public static final String TITLE = "title";
    public static final String TABLE = "table";
    public static final String ID = "_id";


    public static EditNameDialog newInstance(String table, String column, String title,  int id) {
        Bundle args = new Bundle();
        args.putString(Job.COLUMN_NAME, column);
        args.putString(InputDialog.TITLE, title);
        args.putString(TABLE, table);
        args.putInt(ID, id);

        EditNameDialog nameDialog = new EditNameDialog();
        nameDialog.setArguments(args);
        return nameDialog;
    }


    @Inject
    DAO dao;

    String title = "title not available";
    String column = "column not available";
    String table = "title not available";
    int id;
    boolean allowEmptyInput = false;

    public EditNameDialog() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle args = getArguments();

        if (!args.containsKey(Job.COLUMN_NAME)) {
            throw new NullPointerException("Args must include column name");
        } else if (!args.containsKey(TITLE)) {
            throw new NullPointerException("Args must include title for input");
        } else if (!args.containsKey(TABLE)) {
            throw new NullPointerException("Args must include table name");
        } else if (!args.containsKey(ID)) {
            throw new NullPointerException("Args must have id");
        } else {
            title = args.getString(InputDialog.TITLE);
            column = args.getString(Job.COLUMN_NAME);
            table = args.getString(TABLE);
            id = args.getInt(ID);
        }

        ((TallyApplication) getActivity().getApplication()).getComponent().inject(this);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Context context = getActivity();
        return new MaterialDialog.Builder(context)
                .title(title)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input(null, null, allowEmptyInput, (materialDialog, charSequence) -> {
                    ContentValues content = new ContentValues();
                    content.put(column, charSequence.toString());
                    dao.getDb().update(table, content, ID+ " = ?", String.valueOf(id));
                })
                .negativeText("Cancel")
                .onNegative((materialDialog, dialogAction) -> dismiss())
                .build();
    }
}
