package com.austinnightingale.drywalltally;


import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.austinnightingale.drywalltally.db.DAO;
import com.austinnightingale.drywalltally.db.DbOpenHelper;
import com.austinnightingale.drywalltally.tally.Ceiling;
import com.austinnightingale.drywalltally.tally.CeilingPresenter;
import com.austinnightingale.drywalltally.tally.Fire;
import com.austinnightingale.drywalltally.tally.FirePresenter;
import com.austinnightingale.drywalltally.tally.FiveEigths;
import com.austinnightingale.drywalltally.tally.HalfInch;
import com.austinnightingale.drywalltally.tally.HalfInchPresenter;
import com.austinnightingale.drywalltally.tally.fiveEighthsPresenter;
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

    @Provides
    @Singleton
    public DAO provideDAO(BriteDatabase db) {
        return new DAO(db);
    }

    @Provides @Singleton
    public HalfInch.Presenter provideHalfRegPresenter(DAO dao) {
        return new HalfInchPresenter(dao);
    }

    @Provides @Singleton
    public FiveEigths.Presenter providesFiveEighthPresenter(DAO dao) {
        return new fiveEighthsPresenter(dao);
    }

    @Provides @Singleton
    public Ceiling.Presenter providesCeilingPresenter(DAO dao) {
        return new CeilingPresenter(dao);
    }

    @Provides @Singleton
    public Fire.Presenter providesFirePresenter(DAO dao) {
        return new FirePresenter(dao);
    }

}
