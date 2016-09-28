package com.austinnightingale.android.drywalltally.job.tally;

import com.austinnightingale.android.drywalltally.db.Job;
import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Inject;


public class HalfInchPresenter extends BasePresenter implements HalfInch.Presenter {

    private HalfInch.View HalfView;

    @Inject
    public HalfInchPresenter(BriteDatabase db) {
        super(db);
    }

    @Override
    public int getID() {
        return HalfView.getID();
    }


    @Override
    public void setView(Object view) {
        HalfView = (HalfInch.View) view;

    }

    @Override
    public void refreshView(Job job) {
        HalfView.updateRegHalfEight(job.halfRegEight());
        HalfView.updateRegHalfNine(job.halfRegNine());
        HalfView.updateRegHalfTen(job.halfRegTen());
        HalfView.updateRegHalfTwelve(job.halfRegTwelve());
        HalfView.updateRegHalfFourteen(job.halfRegFourteen());
        HalfView.updateRegHalfSixteen(job.halfRegSixteen());
        HalfView.updateStretchHalfTwelve(job.halfStretchTwelve());
        HalfView.updateStretchHalfFourteen(job.halfStretchFourteen());
        HalfView.updateStretchHalfSixteen(job.halfStretchSixteen());
    }


}
