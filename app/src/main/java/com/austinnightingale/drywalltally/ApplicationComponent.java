package com.austinnightingale.drywalltally;


import com.austinnightingale.drywalltally.backup.BackupActivity;
import com.austinnightingale.drywalltally.db.DAO;
import com.austinnightingale.drywalltally.job.JobActivity;
import com.austinnightingale.drywalltally.job.JobReportDialog;
import com.austinnightingale.drywalltally.job.dialogs.EditNameDialog;
import com.austinnightingale.drywalltally.job.dialogs.RemoveChargeDialog;
import com.austinnightingale.drywalltally.job.options.ExtraOptionsFragment;
import com.austinnightingale.drywalltally.job.options.MainOptionsFragment;
import com.austinnightingale.drywalltally.job.options.UpchargeOptionsFragment;
import com.austinnightingale.drywalltally.job.options.finish.FinishOptionsFragment;
import com.austinnightingale.drywalltally.job.options.tallyarea.NewJobAreaDialog;
import com.austinnightingale.drywalltally.job.options.tallyarea.RemoveTallyAreaDialog;
import com.austinnightingale.drywalltally.job.options.tallyarea.TallyAreasFragment;
import com.austinnightingale.drywalltally.job.summary.jobsummary.JobSummaryFragment;
import com.austinnightingale.drywalltally.job.summary.tallysummary.TalliesPagerFragment;
import com.austinnightingale.drywalltally.job.summary.tallysummary.TallySummaryFragment;
import com.austinnightingale.drywalltally.jobs.DeleteJobDialog;
import com.austinnightingale.drywalltally.jobs.JobsActivity;
import com.austinnightingale.drywalltally.jobs.NewJobDialog;
import com.austinnightingale.drywalltally.tally.BaseJobFragment;
import com.austinnightingale.drywalltally.tally.CeilingsFragment;
import com.austinnightingale.drywalltally.tally.FireFragment;
import com.austinnightingale.drywalltally.tally.FiveEighthFragment;
import com.austinnightingale.drywalltally.tally.HalfInchFragment;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by Austin on 8/12/2016.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(JobsActivity jobsActivity);
    void inject(JobActivity jobActivity);
    void inject(NewJobDialog NewJobDialog);
    void inject(HalfInchFragment halfInchFragment);
    void inject(FiveEighthFragment fiveEighthFragment);
    void inject(DeleteJobDialog deleteJobDialog);
    void inject(CeilingsFragment ceilingsFragment);
    void inject(TallySummaryFragment tallySummaryFragment);
    void inject(MainOptionsFragment mainOptionsFragment);
    void inject(UpchargeOptionsFragment upchargeOptionsFragment);
    void inject(FinishOptionsFragment finishOptionsFragment);
    void inject(JobSummaryFragment jobSummaryFragment);
    void inject(BaseJobFragment baseJobFragment);
    void inject(RemoveChargeDialog removeChargeDialog);
    void inject(BackupActivity backupActivity);
    void inject(FireFragment fireFragment);
    void inject(NewJobAreaDialog jobAreaDialog);
    void inject(TallyAreasFragment tallyAreasFragment);
    void inject(RemoveTallyAreaDialog tallyAreaDialog);
    void inject(DAO dao);
    void inject(TalliesPagerFragment talliesPagerFragment);
    void inject(ExtraOptionsFragment extraOptionsFragment);
    void inject(JobReportDialog reportDialog);
    void inject(EditNameDialog editNameDialog);
}
