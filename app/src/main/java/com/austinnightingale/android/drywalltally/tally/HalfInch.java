package com.austinnightingale.android.drywalltally.tally;

import com.austinnightingale.android.drywalltally.db.Job;
import com.austinnightingale.android.drywalltally.db.TallyArea;

/**
 * Created by Austin on 8/19/2016.
 */
public interface HalfInch {

    interface View {

        void updateRegHalfEight(int tally);

        void updateRegHalfNine(int tally);

        void updateRegHalfTen(int tally);

        void updateRegHalfTwelve(int tally);

        void updateRegHalfFourteen(int tally);

        void updateRegHalfSixteen(int tally);

        void updateStretchHalfTwelve(int tally);

        void updateStretchHalfFourteen(int tally);

        void updateStretchHalfSixteen(int tally);

        int getID();

        void setNegativeError();
    }

    interface Presenter {

        void setView(final Object view);

        void refreshView(TallyArea tallyArea);

        void updateJob(String column, int value);

        void onResume();

        void onPause();
    }

}
