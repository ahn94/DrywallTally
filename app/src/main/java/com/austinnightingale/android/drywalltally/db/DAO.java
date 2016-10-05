package com.austinnightingale.android.drywalltally.db;


import android.content.ContentValues;

import com.squareup.sqlbrite.BriteDatabase;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class DAO {

    BriteDatabase db;

    @Inject
    public DAO(BriteDatabase db) {
        this.db = db;
    }


    public Observable<List<TallyArea>> getTallyAreaListForJobId(int jobId) {
        return db.createQuery(TallyArea.TABLE, TallyArea.getAllTallyAreasForJob, String.valueOf(jobId))
                .mapToList(TallyArea.mapper());
    }

    public Observable<TallyArea> getTallyAreaWithId(int areaId) {
        return db.createQuery(TallyArea.TABLE, TallyArea.getTallyAreaWithID, String.valueOf(areaId))
                .mapToOne(TallyArea.mapper());
    }

    public Observable<Job> getJobwithId(int jobId) {
        return db.createQuery(Job.TABLE, Job.getJobwithIDQuery, String.valueOf(jobId))
                .mapToOne(Job.mapper());
    }

    public Observable<List<HeightCharge>> getHeightCharges(int jobId) {
        return db.createQuery(HeightCharge.TABLE, HeightCharge.getHeightChargesWithJobId, String.valueOf(jobId))
                .mapToList(HeightCharge.mapper());
    }

    public Observable<List<Job>> getAllJobs() {
         return db.createQuery(Job.TABLE, "SELECT * FROM " +Job.TABLE+ ";")
                .mapToList(Job.mapper());
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

    public long insertHeightCharge(HeightCharge charge) {
        return db.insert(HeightCharge.TABLE, charge.toContentValues());
    }
}
