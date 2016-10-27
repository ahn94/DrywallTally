package com.austinnightingale.drywalltally.db;


import android.content.ContentValues;

import com.squareup.sqlbrite.BriteDatabase;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DAO {

    BriteDatabase db;

    @Inject
    public DAO(BriteDatabase db) {
        this.db = db;
    }

    public BriteDatabase getDb() {
        return db;
    }


    /**
     *
     * Return Tally Area List from Job Id
     *
     */

    private Observable<List<TallyArea>> getTallyAreaListForJobId(int jobId) {
        return db.createQuery(TallyArea.TABLE, TallyArea.getAllTallyAreasForJob, String.valueOf(jobId))
                .mapToList(TallyArea.mapper());
    }

    public Observable<List<TallyArea>> obsTallyListByJobId(int jobId) {
        return getTallyAreaListForJobId(jobId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public List<TallyArea> tallyListByJobId(int jobId) {
        return getTallyAreaListForJobId(jobId)
                .toBlocking()
                .first();
    }


    /**
     *
     * Return Tally Area from Tally Area Id
     *
     */

    private Observable<TallyArea> getTallyAreaWithId(int areaId) {
        return db.createQuery(TallyArea.TABLE, TallyArea.getTallyAreaWithID, String.valueOf(areaId))
                .mapToOne(TallyArea.mapper());
    }

    public Observable<TallyArea> obsTallyAreaWithId(int areaId) {
        return getTallyAreaWithId(areaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public TallyArea tallyAreaWithId(int areaId) {
        return getTallyAreaWithId(areaId)
                .toBlocking()
                .first();
    }


    /**
     *
     * Return Job from Job Id
     *
     */

    private Observable<Job> getJobwithId(int jobId) {
        return db.createQuery(Job.TABLE, Job.getJobwithIDQuery, String.valueOf(jobId))
                .mapToOne(Job.mapper());
    }

    public Observable<Job> obsJobWithId(int jobId) {
        return getJobwithId(jobId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Job jobWithId(int jobId) {
        return getJobwithId(jobId)
                .toBlocking()
                .first();
    }


    /**
     *
     * Return Height Charge List from Job Id
     *
     */

    private Observable<List<HeightCharge>> getHeightCharges(int jobId) {
        return db.createQuery(HeightCharge.TABLE, HeightCharge.getHeightChargesWithJobId, String.valueOf(jobId))
                .mapToList(HeightCharge.mapper());
    }

    public Observable<List<HeightCharge>> obsHeightChargeListFromJobId(int jobId) {
        return getHeightCharges(jobId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public List<HeightCharge> heightChargeListFromJobId(int jobId) {
        return getHeightCharges(jobId)
                .toBlocking()
                .first();
    }


    /**
     *
     * Return All Jobs
     *
     */

    public Observable<List<Job>> getAllJobs() {
        return db.createQuery(Job.TABLE, "SELECT * FROM " + Job.TABLE + ";")
                .mapToList(Job.mapper());
    }

    public Observable<List<Job>> obsAllJobs() {
        return getAllJobs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public List<Job> jobList() {
        return getAllJobs()
                .toBlocking()
                .first();
    }


    public int updateJob(int jobId, ContentValues values) {
        return db.update(Job.TABLE, values, Job.ID + " = ?", String.valueOf(jobId));
    }

    public int updateTallyArea(int areaId, ContentValues content) {
        return db.update(TallyArea.TABLE, content, TallyArea.ID + " = ?", String.valueOf(areaId));
    }

    public int deleteTallyArea(int tallyId) {
        return db.delete(TallyArea.TABLE, TallyArea.ID + " = ?", String.valueOf(tallyId));
    }

    public int deleteHeightCharge(int heightId) {
        return db.delete(HeightCharge.TABLE, HeightCharge.ID + " = ?", String.valueOf(heightId));
    }

    public void deleteJobInfo(int jobId) {
        db.delete(Job.TABLE, Job.ID + " = ?", String.valueOf(jobId));
        db.delete(HeightCharge.TABLE, HeightCharge.JOB_ID + " = ?", String.valueOf(jobId));
        db.delete(TallyArea.TABLE, TallyArea.JOB_ID + " = ?", String.valueOf(jobId));
    }

    public long insertHeightCharge(HeightCharge charge) {
        return db.insert(HeightCharge.TABLE, charge.toContentValues());
    }

    public long insertTallyArea(ContentValues values) {
        return db.insert(TallyArea.TABLE, values);
    }
}
