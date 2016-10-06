package com.austinnightingale.android.drywalltally.jobs;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ViewSwitcher;

import com.austinnightingale.android.drywalltally.R;
import com.austinnightingale.android.drywalltally.TallyApplication;
import com.austinnightingale.android.drywalltally.backup.BackupActivity;
import com.austinnightingale.android.drywalltally.db.DAO;
import com.austinnightingale.android.drywalltally.db.Job;
import com.austinnightingale.android.drywalltally.job.JobActivity;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

public final class JobsActivity extends AppCompatActivity implements JobsCallBack{

    @BindView(R.id.jobs_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.jobs_switcher)
    ViewSwitcher switcher;
    JobsRecycleAdapter adapter;
    List<Job> jobs;
    @Inject
    DAO dao;


    private Subscription subscription;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ((TallyApplication)getApplication()).getComponent().inject(this);

        adapter = new JobsRecycleAdapter(jobs, this, dao);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(this)
                .build()
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        subscription = dao.obsAllJobs()
                .subscribe(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.jobs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_item_new_crime) {
            NewJobDialog.newInstance().show(getSupportFragmentManager(), "new-job");
        } else if (id == R.id.menu_item_backup) {
            Intent intent = new Intent(this, BackupActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }


    @Override
    public void openFragment(DialogFragment fragment) {
        fragment.show(getSupportFragmentManager(), "jobsActivity fragment");
    }

    @Override
    public void openJobActivity(int jobId, String name) {
        Intent intent = new Intent(this, JobActivity.class);
        intent.putExtra(Job.ID, jobId);
        intent.putExtra(Job.JOB_NAME, name);
        startActivity(intent);

    }
}
