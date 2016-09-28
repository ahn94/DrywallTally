package com.austinnightingale.android.drywalltally;

import com.austinnightingale.android.drywalltally.backup.BackupActivity;
import com.austinnightingale.android.drywalltally.job.JobActivity;
import com.austinnightingale.android.drywalltally.job.options.FinishOptionsFragment;
import com.austinnightingale.android.drywalltally.job.options.MainOptionsFragment;
import com.austinnightingale.android.drywalltally.job.dialogs.RemoveChargeDialog;
import com.austinnightingale.android.drywalltally.job.options.UpchargeOptionsFragment;
import com.austinnightingale.android.drywalltally.job.tally.BaseJobFragment;
import com.austinnightingale.android.drywalltally.job.tally.CeilingsFragment;
import com.austinnightingale.android.drywalltally.job.tally.FireFragment;
import com.austinnightingale.android.drywalltally.job.tally.FiveEighthFragment;
import com.austinnightingale.android.drywalltally.job.tally.HalfInchFragment;
import com.austinnightingale.android.drywalltally.job.summary.OptionTotalsFragment;
import com.austinnightingale.android.drywalltally.job.summary.TotalsFragment;
import com.austinnightingale.android.drywalltally.jobs.DeleteJobDialog;
import com.austinnightingale.android.drywalltally.jobs.JobsActivity;
import com.austinnightingale.android.drywalltally.jobs.NewJobDialog;

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
    void inject(TotalsFragment totalsFragment);
    void inject(MainOptionsFragment mainOptionsFragment);
    void inject(UpchargeOptionsFragment upchargeOptionsFragment);
    void inject(FinishOptionsFragment finishOptionsFragment);
    void inject(OptionTotalsFragment optionTotalsFragment);
    void inject(BaseJobFragment baseJobFragment);
    void inject(RemoveChargeDialog removeChargeDialog);
    void inject(BackupActivity backupActivity);
    void inject(FireFragment fireFragment);
}
