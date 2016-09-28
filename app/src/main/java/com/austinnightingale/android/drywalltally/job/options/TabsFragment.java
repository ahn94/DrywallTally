package com.austinnightingale.android.drywalltally.job.options;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.austinnightingale.android.drywalltally.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabsFragment extends Fragment {
    PageAdapter adapter;
    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @BindView(R.id.pager) ViewPager pager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Job Options");
        adapter = new PageAdapter(getActivity().getSupportFragmentManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_pager, container, false);
        ButterKnife.bind(this, view);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        adapter.addFragment(new MainOptionsFragment(), "Main");
        adapter.addFragment(new UpchargeOptionsFragment(), "Up Charge");
        adapter.addFragment(new FinishOptionsFragment(), "Finish");
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(pager, true);

        return view;
    }
}
