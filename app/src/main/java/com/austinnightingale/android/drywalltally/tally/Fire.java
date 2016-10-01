package com.austinnightingale.android.drywalltally.tally;

import com.austinnightingale.android.drywalltally.db.Job;
import com.austinnightingale.android.drywalltally.db.TallyArea;

/**
 * Created by Austin on 9/24/2016.
 */

public interface Fire {

    interface View {

        void updateFire8Text(String tally);

        void updateFire9Text(String tally);

        void updateFire10Text(String tally);

        void updateFire12Text(String tally);

        void updateFire14Text(String tally);

        void updateFire16Text(String tally);

        void updateMold8Text(String tally);

        void updateMold12Text(String tally);

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
