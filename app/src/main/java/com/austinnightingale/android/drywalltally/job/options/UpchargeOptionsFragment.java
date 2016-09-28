package com.austinnightingale.android.drywalltally.job.options;


import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.austinnightingale.android.drywalltally.R;
import com.austinnightingale.android.drywalltally.TallyApplication;
import com.austinnightingale.android.drywalltally.db.Job;

import com.austinnightingale.android.drywalltally.job.dialogs.InputDialog;
import com.austinnightingale.android.drywalltally.job.dialogs.InputListener;
import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class UpchargeOptionsFragment extends Fragment implements InputListener {

    @BindView(R.id.value_bull_corner) TextView textBullCorner;
    @BindView(R.id.value_window_wrap3) TextView textWindowWrap3;
    @BindView(R.id.check_bullnose_adapter) CheckBox checkBullAdapter;
    @BindView(R.id.value_window_wrap4) TextView textWindowWrap4;
    @BindView(R.id.value_arch_wrap) TextView textArchWrap;
    @BindView(R.id.value_arch_corner) TextView textArchCorner;
    @BindView(R.id.value_door_wrap) TextView textDoorWrap;
    @BindView(R.id.value_opening_wrap) TextView textOpeningWrap;
    @BindView(R.id.value_ltrim) TextView textLtrim;

    @Inject
    BriteDatabase db;
    Subscription subscription;
    Job mJob;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TallyApplication) getActivity().getApplication()).getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcharges_options, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        subscription.unsubscribe();
    }

    public int getID() {
        int id;
        Intent intent = getActivity().getIntent();
        if (intent.hasExtra(Job.ID)) {
            id = intent.getIntExtra(Job.ID, -1);
        } else {
            throw new IllegalArgumentException("no job Id in Intent");
        }
        return id;
    }

    private void refreshView(Job job) {
        textBullCorner.setText(String.valueOf(job.bullCorners()));

        if (job.bullAdapterFlag() == 0) {
            checkBullAdapter.setChecked(false);
        } else {
            checkBullAdapter.setChecked(true);
        }

        textWindowWrap3.setText(String.valueOf(job.windowWrap3S()));
        textWindowWrap4.setText(String.valueOf(job.windowWrap4S()));
        textArchWrap.setText(String.valueOf(job.archWrap()));
        textArchCorner.setText(String.valueOf(job.archCorner()));
        textDoorWrap.setText(String.valueOf(job.doorWrap()));
        textOpeningWrap.setText(String.valueOf(job.openingWrap()));
        textLtrim.setText(String.valueOf(job.lTrim()));

    }


    private void showDialog(String column, String title) {
        InputDialog numberInputDialog = InputDialog.newInstance(
                column,
                title,
                InputType.TYPE_CLASS_NUMBER,
                false,
                this
        );
        numberInputDialog.show(getFragmentManager(), "number picker");
    }

    @Override
    public void onResume() {
        super.onResume();
        subscription = db.createQuery(Job.TABLE, Job.getJobwithIDQuery, String.valueOf(getID()))
                .mapToOne(Job.mapper())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Job>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d("jobinfo", e.toString());
                    }

                    @Override
                    public void onNext(Job job) {
                        refreshView(job);
                        mJob = job;
                        Log.d("jobinfo", job.toString());
                    }
                });
    }

    @Override
    public void setInputResults(ContentValues values) {
        updateJob(values);
    }

    public void updateJob(ContentValues values) {
        db.update(Job.TABLE, values, Job.ID + " = ?", String.valueOf(getID()));
    }

    @OnClick(R.id.view_bull_corner)
    public void bullCornerClicked() {
        showDialog(Job.BULL_CORNER, "Bullnose Corner");
    }

    @OnClick(R.id.view_bull_adapter)
    public void bullAdapterClicked() {
        ContentValues values = new ContentValues();
        if (mJob.bullAdapterFlag() != 0) {
            values.put(Job.BULL_ADAPTER_FLAG, 0);
        } else {
            values.put(Job.BULL_ADAPTER_FLAG, 1);
        }
        updateJob(values);
    }

    @OnClick(R.id.view_window_wrap3)
    public void windowWrap3Clicked() {
        showDialog(Job.WINDOW_WRAP_3SIDE, "Window Wrap - 3 Sided");
    }

    @OnClick(R.id.view_window_wrap4)
    public void windowWrap4Clicked() {
        showDialog(Job.WINDOW_WRAP_4SIDE, "Window Wrap - 4 Sided");
    }

    @OnClick(R.id.view_arch_wrap)
    public void archWrapClicked() {
        showDialog(Job.ARCH_WRAP, "Arch Wraps");
    }

    @OnClick(R.id.view_arch_corner)
    public void archCornerClicked() {
        showDialog(Job.ARCH_CORNER, "Arch Corners");
    }

    @OnClick(R.id.view_door_wrap)
    public void doorWrapClicked() {
        showDialog(Job.DOOR_WRAP, "Door Wraps");
    }

    @OnClick(R.id.view_opening_wrap)
    public void openingWrapClicked() {
        showDialog(Job.OPENING_WRAP, "Opening Wraps");
    }

    @OnClick(R.id.view_ltrim)
    public void ltrimClicked() {
        showDialog(Job.L_TRIM, "Linear L-trim Ft.");
    }

}
