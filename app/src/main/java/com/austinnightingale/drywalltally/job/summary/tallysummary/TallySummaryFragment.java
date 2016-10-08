package com.austinnightingale.android.drywalltally.job.summary.tallysummary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.austinnightingale.android.drywalltally.R;
import com.austinnightingale.android.drywalltally.TallyApplication;
import com.austinnightingale.android.drywalltally.db.DAO;
import com.austinnightingale.android.drywalltally.db.TallyArea;
import com.austinnightingale.android.drywalltally.job.Utils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TallySummaryFragment extends Fragment{

    public static TallySummaryFragment newInstance(TallyArea area, boolean showEmtpyCards) {
        TallySummaryFragment tallySummaryFragment = new TallySummaryFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("tally", area);
        bundle.putBoolean("showEmptyCards", showEmtpyCards);
        tallySummaryFragment.setArguments(bundle);
        return tallySummaryFragment;
    }

    @BindView(R.id.tally_name)
    TextView tallNameText;
    @BindView(R.id.tally_ceil_sq_total)
    TextView ceilSqTotal;
    @BindView(R.id.tally_total_sq)
    TextView tallySqTotal;
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    @BindView(R.id.jobs_switcher)
    ViewSwitcher switcher;
    TallyTotalAdapter adapter;

    @Inject
    DAO dao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TallyApplication) getActivity().getApplication()).getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tally_totals_recycleview, container, false);
        ButterKnife.bind(this, view);

        adapter = new TallyTotalAdapter(getArguments().getBoolean("showEmptyCards"));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 18, true));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if (adapter.getItemCount() == 0) {
                    switcher.setDisplayedChild(1);
                } else {
                    switcher.setDisplayedChild(0);
                }
            }
        });
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        TallyArea tallies = getTallies();
        adapter.call(tallies);
        tallNameText.setText(tallies.areaName());
        ceilSqTotal.setText(getString(R.string.format_ceil_square, Utils.areaCeilingSqFt(tallies)));
        tallySqTotal.setText(getString(R.string.format_total_square, Utils.areaTotalSqFt(tallies)));
    }


    public TallyArea getTallies() {
        TallyArea tally;
        Bundle intent = getArguments();
        if (intent.containsKey("tally")) {
            tally = intent.getParcelable("tally");
        } else {
            throw new IllegalArgumentException("tally area missing from arguments for TallySummaryFragment");
        }
        return tally;
    }
}
