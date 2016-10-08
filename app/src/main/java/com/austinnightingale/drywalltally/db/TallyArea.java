package com.austinnightingale.drywalltally.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.gabrielittner.auto.value.cursor.ColumnName;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import rx.functions.Func1;


@AutoValue
public abstract class TallyArea implements Parcelable{

    public static final String getAllTallyAreasForJob = "SELECT * FROM " + TallyArea.TABLE
            + " WHERE " + TallyArea.JOB_ID + " = ?";

    public static final String getTallyAreaWithID = "SELECT * FROM " + TallyArea.TABLE
            + " WHERE " + TallyArea.ID + " = ?";

    public static final String TABLE = "job_area_table";
    public static final String ID = "_id";
    public static final String AREA_NAME = "area_name";
    public static final String JOB_ID = "id_job";

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


    @Nullable
    @ColumnName(ID)
    public abstract Integer Id();

    @ColumnName(AREA_NAME)
    public abstract String areaName();

    @ColumnName(JOB_ID)
    public abstract int jobID();

    @ColumnName(HALF_8)
    public abstract int halfRegEight();

    @ColumnName(HALF_9)
    public abstract int halfRegNine();

    @ColumnName(HALF_10)
    public abstract int halfRegTen();

    @ColumnName(HALF_12)
    public abstract int halfRegTwelve();

    @ColumnName(HALF_14)
    public abstract int halfRegFourteen();

    @ColumnName(HALF_16)
    public abstract int halfRegSixteen();

    @ColumnName(CEIL_8)
    public abstract int ceilingEight();

    @ColumnName(CEIL_9)
    public abstract int ceilingNine();

    @ColumnName(CEIL_10)
    public abstract int ceilingTen();

    @ColumnName(CEIL_12)
    public abstract int ceilingTwelve();

    @ColumnName(CEIL_14)
    public abstract int ceilingFourteen();

    @ColumnName(CEIL_16)
    public abstract int ceilingSixteen();

    @ColumnName(FIRE_8)
    public abstract int fireEight();

    @ColumnName(FIRE_9)
    public abstract int fireNine();

    @ColumnName(FIRE_10)
    public abstract int fireTen();

    @ColumnName(FIRE_12)
    public abstract int fireTwelve();

    @ColumnName(FIRE_14)
    public abstract int fireFourteen();

    @ColumnName(FIRE_16)
    public abstract int fireSixteen();

    @ColumnName(MOLD_8)
    public abstract int moldEight();

    @ColumnName(MOLD_12)
    public abstract int moldTwelve();

    @ColumnName(FiveE_8)
    public abstract int fiveEighthRegEight();

    @ColumnName(FiveE_9)
    public abstract int fiveEighthRegNine();

    @ColumnName(FiveE_10)
    public abstract int fiveEighthRegTen();

    @ColumnName(FiveE_12)
    public abstract int fiveEighthRegTwelve();

    @ColumnName(FiveE_14)
    public abstract int fiveEighthRegFourteen();

    @ColumnName(HALF_s12)
    public abstract int halfStretchTwelve();

    @ColumnName(HALF_s14)
    public abstract int halfStretchFourteen();

    @ColumnName(HALF_s16)
    public abstract int halfStretchSixteen();

    @ColumnName(FiveE_s12)
    public abstract int fiveEightStretchTwelve();

    public static TallyArea create(Cursor cursor) {
        return AutoValue_TallyArea.createFromCursor(cursor);
    }

    public static Func1<Cursor, TallyArea> mapper() {
        return AutoValue_TallyArea.MAPPER;
    }

    public abstract ContentValues toContentValues();


    public static TypeAdapter<TallyArea> typeAdapter(Gson gson) {
        return new AutoValue_TallyArea.GsonTypeAdapter(gson);
    }

    public abstract TallyArea.Builder toBuilder();

    public static Builder builder() {
        return new AutoValue_TallyArea.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract TallyArea build();

        public abstract Builder Id(Integer value);

        public abstract Builder areaName(String name);

        public abstract Builder jobID(int value);

        public abstract Builder halfRegEight(int value);

        public abstract Builder halfRegNine(int value);

        public abstract Builder halfRegTen(int value);

        public abstract Builder halfRegTwelve(int value);

        public abstract Builder halfRegFourteen(int value);

        public abstract Builder halfRegSixteen(int value);

        public abstract Builder ceilingEight(int value);

        public abstract Builder ceilingNine(int value);

        public abstract Builder ceilingTen(int value);

        public abstract Builder ceilingTwelve(int value);

        public abstract Builder ceilingFourteen(int value);

        public abstract Builder ceilingSixteen(int value);

        public abstract Builder fireEight(int value);

        public abstract Builder fireNine(int value);

        public abstract Builder fireTen(int value);

        public abstract Builder fireTwelve(int value);

        public abstract Builder fireFourteen(int value);

        public abstract Builder fireSixteen(int value);

        public abstract Builder moldEight(int value);

        public abstract Builder moldTwelve(int value);

        public abstract Builder fiveEighthRegEight(int value);

        public abstract Builder fiveEighthRegNine(int value);

        public abstract Builder fiveEighthRegTen(int value);

        public abstract Builder fiveEighthRegTwelve(int value);

        public abstract Builder fiveEighthRegFourteen(int value);

        public abstract Builder halfStretchTwelve(int value);

        public abstract Builder halfStretchFourteen(int value);

        public abstract Builder halfStretchSixteen(int value);

        public abstract Builder fiveEightStretchTwelve(int value);
    }

    public static final class ContentBuilder {
        private final ContentValues values = new ContentValues();


        public TallyArea.ContentBuilder jobName(String areaName) {
            values.put(TallyArea.AREA_NAME, areaName);
            return this;
        }

        public TallyArea.ContentBuilder jobId(int jobId) {
            values.put(TallyArea.JOB_ID, jobId);
            return this;
        }

        public ContentValues build(){
            return values;
        }
    }
}
