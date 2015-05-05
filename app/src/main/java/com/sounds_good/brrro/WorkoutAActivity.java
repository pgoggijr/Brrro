package com.sounds_good.brrro;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

public class WorkoutAActivity extends ActionBarActivity {

    private Workout workout;
    private Exercise[] exercises;
    private String date;
    private WorkoutDatabaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        dbAdapter = new WorkoutDatabaseAdapter(this);
        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        if(date == null) {
            date = sdf.format(new Date());
        }
        //System.out.println("date (workout activity): " + date);
        dbAdapter.open();
        workout = dbAdapter.getWorkout(date,Workout.WORKOUT_A);
        exercises = workout.getExercises();
        dbAdapter.close();
        setContentView(R.layout.activity_workout_a);
        initViews();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
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

        /* inline curls */
        setArr = exercises[5].getSets();
        button = (Button) findViewById(R.id.button_inline_1);
        button.setText(String.valueOf(setArr[0]));
        button = (Button) findViewById(R.id.button_inline_2);
        button.setText(String.valueOf(setArr[1]));
        button = (Button) findViewById(R.id.button_inline_3);
        button.setText(String.valueOf(setArr[2]));

        ((EditText) findViewById(R.id.edit_inline)).setText(String.valueOf(exercises[5].getWeight()));

        /* hyperextensions */
        setArr = exercises[6].getSets();
        button = (Button) findViewById(R.id.button_hyperextensions_1);
        button.setText(String.valueOf(setArr[0]));
        button = (Button) findViewById(R.id.button_hyperextensions_2);
        button.setText(String.valueOf(setArr[1]));

        ((EditText) findViewById(R.id.edit_hyperextensions)).setText(String.valueOf(exercises[6].getWeight()));

        /* inline curls */
        setArr = exercises[7].getSets();
        button = (Button) findViewById(R.id.button_crunches_1);
        button.setText(String.valueOf(setArr[0]));
        button = (Button) findViewById(R.id.button_crunches_2);
        button.setText(String.valueOf(setArr[1]));
        button = (Button) findViewById(R.id.button_crunches_3);
        button.setText(String.valueOf(setArr[2]));

        ((EditText) findViewById(R.id.edit_crunches)).setText(String.valueOf(exercises[7].getWeight()));
    }
}
