package com.austinnightingale.drywalltally.db;

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

    public static final String SCREWS = "course_screws";
    public static final String DSA20 = "dsa20";
    public static final String SHIMS = "shims";
    public static final String LITE_BLUE = "lite_blue";
    public static final String MAGNUM_HP = "magnum_hp";
    public static final String HOT_45 = "hot_45";
    public static final String HOT_90 = "hot_90";
    public static final String HOT_20 = "hot_20";
    public static final String HOT_5 = "hot_5";
    public static final String LEVELCOAT = "levelcoat";
    public static final String ULTRAFLEX_325 = "ultraflex_325";
    public static final String ULTRAFLEX_450 = "ultraflex_450";

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

    @ColumnName(SCREWS)
    public abstract int CScrews();

    @ColumnName(DSA20)
    public abstract int dsa20();

    @ColumnName(SHIMS)
    public abstract int shims();

    @ColumnName(LITE_BLUE)
    public abstract int liteBlue();

    @ColumnName(MAGNUM_HP)
    public abstract int magnumHp();

    @ColumnName(HOT_45)
    public abstract int hot45();

    @ColumnName(HOT_90)
    public abstract int hot90();

    @ColumnName(HOT_20)
    public abstract int hot20();

    @ColumnName(HOT_5)
    public abstract int hot5();

    @ColumnName(LEVELCOAT)
    public abstract int levelcoat();

    @ColumnName(ULTRAFLEX_325)
    public abstract int ultraflex325();

    @ColumnName(ULTRAFLEX_450)
    public abstract int ultraflex450();


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

        abstract Builder CScrews(int value);

        abstract Builder dsa20(int value);

        abstract Builder shims(int value);

        abstract Builder liteBlue(int value);

        abstract Builder magnumHp(int value);

        abstract Builder hot45(int value);

        abstract Builder hot90(int value);

        abstract Builder hot20(int value);

        abstract Builder hot5(int value);

        abstract Builder levelcoat(int value);

        abstract Builder ultraflex325(int value);

        abstract Builder ultraflex450(int value);
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
