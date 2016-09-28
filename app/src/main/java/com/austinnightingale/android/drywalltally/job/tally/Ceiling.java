package com.austinnightingale.android.drywalltally.job.tally;

import com.austinnightingale.android.drywalltally.db.Job;

/**
 * Created by Austin on 8/20/2016.
 */
public interface Ceiling {

    interface View {

        void updateCeil8Text(String tally);

        void updateCeil9Text(String tally);

        void updateCeil10Text(String tally);

        void updateCeil12Text(String tally);

        void updateCeil14Text(String tally);

        void updateCeil16Text(String tally);

        int getID();

        void setNegativeError();
    }

    interface Presenter {

        void setView(final Object view);

        void refreshView(Job job);

        void updateJob(String column, int value);

        void onResume();

        void onPause();
    }
}
