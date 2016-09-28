package com.austinnightingale.android.drywalltally.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Austin on 8/12/2016.
 */
public class DbOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 2;

    private static final String CREATE_JOBS_TABLE = ""
            + "CREATE TABLE " + Job.TABLE + "("
            + Job.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Job.JOB_NAME + " TEXT NOT NULL,"
            + Job.CREATED_AT + " DATE DEFAULT (datetime('now','localtime')),"
            + Job.ADDRESS + " TEXT NOT NULL DEFAULT '',"
            + Job.COMMENT + " TEXT NOT NULL DEFAULT '',"
            + Job.CEIL_FINISH + " TEXT NOT NULL DEFAULT 'Stomp Knockdown',"
            + Job.WALL_FINISH + " TEXT NOT NULL DEFAULT 'Smooth',"
            + Job.BULL_CORNER + " INTEGER NOT NULL DEFAULT 0,"
            + Job.BULL_ADAPTER_FLAG + " INTEGER NOT NULL DEFAULT 0,"
            + Job.WINDOW_WRAP_3SIDE + " INTEGER NOT NULL DEFAULT 0,"
            + Job.WINDOW_WRAP_4SIDE + " INTEGER NOT NULL DEFAULT 0,"
            + Job.ARCH_WRAP + " INTEGER NOT NULL DEFAULT 0,"
            + Job.ARCH_CORNER + " INTEGER NOT NULL DEFAULT 0,"
            + Job.DOOR_WRAP + " INTEGER NOT NULL DEFAULT 0,"
            + Job.OPENING_WRAP + " INTEGER NOT NULL DEFAULT 0,"
            + Job.L_TRIM + " INTEGER NOT NULL DEFAULT 0,"
            + Job.HALF_8 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.HALF_9 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.HALF_10 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.HALF_12 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.HALF_14 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.HALF_16 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.CEIL_8 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.CEIL_9 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.CEIL_10 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.CEIL_12 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.CEIL_14 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.CEIL_16 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.FIRE_8 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.FIRE_9 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.FIRE_10 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.FIRE_12 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.FIRE_14 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.FIRE_16 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.MOLD_8 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.MOLD_12 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.FiveE_8 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.FiveE_9 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.FiveE_10 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.FiveE_12 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.FiveE_14 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.HALF_s12 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.HALF_s14 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.HALF_s16 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.FiveE_s12 + " INTEGER NOT NULL DEFAULT 0"
            + ")";

    public static final String CREATE_HEIGHT_CHARGE_TABLE = ""
            + "CREATE TABLE " + HeightCharge.TABLE + " ("
            + HeightCharge.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + HeightCharge.JOB_ID + " INTEGER NOT NULL,"
            + HeightCharge.HEIGHT_CHARGE + " TEXT NOT NULL"
            + ")";

    public DbOpenHelper(Context context) {
        super(context, "jobs.db", null, VERSION);
    }

    private String addColumntoJobTable(String column) {
        return "ALTER TABLE " +Job.TABLE+ " ADD " +column+ " INTEGER NOT NULL DEFAULT 0;";
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_JOBS_TABLE);
        sqLiteDatabase.execSQL(CREATE_HEIGHT_CHARGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            sqLiteDatabase.execSQL(addColumntoJobTable(Job.FIRE_8));
            sqLiteDatabase.execSQL(addColumntoJobTable(Job.FIRE_9));
            sqLiteDatabase.execSQL(addColumntoJobTable(Job.FIRE_10));
            sqLiteDatabase.execSQL(addColumntoJobTable(Job.FIRE_12));
            sqLiteDatabase.execSQL(addColumntoJobTable(Job.FIRE_14));
            sqLiteDatabase.execSQL(addColumntoJobTable(Job.FIRE_16));
        }
    }

}
