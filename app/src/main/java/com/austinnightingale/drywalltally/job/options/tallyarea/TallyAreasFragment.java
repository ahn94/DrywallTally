package com.austinnightingale.drywalltally.job.options.tallyarea;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.austinnightingale.drywalltally.R;
import com.austinnightingale.drywalltally.TallyApplication;
import com.austinnightingale.drywalltally.db.DAO;
import com.austinnightingale.drywalltally.db.Job;
import com.austinnightingale.drywalltally.job.dialogs.EditNameDialog;
import com.austinnightingale.drywalltally.tally.TallyActivity;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;


public class TallyAreasFragment extends Fragment implements TallyCallback {

    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    TallyAreaAdapter adapter;

    @Inject
    DAO dao;
    Subscription subscription;


    public static TallyAreasFragment newInstance() {
        TallyAreasFragment fragment = new TallyAreasFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tally_areas, container, false);
        ((TallyApplication) getActivity().getApplication()).getComponent().inject(this);

        ButterKnife.bind(this, view);

        adapter = new TallyAreaAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getContext())
                        .build()
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
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
    public void onResume() {
        super.onResume();
        subscription = dao.obsTallyListByJobId(getID()).subscribe(adapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        subscription.unsubscribe();
    }

    @OnClick(R.id.add_area)
    public void addAreaClick() {
        openFragment(NewJobAreaDialog.newInstance(getID()));
    }

    public void openFragment(DialogFragment fragment) {
        fragment.show(getFragmentManager(), "jobsActivity fragment");
    }

    @Override
    public void openTallyForArea(int id) {
        Intent intent = TallyActivity.newInstance(id);
        intent.setClass(getContext(), TallyActivity.class);
        startActivity(intent);
    }

    @Override
    public void showRemoveDialog(Integer id, String areaName) {
        RemoveTallyAreaDialog.newInstance(id, areaName)
                .show(getFragmentManager(), "remove tally area");
    }

    @Override
    public void showEditNameDialog(String table, String column, String title, Integer id) {
        EditNameDialog.newInstance(table, column, title, id)
                .show(getFragmentManager(), "edit name dialog");
    }
}