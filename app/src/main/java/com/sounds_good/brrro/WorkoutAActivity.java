package com.sounds_good.brrro;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Button;

public class WorkoutAActivity extends ActionBarActivity {

    private Workout workout;
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
        workout = dbAdapter.getWorkout(date,0);

        setContentView(R.layout.activity_workout_a);
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
}
