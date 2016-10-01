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
 * Created by Austin on 8/12/2016.
 */

@AutoValue
public abstract class Job {
    public static final String getJobwithIDQuery = "SELECT * FROM " + Job.TABLE
            + " WHERE " + Job.ID + " = ?";

    public static final String getAllJobs = "SELECT * FROM " + Job.TABLE;

    public static final String TABLE = "jobs_table";
    public static final String ID = "_id";
    public static final String JOB_NAME = "job_name";
    public static final String CREATED_AT = "created_at";



    public static final String HALF_8 = "half_regular_eight";
    public static final String HALF_9 = "half_regular_nine";
    public static final String HALF_10 = "half_regular_ten";
    public static final String HALF_12 = "half_regular_twelve";
    public static final String HALF_14 = "half_regular_fourteen";
    public static final String HALF_16 = "half_regular_sixteen";
    public static final String CEIL_8 = "ceiling_eight";

    public static final String CEIL_9 = "ceiling_nine";
    public static final String CEIL_10 = "ceiling_ten";
    public static final String CEIL_12 = "ceiling_twelve";
    public static final String CEIL_14 = "ceiling_fourteen";
    public static final String CEIL_16 = "ceiling_sixteen";

    public static final String FIRE_8 = "fire_8";
    public static final String FIRE_9 = "fire_9";
    public static final String FIRE_10 = "fire_10";
    public static final String FIRE_12 = "fire_12";
    public static final String FIRE_14 = "fire_14";
    public static final String FIRE_16 = "fire_16";
    public static final String MOLD_8 = "mold_resistant_eight";
    public static final String MOLD_12 = "mold_resistant_twelve";

    public static final String FiveE_8 = "five_eighth_regular_eight";
    public static final String FiveE_9 = "five_eighth_regular_nine";
    public static final String FiveE_10 = "five_eighth_regular_ten";
    public static final String FiveE_12 = "five_eighth_regular_twelve";
    public static final String FiveE_14 = "five_eighth_regular_fourteen";
    public static final String HALF_s12 = "half_stretch_twelve";

    public static final String HALF_s14 = "half_stretch_fourteen";
    public static final String HALF_s16 = "half_stretch_sixteen";
    public static final String FiveE_s12 = "five_eigth_stretch_twelve";

    public static final String COLUMN_NAME = "job_column";

    public static final String ADDRESS = "address";
    public static final String COMMENT = "comment";
    public static final String CEIL_FINISH = "ceiling_finish";
    public static final String WALL_FINISH = "wall_finish";
    public static final String BULL_CORNER = "bullnose_corner";
    public static final String BULL_ADAPTER_FLAG = "bullnose_adapter_flag";
    public static final String WINDOW_WRAP_3SIDE = "window_wrap_3sided";
    public static final String WINDOW_WRAP_4SIDE = "window_wrap_4sided";
    public static final String ARCH_WRAP = "arch_wrap";
    public static final String ARCH_CORNER = "arch_corner";
    public static final String DOOR_WRAP = "door_wrap";
    public static final String OPENING_WRAP = "opening_wrap";
    public static final String L_TRIM = "l_trim";

    @Nullable
    @ColumnName(ID)
    public abstract Integer jobID();

    @ColumnName(JOB_NAME)
    public abstract String jobName();

    @Nullable
    @ColumnName(CREATED_AT)
    public abstract String createdOn();

    @ColumnName(ADDRESS)
    public abstract String address();

    @ColumnName(COMMENT)
    public abstract String comment();

    @ColumnName(CEIL_FINISH)
    public abstract String ceilFinish();

    @ColumnName(WALL_FINISH)
    public abstract String wallFinish();

    @ColumnName(BULL_CORNER)
    public abstract int bullCorners();

    @ColumnName(BULL_ADAPTER_FLAG)
    public abstract int bullAdapterFlag();

    @ColumnName(WINDOW_WRAP_3SIDE)
    public abstract int windowWrap3S();

    @ColumnName(WINDOW_WRAP_4SIDE)
    public abstract int windowWrap4S();

    @ColumnName(ARCH_WRAP)
    public abstract int archWrap();

    @ColumnName(ARCH_CORNER)
    public abstract int archCorner();

    @ColumnName(DOOR_WRAP)
    public abstract int doorWrap();

    @ColumnName(OPENING_WRAP)
    public abstract int openingWrap();

    @ColumnName(L_TRIM)
    public abstract int lTrim();


    public static Job create(Cursor cursor) {
        return AutoValue_Job.createFromCursor(cursor);
    }

    public static Func1<Cursor, Job> mapper() {
        return AutoValue_Job.MAPPER;
    }

    public abstract ContentValues toContentValues();


    public static TypeAdapter<Job> typeAdapter(Gson gson) {
        return new AutoValue_Job.GsonTypeAdapter(gson);
    }

    public abstract Builder toBuilder();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder jobID(Integer value);

        public abstract Job build();

        public abstract Builder jobName(String name);

        abstract Builder createdOn(String date);

        abstract Builder address(String address);

        abstract Builder comment(String comment);

        abstract Builder ceilFinish(String value);

        abstract Builder wallFinish(String value);

        abstract Builder bullCorners(int value);

        abstract Builder bullAdapterFlag(int value);

        abstract Builder windowWrap3S(int value);

        abstract Builder windowWrap4S(int value);

        abstract Builder archWrap(int value);

        abstract Builder archCorner(int value);

        abstract Builder doorWrap(int value);

        abstract Builder openingWrap(int value);

        abstract Builder lTrim(int value);
    }

    public static final class ContentBuilder {
        private final ContentValues values = new ContentValues();

        public ContentBuilder jobName(String jobName) {
            values.put(Job.JOB_NAME, jobName);
            return this;
        }

        public ContentBuilder comment(String comment) {
            values.put(Job.COMMENT, comment);
            return this;
        }

        public ContentBuilder address(String address) {
            values.put(Job.ADDRESS, address);
            return this;
        }

        public ContentBuilder bullNoseCorner(int bullNoseCorner) {
            values.put(Job.BULL_CORNER, bullNoseCorner);
            return this;
        }

        public ContentBuilder bullAdapterFlag(int flag) {
            values.put(Job.BULL_ADAPTER_FLAG, flag);
            return this;
        }

        public ContentBuilder windowWrap3Side(int windowWrap3Side) {
            values.put(Job.WINDOW_WRAP_3SIDE, windowWrap3Side);
            return this;
        }

        public ContentBuilder windowWrap4Side(int windowWrap4Side) {
            values.put(Job.WINDOW_WRAP_4SIDE, windowWrap4Side);
            return this;
        }

        public ContentBuilder archWrap(int archWraps) {
            values.put(Job.ARCH_WRAP, archWraps);
            return this;
        }

        public ContentBuilder archCorner(int archCorner) {
            values.put(Job.ARCH_CORNER, archCorner);
            return this;
        }

        public ContentBuilder doorWrap(int doorWraps) {
            values.put(Job.DOOR_WRAP, doorWraps);
            return this;
        }

        public ContentBuilder openingWrap(int openingWraps) {
            values.put(Job.OPENING_WRAP, openingWraps);
            return this;
        }

        public ContentBuilder linearLTrim(int lTrim) {
            values.put(Job.L_TRIM, lTrim);
            return this;
        }

        public ContentValues build() {
            return values;
        }
    }


}
