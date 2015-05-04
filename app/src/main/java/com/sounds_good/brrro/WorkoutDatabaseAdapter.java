package com.sounds_good.brrro;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Peter on 5/4/2015.
 * adapter for database abstraction
 */
public class WorkoutDatabaseAdapter {
    private SQLiteDatabase database;
    private WorkoutDatabaseHelper dbHelper;
    private String[] WorkoutsColumns = { WorkoutDatabaseHelper.COLUMN_ID,
        WorkoutDatabaseHelper.COLUMN_DATE, WorkoutDatabaseHelper.COLUMN_TYPE };
    private String[] SetsColumns = { WorkoutDatabaseHelper.COLUMN_ID,
        WorkoutDatabaseHelper.COLUMN_WORKOUT, WorkoutDatabaseHelper.COLUMN_NUMBER,
        WorkoutDatabaseHelper.COLUMN_REPS, WorkoutDatabaseHelper.COLUMN_WEIGHT,
        WorkoutDatabaseHelper.COLUMN_TYPE };

    public WorkoutDatabaseAdapter(Context context) {
        dbHelper = new WorkoutDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Date[] getWorkouts(){
        Date[] workoutDates;
        int i = 0;
        Cursor cursor = database.query(WorkoutDatabaseHelper.TABLE_WORKOUTS, WorkoutsColumns,
                null, null, null, null, WorkoutDatabaseHelper.COLUMN_DATE + " DESC");

        workoutDates = new Date[cursor.getCount()];
        while(cursor.moveToNext()){
            workoutDates[i] = new Date(cursor.getInt(1) * 1000L);
            i++;
        }

        cursor.close();
        return workoutDates;
    }
    public boolean insertWorkout(Workout workout) {
        ContentValues workoutValues = new ContentValues();
        database = dbHelper.getWritableDatabase();
        Exercise[] exercises = workout.getExercises();
        long workoutId;

        //put in and insert workout Values
        workoutValues.put(WorkoutDatabaseHelper.COLUMN_DATE,workout.getDate().getTime());
        workoutValues.put(WorkoutDatabaseHelper.COLUMN_TYPE,workout.getType());

        try {
            workoutId = database.insert(WorkoutDatabaseHelper.TABLE_WORKOUTS,
                    WorkoutDatabaseHelper.COLUMN_TYPE,
                    workoutValues);
        } catch(SQLException e) {
            System.out.println("Inserting workout failed: ");
            System.out.println(e);
            return false;
        }

        //insert exercise values
        for (Exercise curr : exercises) {
            int[] currSets = curr.getSets();
            int currWeight = curr.getWeight();
            int currType = curr.getType();
            for (int j = 0; j < currSets.length; j++) {
                ContentValues setValues = new ContentValues();

                setValues.put(WorkoutDatabaseHelper.COLUMN_WORKOUT, workoutId);
                setValues.put(WorkoutDatabaseHelper.COLUMN_NUMBER, j);
                setValues.put(WorkoutDatabaseHelper.COLUMN_REPS, currSets[j]);
                setValues.put(WorkoutDatabaseHelper.COLUMN_WEIGHT, currWeight);
                setValues.put(WorkoutDatabaseHelper.COLUMN_TYPE, currType);

                try{
                    database.insert(WorkoutDatabaseHelper.TABLE_SETS,
                            WorkoutDatabaseHelper.COLUMN_WORKOUT,
                            setValues);
                } catch(SQLException e) {
                    System.out.println("Inserting reps failed: ");
                    System.out.println(e);
                    return false;
                }
            }
        }
        return true;
    }
}
