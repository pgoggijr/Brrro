package com.sounds_good.brrro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkoutActivity extends ActionBarActivity
    implements WeightDialog.WeightDialogListener{


    private Workout workout;
    private Exercise[] exercises;
    private String date;
    private WorkoutDatabaseAdapter dbAdapter;
    private int[] weights;
    private int type;
    private WeightDialog dialog;
    private int weightDialogCaller;

    /*weight dialog listener methods */
    @Override
    public void onDialogPositiveClick(WeightDialog dialog) {
        String weight = dialog.getWeight();
        int weightInt;
        try {
            weightInt = Integer.parseInt(weight);
            ((TextView) findViewById(weightDialogCaller)).setText(String.valueOf(weight));
            updateWeight(weightDialogCaller, weightInt);
        } catch (NumberFormatException e) {
            Toast toast  = Toast.makeText(getApplicationContext(),"Please enter a valid number", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    @Override
    public void onDialogNegativeClick(WeightDialog dialog) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dialog = new WeightDialog();
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        dbAdapter = new WorkoutDatabaseAdapter(this);

        /* get date from previous activity */
        date = intent.getStringExtra("date");
        if(date == null) {
            date = sdf.format(new Date());
        }

        /* get workout from database */
        dbAdapter.open();
        workout = dbAdapter.getWorkout(date);
        if (workout == null) {
            if (dbAdapter.getLastWorkoutType() == Workout.WORKOUT_A) {
                workout = new Workout(Workout.WORKOUT_B, date);
            } else {
                workout = new Workout(Workout.WORKOUT_A, date);
            }
            dbAdapter.insertWorkout(workout);
        }
        dbAdapter.close();

        /*initialize layout */
        exercises = workout.getExercises();
        type = workout.getType();
        if(type == Workout.WORKOUT_A) {
            setContentView(R.layout.activity_workout_a);
        } else {
            setContentView(R.layout.activity_workout_b);
        }
        initViews();


    }

    /* opens the change weight dialog box when the weight of an exercise is pressed*/
    public void openWeightDialog(View view) {
        weightDialogCaller = view.getId();
        String weight = ((TextView) view).getText().toString();
        dialog.setWeight(weight);
        dialog.show(getFragmentManager(),"openWeightDialog");
    }

    /* callback for the weight dialog to actually update the weight of an exercise */
    public void updateWeight(int viewId, int weight) {
        String viewName = getResources().getResourceEntryName(viewId);
        if(viewName.matches("edit_([A-z])*")) {
            String[] idArr = viewName.split("_");
            int index = indexFinder(idArr[1]);
            exercises[index].setWeight(weight);
        }
    }

    /* increments reps for a set by 1 when user taps the button associated with a set */
    public void updateSet(View view) {
        int id = view.getId();
        String idName = getResources().getResourceEntryName(id);
        if(idName.matches("button_([A-z])*_[1-5]")) {
            String[] idArr;
            idArr = idName.split("_");
            int index = indexFinder(idArr[1]);
            int set = Integer.parseInt(idArr[2]);

            ((Button) view).setText(String.valueOf(exercises[index].updateSet(set - 1)));
        }
    }

    /* updates the workout in the database */
    public void updateWorkout(View view) {
        dbAdapter.open();
        dbAdapter.updateWorkout(workout);
        dbAdapter.getWorkout(workout.getDate()).printWorkout();
        dbAdapter.close();
    }

    /* used to get a workout index by workout name */
    private int indexFinder(String workoutName) {
        if(workoutName.compareTo("bench") == 0
                || workoutName.compareTo("deadlift") == 0) {
            return 1;
        } else if(workoutName.compareTo("row") == 0
                || workoutName.compareTo("standing") == 0) {
            return 2;
        } else if(workoutName.compareTo("shrugs") == 0
                || workoutName.compareTo("bent") == 0) {
            return 3;
        } else if(workoutName.compareTo("tricep") == 0
                || workoutName.compareTo("close") == 0) {
            return 4;
        } else if(workoutName.compareTo("incline") == 0) {
            return 5;
        } else if(workoutName.compareTo("hyperextensions") == 0
                || (workoutName.compareTo("crunches") == 0
                && type == Workout.WORKOUT_B)) {
            return 6;
        } else if(workoutName.compareTo("crunches") == 0) {
            return 7;
        } else {
            return 0;
        }
    }

    /* initializes all buttons and weights in the activity */
    public void initViews() {
        int[] setArr;

        /* squats */
        setArr = exercises[0].getSets();
        ((Button) findViewById(R.id.button_squats_1)).setText(String.valueOf(setArr[0]));
        ((Button) findViewById(R.id.button_squats_2)).setText(String.valueOf(setArr[1]));
        ((Button) findViewById(R.id.button_squats_3)).setText(String.valueOf(setArr[2]));
        ((Button) findViewById(R.id.button_squats_4)).setText(String.valueOf(setArr[3]));
        ((Button) findViewById(R.id.button_squats_5)).setText(String.valueOf(setArr[4]));

        ((TextView) findViewById(R.id.edit_squats)).setText(String.valueOf(exercises[0].getWeight()));

        if(type == Workout.WORKOUT_A) {
        /* bench */
            setArr = exercises[1].getSets();
            ((Button) findViewById(R.id.button_bench_1)).setText(String.valueOf(setArr[0]));
            ((Button) findViewById(R.id.button_bench_2)).setText(String.valueOf(setArr[1]));
            ((Button) findViewById(R.id.button_bench_3)).setText(String.valueOf(setArr[2]));
            ((Button) findViewById(R.id.button_bench_4)).setText(String.valueOf(setArr[3]));
            ((Button) findViewById(R.id.button_bench_5)).setText(String.valueOf(setArr[4]));

            ((TextView) findViewById(R.id.edit_bench)).setText(String.valueOf(exercises[1].getWeight()));

        /* row */
            setArr = exercises[2].getSets();
            ((Button) findViewById(R.id.button_row_1)).setText(String.valueOf(setArr[0]));
            ((Button) findViewById(R.id.button_row_2)).setText(String.valueOf(setArr[1]));
            ((Button) findViewById(R.id.button_row_3)).setText(String.valueOf(setArr[2]));
            ((Button) findViewById(R.id.button_row_4)).setText(String.valueOf(setArr[3]));
            ((Button) findViewById(R.id.button_row_5)).setText(String.valueOf(setArr[4]));

            ((TextView) findViewById(R.id.edit_row)).setText(String.valueOf(exercises[2].getWeight()));

        /* shrugs */
            setArr = exercises[3].getSets();
            ((Button) findViewById(R.id.button_shrugs_1)).setText(String.valueOf(setArr[0]));
            ((Button) findViewById(R.id.button_shrugs_2)).setText(String.valueOf(setArr[1]));
            ((Button) findViewById(R.id.button_shrugs_3)).setText(String.valueOf(setArr[2]));

            ((TextView) findViewById(R.id.edit_shrugs)).setText(String.valueOf(exercises[3].getWeight()));

        /* tricep extensions */
            setArr = exercises[4].getSets();
            ((Button) findViewById(R.id.button_tricep_1)).setText(String.valueOf(setArr[0]));
            ((Button) findViewById(R.id.button_tricep_2)).setText(String.valueOf(setArr[1]));
            ((Button) findViewById(R.id.button_tricep_3)).setText(String.valueOf(setArr[2]));

            ((TextView) findViewById(R.id.edit_tricep)).setText(String.valueOf(exercises[4].getWeight()));

        /* incline curls */
            setArr = exercises[5].getSets();
            ((Button) findViewById(R.id.button_incline_1)).setText(String.valueOf(setArr[0]));
            ((Button) findViewById(R.id.button_incline_2)).setText(String.valueOf(setArr[1]));
            ((Button) findViewById(R.id.button_incline_3)).setText(String.valueOf(setArr[2]));

            ((TextView) findViewById(R.id.edit_incline)).setText(String.valueOf(exercises[5].getWeight()));

        /* hyperextensions */
            setArr = exercises[6].getSets();
            ((Button) findViewById(R.id.button_hyperextensions_1)).setText(String.valueOf(setArr[0]));
            ((Button) findViewById(R.id.button_hyperextensions_2)).setText(String.valueOf(setArr[1]));

            ((TextView) findViewById(R.id.edit_hyperextensions)).setText(String.valueOf(exercises[6].getWeight()));

        /* cable crunches */
            setArr = exercises[7].getSets();
            ((Button) findViewById(R.id.button_crunches_1)).setText(String.valueOf(setArr[0]));
            ((Button) findViewById(R.id.button_crunches_2)).setText(String.valueOf(setArr[1]));
            ((Button) findViewById(R.id.button_crunches_3)).setText(String.valueOf(setArr[2]));

            ((TextView) findViewById(R.id.edit_crunches)).setText(String.valueOf(exercises[7].getWeight()));
        } else {
        /* deadlift */
            setArr = exercises[1].getSets();
            ((Button) findViewById(R.id.button_deadlift_1)).setText(String.valueOf(setArr[0]));

            ((TextView) findViewById(R.id.edit_deadlift)).setText(String.valueOf(exercises[1].getWeight()));

        /* standing */
            setArr = exercises[2].getSets();
            ((Button) findViewById(R.id.button_standing_1)).setText(String.valueOf(setArr[0]));
            ((Button) findViewById(R.id.button_standing_2)).setText(String.valueOf(setArr[1]));
            ((Button) findViewById(R.id.button_standing_3)).setText(String.valueOf(setArr[2]));
            ((Button) findViewById(R.id.button_standing_4)).setText(String.valueOf(setArr[3]));
            ((Button) findViewById(R.id.button_standing_5)).setText(String.valueOf(setArr[4]));

            ((TextView) findViewById(R.id.edit_standing)).setText(String.valueOf(exercises[2].getWeight()));

        /* bent */
            setArr = exercises[3].getSets();
            ((Button) findViewById(R.id.button_bent_1)).setText(String.valueOf(setArr[0]));
            ((Button) findViewById(R.id.button_bent_2)).setText(String.valueOf(setArr[1]));
            ((Button) findViewById(R.id.button_bent_3)).setText(String.valueOf(setArr[2]));
            ((Button) findViewById(R.id.button_bent_4)).setText(String.valueOf(setArr[3]));
            ((Button) findViewById(R.id.button_bent_5)).setText(String.valueOf(setArr[4]));

            ((TextView) findViewById(R.id.edit_bent)).setText(String.valueOf(exercises[3].getWeight()));

        /* close grip bench press*/
            setArr = exercises[4].getSets();
            ((Button) findViewById(R.id.button_close_1)).setText(String.valueOf(setArr[0]));
            ((Button) findViewById(R.id.button_close_2)).setText(String.valueOf(setArr[1]));
            ((Button) findViewById(R.id.button_close_3)).setText(String.valueOf(setArr[2]));

            ((TextView) findViewById(R.id.edit_close)).setText(String.valueOf(exercises[4].getWeight()));

        /* incline curls */
            setArr = exercises[5].getSets();
            ((Button) findViewById(R.id.button_incline_1)).setText(String.valueOf(setArr[0]));
            ((Button) findViewById(R.id.button_incline_2)).setText(String.valueOf(setArr[1]));
            ((Button) findViewById(R.id.button_incline_3)).setText(String.valueOf(setArr[2]));

            ((TextView) findViewById(R.id.edit_incline)).setText(String.valueOf(exercises[5].getWeight()));

        /* hyperextensions */
            setArr = exercises[6].getSets();
            ((Button) findViewById(R.id.button_incline_1)).setText(String.valueOf(setArr[0]));
            ((Button) findViewById(R.id.button_incline_2)).setText(String.valueOf(setArr[1]));
            ((Button) findViewById(R.id.button_incline_3)).setText(String.valueOf(setArr[2]));

            ((TextView) findViewById(R.id.edit_crunches)).setText(String.valueOf(exercises[6].getWeight()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_workout_a, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            workout.printWorkout();
            dbAdapter.open();
            System.out.println(dbAdapter.getWorkoutId(workout.getDate()));
            dbAdapter.close();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
