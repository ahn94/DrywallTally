package com.austinnightingale.drywalltally.job;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import com.afollestad.materialdialogs.MaterialDialog;
import com.austinnightingale.drywalltally.R;
import com.austinnightingale.drywalltally.TallyApplication;
import com.austinnightingale.drywalltally.db.DAO;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Austin on 10/19/2016.
 */

public class JobReportDialog extends DialogFragment {

    public static JobReportDialog newInstance() {
        return new JobReportDialog();
    }

    @Inject
    DAO dao;
    @BindView(R.id.check_include_options)
    CheckBox checkOptions;
    @BindView(R.id.check_only_half_totals)
    CheckBox checkTotalsOnly;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((TallyApplication) getActivity().getApplication()).getComponent().inject(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Context context = getActivity();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_job_report, null);
        ButterKnife.bind(this, view);
        return new MaterialDialog.Builder(context)
                .title("Job Report Options")
                .customView(view, false)
                .positiveText("Report")
                .negativeText("Cancel")
                .onPositive((dialog, which) -> {
                    boolean includeOptions = checkOptions.isChecked();
                    boolean totalsOnly = checkTotalsOnly.isChecked();
                    ReportListener reportListener = (ReportListener) getActivity();
                    reportListener.sendReportWithOptions(totalsOnly, includeOptions);
                })
                .build();

    }
}
