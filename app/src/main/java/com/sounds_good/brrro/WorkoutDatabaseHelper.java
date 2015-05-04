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

    private static final String DATABASE_NAME = "workouts.db";
    private static final int DATABASE_VERSION = 1;

    //database creation statement
    private static final String CREATE_WORKOUTS = "create table "
            + TABLE_WORKOUTS + "(" + COLUMN_ID
            + " integer primary key autoincrement, "
            + COLUMN_DATE + " varchar(8), "
            + COLUMN_TYPE + " integer);";

    private static final String CREATE_SETS = "create table "
            + TABLE_SETS + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_WORKOUT + "integer not null, "
            + COLUMN_NUMBER + " integer unique, "
            + COLUMN_REPS + " integer unique, "
            + COLUMN_WEIGHT + " integer unique, "
            + COLUMN_TYPE + " integer not null, "
            + "FOREIGN KEY(" + COLUMN_WORKOUT + "( REFERENCES " + TABLE_WORKOUTS + "(" + COLUMN_ID + "));";

    public WorkoutDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(CREATE_WORKOUTS);
        //db.execSQL(CREATE_SETS);
        System.out.println(CREATE_WORKOUTS);
        System.out.println(CREATE_SETS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //one day over the rainbow
    }
}
