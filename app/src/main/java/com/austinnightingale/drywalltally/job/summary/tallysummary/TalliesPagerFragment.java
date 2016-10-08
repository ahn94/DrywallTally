package com.austinnightingale.drywalltally.job.summary.tallysummary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import com.austinnightingale.drywalltally.R;
import com.austinnightingale.drywalltally.TallyApplication;
import com.austinnightingale.drywalltally.db.DAO;
import com.austinnightingale.drywalltally.db.Job;
import com.austinnightingale.drywalltally.db.TallyArea;
import com.austinnightingale.drywalltally.job.Utils;
import com.austinnightingale.drywalltally.job.options.PageAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Austin on 9/7/2016.
 */
public class TalliesPagerFragment extends Fragment {

    public static TalliesPagerFragment newInstance(int position) {
        TalliesPagerFragment pager = new TalliesPagerFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        pager.setArguments(args);
        return pager;
    }
    PageAdapter adapter;
    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.tallies_flipper)
    ViewFlipper talliesFlipper;

    @Inject
    DAO dao;

    List<TallyArea> tallyAreas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Tally Summary");
        adapter = new PageAdapter(getActivity().getSupportFragmentManager());
        ((TallyApplication) getActivity().getApplication()).getComponent().inject(this);
        getActivity().setTitle("Area Tallies");
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_totals_pager, container, false);
        ButterKnife.bind(this, view);

        tallyAreas = dao.tallyListByJobId(getID());

        if (Utils.jobTotalFtNum(tallyAreas) == 0) {
            talliesFlipper.setDisplayedChild(1);
        } else {
            talliesFlipper.setDisplayedChild(0);
            for (TallyArea area : tallyAreas) {
                adapter.addFragment(TallySummaryFragment.newInstance(area, false), area.areaName() + " Area");
            }
        }

        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(3);

        return view;
    }

}
