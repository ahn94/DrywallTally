package com.austinnightingale.drywalltally.tally;


import com.austinnightingale.drywalltally.db.DAO;
import com.austinnightingale.drywalltally.db.TallyArea;

/**
 * Created by Austin on 8/19/2016.
 */
public class fiveEighthsPresenter extends BasePresenter implements FiveEigths.Presenter {

    FiveEigths.View fiveEigthsView;

    public fiveEighthsPresenter(DAO dao) {
        super(dao);
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
