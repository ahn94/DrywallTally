package com.austinnightingale.drywalltally.tally;

import com.austinnightingale.drywalltally.db.DAO;
import com.austinnightingale.drywalltally.db.TallyArea;

import javax.inject.Inject;

/**
 * Created by Austin on 9/24/2016.
 */

public class FirePresenter extends BasePresenter implements Fire.Presenter {

    private Fire.View fireView;

    @Inject
    public FirePresenter(DAO dao) {
        super(dao);
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
    public void refreshView(TallyArea tallyArea) {
        fireView.updateFire8Text(strOf(tallyArea.fireEight()));
        fireView.updateFire9Text(strOf(tallyArea.fireNine()));
        fireView.updateFire10Text(strOf(tallyArea.fireTen()));
        fireView.updateFire12Text(strOf(tallyArea.fireTwelve()));
        fireView.updateFire14Text(strOf(tallyArea.fireFourteen()));
        fireView.updateFire16Text(strOf(tallyArea.fireSixteen()));
        fireView.updateMold8Text(strOf(tallyArea.moldEight()));
        fireView.updateMold12Text(strOf(tallyArea.moldTwelve()));
    }
}
