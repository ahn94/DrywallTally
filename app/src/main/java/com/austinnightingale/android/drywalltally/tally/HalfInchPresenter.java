package com.austinnightingale.android.drywalltally.tally;

import com.austinnightingale.android.drywalltally.db.Job;
import com.austinnightingale.android.drywalltally.db.TallyArea;
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
    public void refreshView(TallyArea tallyArea) {
        HalfView.updateRegHalfEight(tallyArea.halfRegEight());
        HalfView.updateRegHalfNine(tallyArea.halfRegNine());
        HalfView.updateRegHalfTen(tallyArea.halfRegTen());
        HalfView.updateRegHalfTwelve(tallyArea.halfRegTwelve());
        HalfView.updateRegHalfFourteen(tallyArea.halfRegFourteen());
        HalfView.updateRegHalfSixteen(tallyArea.halfRegSixteen());
        HalfView.updateStretchHalfTwelve(tallyArea.halfStretchTwelve());
        HalfView.updateStretchHalfFourteen(tallyArea.halfStretchFourteen());
        HalfView.updateStretchHalfSixteen(tallyArea.halfStretchSixteen());
    }


}