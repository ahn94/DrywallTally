package com.austinnightingale.android.drywalltally.tally;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.austinnightingale.android.drywalltally.R;
import com.austinnightingale.android.drywalltally.db.Job;
import com.austinnightingale.android.drywalltally.db.TallyArea;
import com.austinnightingale.android.drywalltally.job.options.PageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TallyActivity extends AppCompatActivity  {

    public static Intent newInstance(int id) {
        Intent intent = new Intent();
        intent.putExtra(TallyArea.ID, id);
        return intent;
    }

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    PageAdapter adapter;
    @BindView(R.id.pager)
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tally);
        ButterKnife.bind(this);
        adapter = new PageAdapter(getSupportFragmentManager());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Toast.makeText(this, "Job id: " + getID(), Toast.LENGTH_SHORT).show();

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

//        pager.setCurrentItem(getArguments().getInt("position"));
        pager.setOffscreenPageLimit(3);

        setTitle(pager.getCurrentItem());

    }

    public int getID() {
        int id;
        Intent intent = getIntent();
        if (intent.hasExtra(TallyArea.ID)) {
            id = intent.getIntExtra(TallyArea.ID, -1);
        } else {
            throw new IllegalArgumentException("no job area Id in Intent");
        }
        return id;
    }

    public void setTitle(int position) {
        switch (position) {
            case 0:
                setTitle("1/2\" Lite & Stretch");
                break;
            case 1:
                setTitle("5/8\" Regular & Stretch");
                break;
            case 2:
                setTitle("Ceiling");
                break;
            case 3:
                setTitle("Fire & Mold Resist.");
                break;
        }
    }

    public void setPosition(int pos) {
        pager.setCurrentItem(pos);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);

        }
    }
}
