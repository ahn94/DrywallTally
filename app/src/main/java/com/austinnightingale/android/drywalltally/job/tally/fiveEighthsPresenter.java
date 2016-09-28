package com.austinnightingale.android.drywalltally.job.tally;

import android.content.ContentValues;
import android.util.Log;

import com.austinnightingale.android.drywalltally.db.Job;
import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Austin on 8/19/2016.
 */
public class fiveEighthsPresenter implements FiveEigths.Presenter {

    BriteDatabase db;
    FiveEigths.View fiveEigthsView;
    Subscription subscription;
    Job job;

    @Inject
    public fiveEighthsPresenter(BriteDatabase db) {
        this.db = db;
    }

    @Override
    public void setView(Object view) {
        fiveEigthsView = (FiveEigths.View) view;
    }

    public void setJob(Job job) {
        this.job = job;
    }


    @Override
    public void refreshView() {
        fiveEigthsView.updateFiveEighthEightText(String.valueOf(job.fiveEighthRegEight()));
        fiveEigthsView.updateFiveEighthNineText(String.valueOf(job.fiveEighthRegNine()));
        fiveEigthsView.updateFiveEighthTenText(String.valueOf(job.fiveEighthRegTen()));
        fiveEigthsView.updateFiveEighthTwelveText(String.valueOf(job.fiveEighthRegTwelve()));
        fiveEigthsView.updateFiveEighthFourteenText(String.valueOf(job.fiveEighthRegFourteen()));
        fiveEigthsView.updateStretchFiveEighthTwelveText(String.valueOf(job.fiveEightStretchTwelve()));
    }

    @Override
    public void updateJob(String column, int value) {
        ContentValues content = job.toContentValues();
        int updatedValue = content.getAsInteger(column) + value;
        if (updatedValue < 0) {
            updatedValue = 0;
            fiveEigthsView.setNegativeError();
        }
        content.put(column, updatedValue);
        db.update(Job.TABLE, content, Job.ID + " = ?", String.valueOf(fiveEigthsView.getID()));
    }

    @Override
    public void onResume() {
        Log.d("jobinfo", "onResume presenter HalfView.getID() is: " + String.valueOf(fiveEigthsView.getID()));
        subscription = db.createQuery(Job.TABLE, Job.getJobwithIDQuery, String.valueOf(fiveEigthsView.getID()))
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
                        refreshView();
                        Log.d("jobinfo", job.toString());
                    }
                });
    }

    @Override
    public void onPause() {
        subscription.unsubscribe();
    }

}
