package com.austinnightingale.android.drywalltally.job.options;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;


import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.austinnightingale.android.drywalltally.R;

import biz.kasual.materialnumberpicker.MaterialNumberPicker;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Austin on 9/6/2016.
 */
public class HeightChargeDialog extends DialogFragment {

    public static HeightChargeDialog newInstance(Fragment targetFragment) {
        HeightChargeDialog chargeDialog = new HeightChargeDialog();
        chargeDialog.setTargetFragment(targetFragment, 0);
        return chargeDialog;
    }

    @BindView(R.id.spinner_length)
    MaterialNumberPicker pickerLength;
    @BindView(R.id.spinner_width)
    MaterialNumberPicker pickerWidth;
    @BindView(R.id.spinner_height)
    MaterialNumberPicker pickerHeight;

    HeightChargeListener callback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            callback = (HeightChargeListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling fragment must implement HeightChargeListener Interface");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Context context = getActivity();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_height_charge, null);
        ButterKnife.bind(this, view);
        return new MaterialDialog.Builder(context)
                .title("Add Height Charge")
                .customView(view, false)
                .positiveText("Set")
                .negativeText("Cancel")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                        int length = pickerLength.getValue();
                        int width = pickerWidth.getValue();
                        int height = pickerHeight.getValue();
                        callback.addCharge(getString(R.string.format_height_charge, length, width, height));
                    }
                })
                .build();
    }
}
