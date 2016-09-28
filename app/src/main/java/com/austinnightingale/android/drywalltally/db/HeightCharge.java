package com.austinnightingale.android.drywalltally.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.Nullable;

import com.gabrielittner.auto.value.cursor.ColumnName;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import rx.functions.Func1;

/**
 * Created by Austin on 9/6/2016.
 */

@AutoValue
public abstract class HeightCharge {

    public static final String getHeightChargesWithJobId = "SELECT * FROM " + HeightCharge.TABLE
            + " WHERE " + HeightCharge.JOB_ID + " = ?";

    public static final String TABLE = "table_height_charges";

    public static final String ID = "_id";
    public static final String JOB_ID = "job_id";
    public static final String HEIGHT_CHARGE = "height_charge";

    @Nullable
    @ColumnName(ID) public abstract Integer chargeId();
    @ColumnName(JOB_ID) public abstract int jobId();
    @ColumnName(HEIGHT_CHARGE) public abstract String heightCharge();

    public static HeightCharge create(Cursor cursor) {
        return AutoValue_HeightCharge.createFromCursor(cursor);
    }

    public static TypeAdapter<HeightCharge> typeAdapter(Gson gson) {
        return new AutoValue_HeightCharge.GsonTypeAdapter(gson);
    }

    public static Func1<Cursor, HeightCharge> mapper() {
        return AutoValue_HeightCharge.MAPPER;
    }

    public static Builder builder() {
        return new AutoValue_HeightCharge.Builder();
    }

    public abstract ContentValues toContentValues();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setChargeId(Integer chargeId);
        public abstract Builder setJobId(int jobId);
        public abstract Builder setHeightCharge(String heightCharge);
        public abstract HeightCharge build();
    }

}
