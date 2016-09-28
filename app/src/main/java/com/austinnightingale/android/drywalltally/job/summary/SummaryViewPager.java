package com.austinnightingale.android.drywalltally.job.summary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.austinnightingale.android.drywalltally.R;
import com.austinnightingale.android.drywalltally.job.options.PageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Austin on 9/7/2016.
 */
public class SummaryViewPager extends Fragment{

    public static SummaryViewPager newInstance(int position) {
        SummaryViewPager pager = new SummaryViewPager();
        Bundle args = new Bundle();
        args.putInt("position", position);
        pager.setArguments(args);
        return pager;
    }
    PageAdapter adapter;
    @BindView(R.id.pager)
    ViewPager pager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Job Summary");
        adapter = new PageAdapter(getActivity().getSupportFragmentManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_totals_pager, container, false);
        ButterKnife.bind(this, view);

        adapter.addFragment(new TotalsFragment(), "Tally Total");
        adapter.addFragment(new OptionTotalsFragment(), "Option Summary");

        pager.setAdapter(adapter);
        pager.setCurrentItem(getArguments().getInt("position"));
        pager.setOffscreenPageLimit(3);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                setTitle(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setTitle(pager.getCurrentItem());

        return view;
    }

    private void setTitle(int position) {
        switch (position) {
            case 0:
                getActivity().setTitle("Tally Summary");
                break;
            case 1:
                getActivity().setTitle("Options Summary");
                break;
        }
    }

    public void setPosition(int pos) {
        pager.setCurrentItem(pos);
    }
}
