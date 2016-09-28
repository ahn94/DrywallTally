package com.austinnightingale.android.drywalltally.job.tally;

/**
 * Created by Austin on 8/19/2016.
 */
public interface FiveEigths {

    interface View {

        int getID();

        void setNegativeError();

        void updateFiveEighthEightText(String tally);

        void updateFiveEighthNineText(String tally);

        void updateFiveEighthTenText(String tally);

        void updateFiveEighthTwelveText(String tally);

        void updateFiveEighthFourteenText(String tally);

        void updateStretchFiveEighthTwelveText(String tally);
    }

    interface Presenter {

        void setView(final Object view);

        void refreshView();

        void updateJob(String column, int value);

        void onResume();

        void onPause();

    }
}
