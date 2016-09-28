package com.austinnightingale.android.drywalltally;


import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.austinnightingale.android.drywalltally.db.DbOpenHelper;
import com.austinnightingale.android.drywalltally.job.tally.Ceiling;
import com.austinnightingale.android.drywalltally.job.tally.CeilingPresenter;
import com.austinnightingale.android.drywalltally.job.tally.Fire;
import com.austinnightingale.android.drywalltally.job.tally.FirePresenter;
import com.austinnightingale.android.drywalltally.job.tally.FiveEigths;
import com.austinnightingale.android.drywalltally.job.tally.HalfInchPresenter;
import com.austinnightingale.android.drywalltally.job.tally.fiveEighthsPresenter;
import com.austinnightingale.android.drywalltally.job.tally.HalfInch;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.schedulers.Schedulers;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides @Singleton
    public Context provideContext() {
        return application;
    }


    @Provides @Singleton
    public SQLiteOpenHelper provideDbOpenHelper(Context context) {
        return new DbOpenHelper(context);
    }

    @Provides @Singleton
    public BriteDatabase provideDB(SQLiteOpenHelper openHelper) {
        SqlBrite sqlBrite = SqlBrite.create();
        return sqlBrite.wrapDatabaseHelper(openHelper, Schedulers.io());
    }

    @Provides @Singleton
    public HalfInch.Presenter provideHalfRegPresenter(BriteDatabase db) {
        return new HalfInchPresenter(db);
    }

    @Provides @Singleton
    public FiveEigths.Presenter providesFiveEighthPresenter(BriteDatabase db) {
        return new fiveEighthsPresenter(db);
    }

    @Provides @Singleton
    public Ceiling.Presenter providesCeilingPresenter(BriteDatabase db) {
        return new CeilingPresenter(db);
    }

    @Provides @Singleton
    public Fire.Presenter providesFirePresenter(BriteDatabase db) {
        return new FirePresenter(db);
    }

}
