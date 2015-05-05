package com.sounds_good.brrro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;

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
        WorkoutDatabaseHelper.COLUMN_MAX, WorkoutDatabaseHelper.COLUMN_TYPE };

    public WorkoutDatabaseAdapter(Context context) {
        dbHelper = new WorkoutDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Workout getWorkout(String date, int type) {
        int id;
        int workoutType;
        Exercise[] exercises;
        int[] exerciseTypes;
        Cursor cursor = database.query(WorkoutDatabaseHelper.TABLE_WORKOUTS, WorkoutsColumns,
                WorkoutDatabaseHelper.COLUMN_DATE + " = " + date,null,null,null,null);
        if(cursor.getCount() < 1) {
            return new Workout(type, date);
        }
        cursor.moveToNext();
        id = cursor.getInt(0);
        workoutType = cursor.getInt(2);
        cursor.close();
        if(workoutType == Workout.WORKOUT_A) {
            exerciseTypes = Workout.A_EXERCISES;
        } else {
            exerciseTypes = Workout.B_EXERCISES;
        }
        exercises = new Exercise[exerciseTypes.length];
        for(int i = 0; i < exercises.length; i++) {
            exercises[i] = getExercise(id, exerciseTypes[i]);
        }

        return new Workout(workoutType,date,exercises);
    }

    private Exercise getExercise(int workoutID, int exerciseType) {
        Exercise toRet;
        int[] setArr;
        Cursor cursor = database.query(WorkoutDatabaseHelper.TABLE_SETS, SetsColumns,
                WorkoutDatabaseHelper.COLUMN_WORKOUT + " = " + workoutID +
                        " AND " + WorkoutDatabaseHelper.COLUMN_TYPE + " = " +
                        exerciseType,
                null, null, null, WorkoutDatabaseHelper.COLUMN_NUMBER + " ASC");

        if (cursor.getCount() < 1) {
            return null;
        }
        setArr = new int[cursor.getCount()];

        while(cursor.moveToNext()) {
            setArr[cursor.getInt(2)] = cursor.getInt(3);
        }
        cursor.moveToPrevious();
        toRet = new Exercise(setArr.length,cursor.getInt(5),cursor.getInt(4),cursor.getInt(6),setArr);
        cursor.close();
        return toRet;
    }

    public String[] getWorkouts(){
        String[] workoutDates;
        int i = 0;
        Cursor cursor = database.query(WorkoutDatabaseHelper.TABLE_WORKOUTS, WorkoutsColumns,
                null, null, null, null, WorkoutDatabaseHelper.COLUMN_DATE + " DESC");

        workoutDates = new String[cursor.getCount()];
        while(cursor.moveToNext()){
            workoutDates[i] = cursor.getString(1);
            i++;
        }

        cursor.close();
        return workoutDates;
    }

    public boolean updateWorkout(Workout workout) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        ContentValues workoutValues = new ContentValues();
        database = dbHelper.getWritableDatabase();
        Exercise[] exercises = workout.getExercises();
        int affected;
        long workoutId;

        workoutId = getWorkoutId(workout.getDate());

        //put in and insert workout Values
        workoutValues.put(WorkoutDatabaseHelper.COLUMN_DATE,workout.getDate());
        workoutValues.put(WorkoutDatabaseHelper.COLUMN_TYPE,workout.getType());

        try {
            affected = database.update(WorkoutDatabaseHelper.TABLE_WORKOUTS,
                    workoutValues,
                    WorkoutDatabaseHelper.COLUMN_ID + " = ? ", new String[]{String.valueOf(workoutId)});

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
            int currReps = curr.getReps();
            for (int j = 0; j < currSets.length; j++) {
                ContentValues setValues = new ContentValues();

                setValues.put(WorkoutDatabaseHelper.COLUMN_REPS, currSets[j]);
                setValues.put(WorkoutDatabaseHelper.COLUMN_WEIGHT, currWeight);

                try{
                    database.update(WorkoutDatabaseHelper.TABLE_SETS, setValues,
                            dbHelper.COLUMN_WORKOUT + " = ? AND " +
                                    dbHelper.COLUMN_TYPE + " = ? AND " +
                                    dbHelper.COLUMN_NUMBER + " = ?"
                            ,
                            new String[]{String.valueOf(workoutId), String.valueOf(curr.getType()),
                                    String.valueOf(j)});
                } catch(SQLException e) {
                    System.out.println("Updating reps failed: ");
                    System.out.println(e);
                    return false;
                }
            }
        }
        return true;
    }

    public long getWorkoutId(String date) {
        Cursor cursor;
        try {
            cursor = database.query(WorkoutDatabaseHelper.TABLE_WORKOUTS, new String[]{"_id"},
                    WorkoutDatabaseHelper.COLUMN_DATE + " = " + date, null, null, null, null);
        } catch (SQLException e) {
            System.out.println(e);
            cursor = null;
        }
        if (cursor == null || cursor.getCount() < 1) {
            return -1;
        }
        cursor.moveToNext();
        return cursor.getLong(0);
    }

    public boolean insertWorkout(Workout workout) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        ContentValues workoutValues = new ContentValues();
        database = dbHelper.getWritableDatabase();
        Exercise[] exercises = workout.getExercises();
        long workoutId;

        //put in and insert workout Values
        workoutValues.put(WorkoutDatabaseHelper.COLUMN_DATE, workout.getDate());
        workoutValues.put(WorkoutDatabaseHelper.COLUMN_TYPE, workout.getType());

        try {
            workoutId = database.insert(WorkoutDatabaseHelper.TABLE_WORKOUTS,
                    WorkoutDatabaseHelper.COLUMN_TYPE,
                    workoutValues);
        } catch (SQLException e) {
            System.out.println("Inserting workout failed: ");
            System.out.println(e);
            return false;
        }

        //insert exercise values
        for (Exercise curr : exercises) {
            int[] currSets = curr.getSets();
            int currWeight = curr.getWeight();
            int currType = curr.getType();
            int currReps = curr.getReps();
            for (int j = 0; j < currSets.length; j++) {
                ContentValues setValues = new ContentValues();

                setValues.put(WorkoutDatabaseHelper.COLUMN_WORKOUT, workoutId);
                setValues.put(WorkoutDatabaseHelper.COLUMN_NUMBER, j);
                setValues.put(WorkoutDatabaseHelper.COLUMN_REPS, currSets[j]);
                setValues.put(WorkoutDatabaseHelper.COLUMN_WEIGHT, currWeight);
                setValues.put(WorkoutDatabaseHelper.COLUMN_TYPE, currType);
                setValues.put(WorkoutDatabaseHelper.COLUMN_MAX, currReps);

                try {
                    database.insert(WorkoutDatabaseHelper.TABLE_SETS,
                            WorkoutDatabaseHelper.COLUMN_WORKOUT,
                            setValues);
                } catch (SQLException e) {
                    System.out.println("Inserting reps failed: ");
                    System.out.println(e);
                    return false;
                }
            }
        }
        return true;
    }
}
