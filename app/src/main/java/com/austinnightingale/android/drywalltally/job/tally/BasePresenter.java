package com.austinnightingale.android.drywalltally.job.tally;

import android.content.ContentValues;
import android.util.Log;

import com.austinnightingale.android.drywalltally.db.Job;
import com.squareup.sqlbrite.BriteDatabase;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public abstract class BasePresenter {

    private Subscription subscription;
    private BriteDatabase db;
    private Job mJob;

    public BasePresenter(BriteDatabase db) {
        this.db = db;
    }

    public abstract int getID();

    public void setJob(Job job){
        mJob = job;
    }

    public void updateJob(String column, int value) {
        ContentValues content = mJob.toContentValues();
        int updatedValue = content.getAsInteger(column) + value;
        if (updatedValue < 0) {
            updatedValue = 0;
        }
        content.put(column, updatedValue);
        db.update(Job.TABLE, content, Job.ID + " = ?", String.valueOf(getID()));
    }

    public abstract void refreshView(Job job);

    protected String strOf(int number) {
        return String.valueOf(number);
    }

    public void onResume() {
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
                        Log.d("jobcheck", e.toString());
                    }
                    @Override
                    public void onNext(Job job) {
                        setJob(job);
                        refreshView(job);
                    }
                });
    }

    public void onPause() {
        subscription.unsubscribe();
    }

}
