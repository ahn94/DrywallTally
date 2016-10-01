package com.austinnightingale.android.drywalltally.tally;

import android.content.ContentValues;
import android.util.Log;

import com.austinnightingale.android.drywalltally.db.Job;
import com.austinnightingale.android.drywalltally.db.TallyArea;
import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Austin on 8/19/2016.
 */
public class fiveEighthsPresenter extends BasePresenter implements FiveEigths.Presenter {

    FiveEigths.View fiveEigthsView;

    public fiveEighthsPresenter(BriteDatabase db) {
        super(db);
    }

    @Override
    public void setView(Object view) {
        fiveEigthsView = (FiveEigths.View) view;
    }
    
    

    @Override
    public void refreshView(TallyArea tallyArea) {
        fiveEigthsView.updateFiveEighthEightText(String.valueOf(tallyArea.fiveEighthRegEight()));
        fiveEigthsView.updateFiveEighthNineText(String.valueOf(tallyArea.fiveEighthRegNine()));
        fiveEigthsView.updateFiveEighthTenText(String.valueOf(tallyArea.fiveEighthRegTen()));
        fiveEigthsView.updateFiveEighthTwelveText(String.valueOf(tallyArea.fiveEighthRegTwelve()));
        fiveEigthsView.updateFiveEighthFourteenText(String.valueOf(tallyArea.fiveEighthRegFourteen()));
        fiveEigthsView.updateStretchFiveEighthTwelveText(String.valueOf(tallyArea.fiveEightStretchTwelve()));
    }
    
    @Override
    public int getID() {
        return fiveEigthsView.getID();
    }
}
