package com.sounds_good.brrro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pgoggijr on 5/3/15.
 */
public class WorkoutDatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_WORKOUTS = "workouts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TYPE = "type";

    public static final String TABLE_SETS = "sets";
    public static final String COLUMN_WORKOUT = "workout";
    public static final String COLUMN_NUMBER = "number";
    public static final String COLUMN_REPS = "reps";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_MAX = "max";

    private static final String DATABASE_NAME = "brrro.db";
    private static final int DATABASE_VERSION = 1;

    //database creation statement
    private static final String CREATE_WORKOUTS = "CREATE TABLE "
            + TABLE_WORKOUTS + "(" + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTO_INCREMENT, "
            + COLUMN_DATE + " VARCHAR(8), "
            + COLUMN_TYPE + " INTEGER);";

    private static final String CREATE_SETS = "CREATE TABLE "
            + TABLE_SETS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT, "
            + COLUMN_WORKOUT + " INTEGER NOT NULL, "
            + COLUMN_NUMBER + " INTEGER UNIQUE, "
            + COLUMN_REPS + " INTEGER UNIQUE, "
            + COLUMN_WEIGHT + " INTEGER UNIQUE, "
            + COLUMN_TYPE + " INTEGER NOT NULL, "
            + COLUMN_MAX + " INTEGER NOT NULL, "
            + "FOREIGN KEY(" + COLUMN_WORKOUT + ") REFERENCES " + TABLE_WORKOUTS + "(" + COLUMN_ID + "));";

    public WorkoutDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_WORKOUTS);
        db.execSQL(CREATE_SETS);
        System.out.println(CREATE_WORKOUTS);
        System.out.println(CREATE_SETS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //one day over the rainbow
    }
}
