package com.austinnightingale.drywalltally.job.options.tallyarea;


public interface TallyCallback {

    public void openTallyForArea(int id);

    public void showDialog(Integer id, String areaName);
}
