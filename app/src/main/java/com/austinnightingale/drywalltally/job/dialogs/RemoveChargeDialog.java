package com.austinnightingale.drywalltally.job.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;
import com.austinnightingale.drywalltally.db.HeightCharge;


public class RemoveChargeDialog extends DialogFragment {
    public static final String TITLE = "title";

    public static RemoveChargeDialog newInstance(int chargeId, String title,  Fragment targetFragment) {
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putInt(HeightCharge.ID, chargeId);

        RemoveChargeDialog removeDialog = new RemoveChargeDialog();
        removeDialog.setArguments(args);
        removeDialog.setTargetFragment(targetFragment, 0);

        return removeDialog;
    }

    String title;
    int chargeId;
    RemoveChargeListener callback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle args = getArguments();

        if (!args.containsKey(TITLE)) {
            throw new NullPointerException("Args must include title for input");
        } else if (!args.containsKey(HeightCharge.ID)) {
            throw new NullPointerException("Args must include height charge id");
        } else {
            title = args.getString(InputDialog.TITLE);
            chargeId = args.getInt(HeightCharge.ID);
        }

        try {
            callback = (RemoveChargeListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling fragment must implement NumberPickerListener Interface");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Context context = getActivity();

        return new MaterialDialog.Builder(context)
                .title("Remove")
                .content(title)
                .positiveText("Remove")
                .negativeText("Cancel")
                .onPositive((materialDialog, dialogAction) -> {
                    callback.removeChargeWithId(chargeId);
                })
                .build();
    }
}
