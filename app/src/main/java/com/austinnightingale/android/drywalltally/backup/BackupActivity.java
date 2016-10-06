package com.austinnightingale.android.drywalltally.backup;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.austinnightingale.android.drywalltally.R;
import com.austinnightingale.android.drywalltally.TallyApplication;
import com.austinnightingale.android.drywalltally.db.DAO;
import com.austinnightingale.android.drywalltally.db.HeightCharge;
import com.austinnightingale.android.drywalltally.db.Job;
import com.austinnightingale.android.drywalltally.db.JobInfo;
import com.austinnightingale.android.drywalltally.db.TallyArea;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.BriteDatabase.Transaction;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;

public class BackupActivity extends AppCompatActivity {

    @BindView(R.id.text)
    TextView text;

    protected Subscription subscription;
    @Inject
    protected BriteDatabase db;

    @Inject
    DAO dao;

    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ((TallyApplication) getApplication()).getComponent().inject(this);
        gson = new GsonBuilder()
                .registerTypeAdapterFactory(MyAdapterFactory.create())
                .setPrettyPrinting()
                .create();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.btn_restore)
    public void restoreClick() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    restoreFromFile(data.getData());
                }
                break;
        }
    }

    private void restoreFromFile(Uri uri) {
        String json = uriToDataString(uri);
        JobInfo[] jobs = getJobsListFromJson(json);

        Transaction transaction = db.newTransaction();
        try {
            for (JobInfo jobInfo : jobs) {
                Job job = jobInfo.job();
                List<String> heightCharges = jobInfo.heightCharges();
                job = job.toBuilder().jobID(null).build();
                long id = db.insert(Job.TABLE, job.toContentValues());
                for (String charge : heightCharges) {
                    db.insert(HeightCharge.TABLE, HeightCharge.builder()
                            .setHeightCharge(charge)
                            .setJobId((int) id)
                            .build()
                            .toContentValues()
                    );
                }
                for (TallyArea tallyArea : jobInfo.tallyAreas()) {
                    db.insert(TallyArea.TABLE, tallyArea.toBuilder()
                            .Id(null)
                            .jobID((int) id)
                            .build()
                            .toContentValues()
                    );
                }
            }
            text.setText(jobs.length + " jobs restored");
            transaction.markSuccessful();
        } finally {
            transaction.end();
        }

    }

    @OnClick(R.id.btn_backup)
    public void backupClick() {
        List<Job> jobs = db.createQuery(Job.TABLE, Job.getAllJobs)
                .mapToList(Job.mapper())
                .toBlocking()
                .first();

        List<HeightCharge> heightCharges = db.createQuery(HeightCharge.TABLE, "SELECT * FROM " + HeightCharge.TABLE)
                .mapToList(HeightCharge.mapper())
                .toBlocking()
                .first();


        List<JobInfo> jobInfoList = new ArrayList<>();

        for (Job job : jobs) {
            List<String> charges = new ArrayList<>();
            for (HeightCharge charge : heightCharges) {
                if (charge.jobId() == job.jobID()) {
                    charges.add(charge.heightCharge());
                }
            }

            List<TallyArea> tallyAreas = dao.tallyListByJobId(job.jobID());

            JobInfo jobInfo = JobInfo.builder()
                    .job(job)
                    .heightCharges(charges)
                    .tallyAreas(tallyAreas)
                    .build();
            jobInfoList.add(jobInfo);
        }

        try {
            Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                    .setText(gson.toJson(jobInfoList))
                    .setSubject(getCurrentDate())
                    .setType("application/json")
                    .getIntent()
                    .setPackage("com.google.android.apps.docs");
            startActivity(shareIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(BackupActivity.this, "Google Drive is not installed!", Toast.LENGTH_LONG).show();
        }
    }


    public String getCurrentDate() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("MM-dd-yyyy");
        String strDate = "drywallBackup_" + mdformat.format(calendar.getTime());
        return strDate;
    }

    private JobInfo[] getJobsListFromJson(String json) {
        JobInfo[] list = new JobInfo[0];
        try {
            list = gson.fromJson(json, JobInfo[].class);
            Toast.makeText(BackupActivity.this, "Valid File", Toast.LENGTH_SHORT).show();
        } catch (JsonSyntaxException ex) {
            Toast.makeText(BackupActivity.this, "Invalid File", Toast.LENGTH_SHORT).show();
        }
        return list;
    }

    private String uriToDataString(Uri uri) {
        String json = "";
        try (InputStream is = getContentResolver().openInputStream(uri)) {
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != subscription && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
