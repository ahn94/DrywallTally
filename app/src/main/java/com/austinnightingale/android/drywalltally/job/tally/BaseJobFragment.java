package com.austinnightingale.android.drywalltally.job.tally;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.austinnightingale.android.drywalltally.TallyApplication;
import com.austinnightingale.android.drywalltally.db.Job;
import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

public abstract class BaseJobFragment extends Fragment {

    protected CompositeSubscription subscription;
    @Inject
    protected BriteDatabase db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscription = new CompositeSubscription();
        ((TallyApplication)getActivity().getApplication()).getComponent().inject(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        subscription.clear();
    }


    public void updateJob(ContentValues values) {
        db.update(Job.TABLE, values, Job.ID + " = ?", String.valueOf(getID()));
    }

    public int getID() {
        int id;
        Intent intent = getActivity().getIntent();
        if (intent.hasExtra(Job.ID)) {
            id = intent.getIntExtra(Job.ID, -1);
        } else {
            throw new IllegalArgumentException("no job Id in Intent");
        }
        return id;
    }
}
