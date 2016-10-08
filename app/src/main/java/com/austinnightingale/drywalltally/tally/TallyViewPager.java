package com.austinnightingale.drywalltally.tally;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.austinnightingale.drywalltally.R;
import com.austinnightingale.drywalltally.job.options.PageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Austin on 9/7/2016.
 */
public class TallyViewPager extends Fragment{

    public static TallyViewPager newInstance(int position) {
        TallyViewPager pager = new TallyViewPager();
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
        adapter = new PageAdapter(getActivity().getSupportFragmentManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_totals_pager, container, false);
        ButterKnife.bind(this, view);

        adapter.addFragment(new HalfInchFragment(), "1/2\" Lite & Stretch");
        adapter.addFragment(new FiveEighthFragment(), "5/8\" Regular & Stretch");
        adapter.addFragment(new CeilingsFragment(), "Ceiling");
        adapter.addFragment(new FireFragment(), "Fire & Mold Resist.");
        pager.setAdapter(adapter);

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

        pager.setCurrentItem(getArguments().getInt("position"));
        pager.setOffscreenPageLimit(3);

        setTitle(pager.getCurrentItem());

        return view;
    }

    private void setTitle(int position) {
        switch (position) {
            case 0:
                getActivity().setTitle("1/2\" Lite & Stretch");
                break;
            case 1:
                getActivity().setTitle("5/8\" Regular & Stretch");
                break;
            case 2:
                getActivity().setTitle("Ceiling");
                break;
            case 3:
                getActivity().setTitle("Fire & Mold Resist.");
                break;
        }
    }

    public void setPosition(int pos) {
        pager.setCurrentItem(pos);
    }
}
