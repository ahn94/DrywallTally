package com.austinnightingale.android.drywalltally.job.tally;

import com.austinnightingale.android.drywalltally.db.Job;
import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Inject;

/**
 * Created by Austin on 9/24/2016.
 */

public class FirePresenter extends BasePresenter implements Fire.Presenter {

    private Fire.View fireView;

    @Inject
    public FirePresenter(BriteDatabase db) {
        super(db);
    }

    @Override
    public int getID() {
        return fireView.getID();
    }

    @Override
    public void setView(Object view) {
        fireView = (Fire.View) view;
    }

    @Override
    public void refreshView(Job job) {
        fireView.updateFire8Text(strOf(job.fireEight()));
        fireView.updateFire9Text(strOf(job.fireNine()));
        fireView.updateFire10Text(strOf(job.fireTen()));
        fireView.updateFire12Text(strOf(job.fireTwelve()));
        fireView.updateFire14Text(strOf(job.fireFourteen()));
        fireView.updateFire16Text(strOf(job.fireSixteen()));
        fireView.updateMold8Text(strOf(job.moldEight()));
        fireView.updateMold12Text(strOf(job.moldTwelve()));
    }
}
