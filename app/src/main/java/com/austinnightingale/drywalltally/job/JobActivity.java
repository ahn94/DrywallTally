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

import com.austinnightingale.android.drywalltally.JobReport;
import com.austinnightingale.android.drywalltally.R;
import com.austinnightingale.android.drywalltally.TallyApplication;
import com.austinnightingale.android.drywalltally.db.DAO;
import com.austinnightingale.android.drywalltally.db.Job;
import com.austinnightingale.android.drywalltally.db.TallyArea;
import com.austinnightingale.android.drywalltally.job.options.OptionsTabFragment;
import com.austinnightingale.android.drywalltally.job.summary.jobsummary.JobSummaryFragment;
import com.austinnightingale.android.drywalltally.job.summary.tallysummary.TalliesPagerFragment;
import com.austinnightingale.android.drywalltally.job.summary.tallysummary.TallySummaryFragment;

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
    DAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            setJobOptionsFragment(4);
        }

        ((TallyApplication) getApplication()).getComponent().inject(this);
        TextView headerName = (TextView) navigationView.getHeaderView(0)
                .findViewById(R.id.job_name_header);

        Intent intent = getIntent();
        if (intent.hasExtra(Job.JOB_NAME)) {
            headerName.setText(intent.getStringExtra(Job.JOB_NAME));
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
            Job job = dao.jobWithId(getID());
            sendReport(job);
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendReport(Job job) {
        JobReport report = new JobReport(getID(), dao);
        Intent r = new Intent(Intent.ACTION_SEND);
        r.setType("text/plain");
        r.putExtra(Intent.EXTRA_TEXT, report.getReport());
        r.putExtra(Intent.EXTRA_SUBJECT, job.jobName() + " Tally");
        startActivity(Intent.createChooser(r, "Send tally via"));
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

        if (id == R.id.job_summary) {
            openFragment(new JobSummaryFragment(), "job_summary");
        } else if (id == R.id.options_job_area) {
            setJobOptionsFragment(4);
        } else if (id == R.id.options_main) {
            setJobOptionsFragment(0);
        } else if (id == R.id.area_tally_summary) {
            openFragment(new TalliesPagerFragment(), "tallies");
        } else if (id == R.id.job_tally_summary) {
            Job job = dao.jobWithId(getID());
            List<TallyArea> tallies = dao.tallyListByJobId(getID());
            openFragment(TallySummaryFragment.newInstance(Utils.getJobTally(job.jobName() + " Job", tallies), true), "job tally");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    private void setJobOptionsFragment(int position) {
        OptionsTabFragment jobOptionsFragment = (OptionsTabFragment) getSupportFragmentManager()
                .findFragmentByTag("job options");
        if (jobOptionsFragment != null && jobOptionsFragment.isVisible()) {
            jobOptionsFragment.setPosition(position);
        } else {
            OptionsTabFragment pager = OptionsTabFragment.newInstance(position);
            openFragment(pager, "job options");
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
