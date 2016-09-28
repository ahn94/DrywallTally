package com.austinnightingale.android.drywalltally.job.tally;

import com.austinnightingale.android.drywalltally.db.Job;
import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Inject;

public class CeilingPresenter extends BasePresenter implements Ceiling.Presenter {

    private Ceiling.View ceilingView;

    @Inject
    public CeilingPresenter(BriteDatabase db) {
        super(db);
    }

    @Override
    public void setView(Object view) {
        ceilingView = (Ceiling.View) view;
    }

    @Override
    public int getID() {
        return ceilingView.getID();
    }

    @Override
    public void refreshView(Job job) {
        ceilingView.updateCeil8Text(strOf(job.ceilingEight()));
        ceilingView.updateCeil9Text(strOf(job.ceilingNine()));
        ceilingView.updateCeil10Text(strOf(job.ceilingTen()));
        ceilingView.updateCeil12Text(strOf(job.ceilingTwelve()));
        ceilingView.updateCeil14Text(strOf(job.ceilingFourteen()));
        ceilingView.updateCeil16Text(strOf(job.ceilingSixteen()));
    }

}
