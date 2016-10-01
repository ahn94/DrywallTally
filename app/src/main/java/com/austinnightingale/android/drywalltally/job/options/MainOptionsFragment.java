package com.austinnightingale.android.drywalltally.job.options;


import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.austinnightingale.android.drywalltally.R;
import com.austinnightingale.android.drywalltally.db.Job;
import com.austinnightingale.android.drywalltally.job.dialogs.InputDialog;
import com.austinnightingale.android.drywalltally.job.dialogs.InputListener;
import com.austinnightingale.android.drywalltally.tally.BaseJobFragment;
import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class MainOptionsFragment extends BaseJobFragment implements InputListener {

    @BindView(R.id.value_job_name) TextView textName;
    @BindView(R.id.value_address) TextView textAddress;
    @BindView(R.id.input_comment)
    EditText inputComment;

    Subscription textChange;

    boolean isFirstTime;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isFirstTime = savedInstanceState == null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_options, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        textChange = RxTextView.textChanges(inputComment)
                .map(value -> new Job.ContentBuilder().comment(value.toString()).build())
                .subscribe(contentValues -> {
                    if (!isFirstTime) {
                        updateJob(contentValues);
                    }
                });
        subscription.add(db.createQuery(Job.TABLE, Job.getJobwithIDQuery, String.valueOf(getID()))
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
                        Log.d("jobinfo", job.toString());
                    }
                }));
    }

    private void refreshView(Job job) {
        if (isFirstTime) {
            inputComment.setText(job.comment());
            isFirstTime = false;
        }
        textAddress.setText(job.address());
        textName.setText(job.jobName());
    }

    @Override
    public void onPause() {
        super.onPause();
        textChange.unsubscribe();
    }


    private void showDialog(String column, String title, boolean allowEmptyInput) {
        InputDialog inputDialog = InputDialog.newInstance(
                column,
                title,
                InputType.TYPE_CLASS_TEXT,
                allowEmptyInput,
                this
        );
        inputDialog.show(getFragmentManager(), "string picker");
    }

    @Override
    public void setInputResults(ContentValues values) {
        updateJob(values);
    }

    @OnClick(R.id.view_job_name)
    public void jobNameClicked() {
        showDialog(Job.JOB_NAME, "Set Job Name", false);
    }

    @OnClick(R.id.view_address)
    public void addressClicked() {
        showDialog(Job.ADDRESS, "Set Address", true);
    }
}
