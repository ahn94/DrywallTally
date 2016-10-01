package com.austinnightingale.android.drywalltally.job.options.tallyarea;

/**
 * Created by Austin on 9/30/2016.
 */

public interface TallyCallback {

    public void openTallyForArea(int id);

    public void showDialog(Integer id, String areaName);
}
