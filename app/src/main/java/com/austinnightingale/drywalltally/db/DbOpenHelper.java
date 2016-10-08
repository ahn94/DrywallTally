package com.austinnightingale.drywalltally.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;

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
            + Job.SCREWS + " INTEGER NOT NULL DEFAULT 0,"
            + Job.DSA20 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.SHIMS + " INTEGER NOT NULL DEFAULT 0,"
            + Job.LITE_BLUE + " INTEGER NOT NULL DEFAULT 0,"
            + Job.MAGNUM_HP + " INTEGER NOT NULL DEFAULT 0,"
            + Job.HOT_45 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.HOT_90 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.HOT_20 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.HOT_5 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.LEVELCOAT + " INTEGER NOT NULL DEFAULT 0,"
            + Job.ULTRAFLEX_325 + " INTEGER NOT NULL DEFAULT 0,"
            + Job.ULTRAFLEX_450 + " INTEGER NOT NULL DEFAULT 0"
            + ")";

    private static final String CREATE_TALLY_AREA_TABLE = ""
            + "CREATE TABLE " + TallyArea.TABLE + "("
            + TallyArea.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TallyArea.AREA_NAME + " TEXT NOT NULL,"
            + TallyArea.JOB_ID + " INTEGER NOT NULL,"
            + TallyArea.HALF_8 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.HALF_9 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.HALF_10 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.HALF_12 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.HALF_14 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.HALF_16 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.CEIL_8 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.CEIL_9 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.CEIL_10 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.CEIL_12 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.CEIL_14 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.CEIL_16 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.FIRE_8 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.FIRE_9 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.FIRE_10 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.FIRE_12 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.FIRE_14 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.FIRE_16 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.MOLD_8 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.MOLD_12 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.FiveE_8 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.FiveE_9 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.FiveE_10 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.FiveE_12 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.FiveE_14 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.HALF_s12 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.HALF_s14 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.HALF_s16 + " INTEGER NOT NULL DEFAULT 0,"
            + TallyArea.FiveE_s12 + " INTEGER NOT NULL DEFAULT 0"
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
        sqLiteDatabase.execSQL(CREATE_TALLY_AREA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

}
