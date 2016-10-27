package com.austinnightingale.drywalltally.job.options.tallyarea;


public interface TallyCallback {

    void openTallyForArea(int id);

    void showRemoveDialog(Integer id, String areaName);

    void showEditNameDialog(String table, String column, String title, Integer id);

}
