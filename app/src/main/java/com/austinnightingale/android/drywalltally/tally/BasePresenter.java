package com.austinnightingale.android.drywalltally.tally;

import android.content.ContentValues;

import com.austinnightingale.android.drywalltally.db.DAO;
import com.austinnightingale.android.drywalltally.db.TallyArea;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public abstract class BasePresenter {

    private static final String TAG = BasePresenter.class.getName();
    private Subscription subscription;

    private DAO dao;
    private TallyArea mTallyArea;

    public BasePresenter(DAO dao) {
        this.dao = dao;
    }

    public abstract int getID();

    public void setTallyArea(TallyArea tallyArea){
        mTallyArea = tallyArea;
    }

    public void updateJob(String column, int value) {
        ContentValues content = mTallyArea.toContentValues();
        int updatedValue = content.getAsInteger(column) + value;
        if (updatedValue < 0) {
            updatedValue = 0;
        }
        content.put(column, updatedValue);
        dao.updateTallyArea(getID(), content);
    }

    public abstract void refreshView(TallyArea tallyArea);

    protected String strOf(int number) {
        return String.valueOf(number);
    }

    public void onResume() {
        subscription = dao.getTallyAreaWithId(getID())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TallyArea>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onNext(TallyArea tallyArea) {
                        refreshView(tallyArea);
                        setTallyArea(tallyArea);
                    }
                });
    }

    public void onPause() {
        subscription.unsubscribe();
    }

}
