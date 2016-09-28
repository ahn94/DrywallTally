package com.austinnightingale.android.drywalltally.job;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.austinnightingale.android.drywalltally.R;
import com.austinnightingale.android.drywalltally.TallyApplication;
import com.austinnightingale.android.drywalltally.db.HeightCharge;
import com.austinnightingale.android.drywalltally.db.Job;
import com.austinnightingale.android.drywalltally.job.options.TabsFragment;
import com.austinnightingale.android.drywalltally.job.summary.SummaryViewPager;
import com.austinnightingale.android.drywalltally.job.tally.TallyViewPager;
import com.squareup.sqlbrite.BriteDatabase;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JobActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Inject
    BriteDatabase db;
    List<HeightCharge> charges;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        ButterKnife.bind(this);

        ((TallyApplication) getApplication()).getComponent().inject(this);
        TextView headerName = (TextView) navigationView.getHeaderView(0)
                .findViewById(R.id.job_name_header);

        Intent intent = getIntent();
        if (intent.hasExtra(Job.JOB_NAME)) {
            headerName.setText(intent.getStringExtra(Job.JOB_NAME));
        }

        if (savedInstanceState == null) {
            setTallyFragment(0);
        }

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.job, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.report) {
            sendReport(getJob(), getHeightChargeList());
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendReport(Job job, List<HeightCharge> heightChargeList) {
        Intent r = new Intent(Intent.ACTION_SEND);
        r.setType("text/plain");
        r.putExtra(Intent.EXTRA_TEXT, Report.forJob(job, heightChargeList));
        r.putExtra(Intent.EXTRA_SUBJECT, job.jobName() + " Tally");
        startActivity(Intent.createChooser(r, "Send tally via"));
    }

    private List<HeightCharge> getHeightChargeList() {
        return db.createQuery(HeightCharge.TABLE, HeightCharge.getHeightChargesWithJobId, String.valueOf(getID()))
                .mapToList(HeightCharge.mapper())
                .toBlocking()
                .first();
    }

    private Job getJob() {
        return db.createQuery(Job.TABLE, Job.getJobwithIDQuery, String.valueOf(getID()))
                .mapToOne(Job.mapper())
                .toBlocking()
                .first();
    }

    public int getID() {
        int id;
        Intent intent = getIntent();
        if (intent.hasExtra(Job.ID)) {
            id = intent.getIntExtra(Job.ID, -1);
        } else {
            throw new IllegalArgumentException("no job Id in Intent");
        }
        return id;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.half_regular) {
            setTallyFragment(0);
        } else if (id == R.id.ceilings) {
            setTallyFragment(2);
        } else if (id == R.id.five_eight_regular) {
            setTallyFragment(1);
        } else if (id == R.id.fire) {
            setTallyFragment(3);
        } else if (id == R.id.summary) {
            setSummaryFragment(0);
        } else if (id == R.id.tabbed) {
            openFragment(new TabsFragment(), "tabs");
        } else if (id == R.id.recycleview_options) {
            setSummaryFragment(1);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setSummaryFragment(int position) {
        SummaryViewPager pagerFragment = (SummaryViewPager) getSupportFragmentManager()
                .findFragmentByTag("summary");
        if (pagerFragment != null && pagerFragment.isVisible()) {
            pagerFragment.setPosition(position);
        } else {
            SummaryViewPager pager = SummaryViewPager.newInstance(position);
            openFragment(pager, "summary");
        }
    }

    public void setTallyFragment(int position) {
        TallyViewPager pagerFragment = (TallyViewPager) getSupportFragmentManager()
                .findFragmentByTag("tally");
        if (pagerFragment != null && pagerFragment.isVisible()) {
            pagerFragment.setPosition(position);
        } else {
            TallyViewPager pager = TallyViewPager.newInstance(position);
            openFragment(pager, "tally");
        }
    }

    public void openFragment(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment, tag)
                .disallowAddToBackStack()
                .commit();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
}
