package com.sounds_good.brrro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkoutActivity extends ActionBarActivity {

    private Workout workout;
    private Exercise[] exercises;
    private String date;
    private WorkoutDatabaseAdapter dbAdapter;
    private int[] weights;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        dbAdapter = new WorkoutDatabaseAdapter(this);

        date = intent.getStringExtra("date");

        if(date == null) {
            date = sdf.format(new Date());
        }

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
        exercises = workout.getExercises();
        type = workout.getType();
        if(type == Workout.WORKOUT_A) {
            setContentView(R.layout.activity_workout_a);
        } else {
            setContentView(R.layout.activity_workout_b);
        }
        initViews();
    }

    public void updateSet(View view) {
        int id = view.getId();
        String idName = getResources().getResourceEntryName(id);
        if(idName.matches("button_([A-z])*_[1-5]")) {
            String[] idArr;
            idArr = idName.split("_");
            int index;
            int set = Integer.parseInt(idArr[2]);

            /* Switching isn't working on a string, despite compiling w/ java 7
                Thanks java, for this disgusting if else block */
            if(idArr[1].compareTo("squats") == 0) {
                index = 0;
            } else if(idArr[1].compareTo("bench") == 0
                || idArr[1].compareTo("deadlift") == 0) {
                index = 1;
            } else if(idArr[1].compareTo("row") == 0
                    || idArr[1].compareTo("standing") == 0) {
                index = 2;
            } else if(idArr[1].compareTo("shrugs") == 0
                    || idArr[1].compareTo("bent") == 0) {
                index = 3;
            } else if(idArr[1].compareTo("tricep") == 0
                    || idArr[1].compareTo("close") == 0) {
                index = 4;
            } else if(idArr[1].compareTo("incline") == 0) {
                index = 5;
            } else if(idArr[1].compareTo("hyperextensions") == 0
                    || (idArr[1].compareTo("crunches") == 0
                        && type == Workout.WORKOUT_B)) {
                index = 6;
            } else if(idArr[1].compareTo("crunches") == 0) {
                index = 7;
            } else {
                return;
            }

            ((Button) view).setText(String.valueOf(exercises[index].updateSet(set - 1)));
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

    public void updateWorkout(View view) {
        dbAdapter.open();
        dbAdapter.updateWorkout(workout);
        dbAdapter.getWorkout(workout.getDate()).printWorkout();
        dbAdapter.close();
    }

    public void initViews() {

        Button button;
        int[] setArr;

        /* squats */
        setArr = exercises[0].getSets();
        button = (Button) findViewById(R.id.button_squats_1);
        button.setText(String.valueOf(setArr[0]));
        button = (Button) findViewById(R.id.button_squats_2);
        button.setText(String.valueOf(setArr[1]));
        button = (Button) findViewById(R.id.button_squats_3);
        button.setText(String.valueOf(setArr[2]));
        button = (Button) findViewById(R.id.button_squats_4);
        button.setText(String.valueOf(setArr[3]));
        button = (Button) findViewById(R.id.button_squats_5);
        button.setText(String.valueOf(setArr[4]));

        ((EditText) findViewById(R.id.edit_squats)).setText(String.valueOf(exercises[0].getWeight()));

        if(type == Workout.WORKOUT_A) {
        /* bench */
            setArr = exercises[1].getSets();
            button = (Button) findViewById(R.id.button_bench_1);
            button.setText(String.valueOf(setArr[0]));
            button = (Button) findViewById(R.id.button_bench_2);
            button.setText(String.valueOf(setArr[1]));
            button = (Button) findViewById(R.id.button_bench_3);
            button.setText(String.valueOf(setArr[2]));
            button = (Button) findViewById(R.id.button_bench_4);
            button.setText(String.valueOf(setArr[3]));
            button = (Button) findViewById(R.id.button_bench_5);
            button.setText(String.valueOf(setArr[4]));

            ((EditText) findViewById(R.id.edit_bench)).setText(String.valueOf(exercises[1].getWeight()));

        /* row */
            setArr = exercises[2].getSets();
            button = (Button) findViewById(R.id.button_row_1);
            button.setText(String.valueOf(setArr[0]));
            button = (Button) findViewById(R.id.button_row_2);
            button.setText(String.valueOf(setArr[1]));
            button = (Button) findViewById(R.id.button_row_3);
            button.setText(String.valueOf(setArr[2]));
            button = (Button) findViewById(R.id.button_row_4);
            button.setText(String.valueOf(setArr[3]));
            button = (Button) findViewById(R.id.button_row_5);
            button.setText(String.valueOf(setArr[4]));

            ((EditText) findViewById(R.id.edit_row)).setText(String.valueOf(exercises[2].getWeight()));

        /* shrugs */
            setArr = exercises[3].getSets();
            button = (Button) findViewById(R.id.button_shrugs_1);
            button.setText(String.valueOf(setArr[0]));
            button = (Button) findViewById(R.id.button_shrugs_2);
            button.setText(String.valueOf(setArr[1]));
            button = (Button) findViewById(R.id.button_shrugs_3);
            button.setText(String.valueOf(setArr[2]));

            ((EditText) findViewById(R.id.edit_shrugs)).setText(String.valueOf(exercises[3].getWeight()));

        /* tricep extensions */
            setArr = exercises[4].getSets();
            button = (Button) findViewById(R.id.button_tricep_1);
            button.setText(String.valueOf(setArr[0]));
            button = (Button) findViewById(R.id.button_tricep_2);
            button.setText(String.valueOf(setArr[1]));
            button = (Button) findViewById(R.id.button_tricep_3);
            button.setText(String.valueOf(setArr[2]));

            ((EditText) findViewById(R.id.edit_tricep)).setText(String.valueOf(exercises[4].getWeight()));

        /* incline curls */
            setArr = exercises[5].getSets();
            button = (Button) findViewById(R.id.button_incline_1);
            button.setText(String.valueOf(setArr[0]));
            button = (Button) findViewById(R.id.button_incline_2);
            button.setText(String.valueOf(setArr[1]));
            button = (Button) findViewById(R.id.button_incline_3);
            button.setText(String.valueOf(setArr[2]));

            ((EditText) findViewById(R.id.edit_incline)).setText(String.valueOf(exercises[5].getWeight()));

        /* hyperextensions */
            setArr = exercises[6].getSets();
            button = (Button) findViewById(R.id.button_hyperextensions_1);
            button.setText(String.valueOf(setArr[0]));
            button = (Button) findViewById(R.id.button_hyperextensions_2);
            button.setText(String.valueOf(setArr[1]));

            ((EditText) findViewById(R.id.edit_hyperextensions)).setText(String.valueOf(exercises[6].getWeight()));

        /* incline curls */
            setArr = exercises[7].getSets();
            button = (Button) findViewById(R.id.button_crunches_1);
            button.setText(String.valueOf(setArr[0]));
            button = (Button) findViewById(R.id.button_crunches_2);
            button.setText(String.valueOf(setArr[1]));
            button = (Button) findViewById(R.id.button_crunches_3);
            button.setText(String.valueOf(setArr[2]));

            ((EditText) findViewById(R.id.edit_crunches)).setText(String.valueOf(exercises[7].getWeight()));
        } else {
        /* deadlif */
            setArr = exercises[1].getSets();
            button = (Button) findViewById(R.id.button_deadlift_1);
            button.setText(String.valueOf(setArr[0]));

            ((EditText) findViewById(R.id.edit_deadlift)).setText(String.valueOf(exercises[1].getWeight()));

        /* standing */
            setArr = exercises[2].getSets();
            button = (Button) findViewById(R.id.button_standing_1);
            button.setText(String.valueOf(setArr[0]));
            button = (Button) findViewById(R.id.button_standing_2);
            button.setText(String.valueOf(setArr[1]));
            button = (Button) findViewById(R.id.button_standing_3);
            button.setText(String.valueOf(setArr[2]));
            button = (Button) findViewById(R.id.button_standing_4);
            button.setText(String.valueOf(setArr[3]));
            button = (Button) findViewById(R.id.button_standing_5);
            button.setText(String.valueOf(setArr[4]));

            ((EditText) findViewById(R.id.edit_standing)).setText(String.valueOf(exercises[2].getWeight()));

        /* bent */
            setArr = exercises[3].getSets();
            button = (Button) findViewById(R.id.button_bent_1);
            button.setText(String.valueOf(setArr[0]));
            button = (Button) findViewById(R.id.button_bent_2);
            button.setText(String.valueOf(setArr[1]));
            button = (Button) findViewById(R.id.button_bent_3);
            button.setText(String.valueOf(setArr[2]));
            button = (Button) findViewById(R.id.button_bent_4);
            button.setText(String.valueOf(setArr[3]));
            button = (Button) findViewById(R.id.button_bent_5);
            button.setText(String.valueOf(setArr[4]));

            ((EditText) findViewById(R.id.edit_bent)).setText(String.valueOf(exercises[3].getWeight()));

        /* close grip bench press*/
            setArr = exercises[4].getSets();
            button = (Button) findViewById(R.id.button_close_1);
            button.setText(String.valueOf(setArr[0]));
            button = (Button) findViewById(R.id.button_close_2);
            button.setText(String.valueOf(setArr[1]));
            button = (Button) findViewById(R.id.button_close_3);
            button.setText(String.valueOf(setArr[2]));

            ((EditText) findViewById(R.id.edit_close)).setText(String.valueOf(exercises[4].getWeight()));

        /* incline curls */
            setArr = exercises[5].getSets();
            button = (Button) findViewById(R.id.button_incline_1);
            button.setText(String.valueOf(setArr[0]));
            button = (Button) findViewById(R.id.button_incline_2);
            button.setText(String.valueOf(setArr[1]));
            button = (Button) findViewById(R.id.button_incline_3);
            button.setText(String.valueOf(setArr[2]));

            ((EditText) findViewById(R.id.edit_incline)).setText(String.valueOf(exercises[5].getWeight()));

        /* hyperextensions */
            setArr = exercises[6].getSets();
            button = (Button) findViewById(R.id.button_crunches_1);
            button.setText(String.valueOf(setArr[0]));
            button = (Button) findViewById(R.id.button_crunches_2);
            button.setText(String.valueOf(setArr[1]));
            button = (Button) findViewById(R.id.button_crunches_3);
            button.setText(String.valueOf(setArr[2]));

            ((EditText) findViewById(R.id.edit_crunches)).setText(String.valueOf(exercises[6].getWeight()));
        }
    }
}
