package com.sounds_good.brrro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MenuActivity extends AppCompatActivity {

    SharedPreferences prefs = null;

    public void checkHistory(View view) {
        Intent intent = new Intent(MenuActivity.this, HistoryActivity.class);
        MenuActivity.this.startActivity(intent);
    }

    public void logWorkout(View view) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
        Intent intent = new Intent(MenuActivity.this, WorkoutActivity.class);
        intent.putExtra("date",sdf.format(new Date()));
        MenuActivity.this.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("com.sounds_good.brrro", MODE_PRIVATE);
        setContentView(R.layout.activity_menu);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstRun", true)) {
            //insert dummy workouts
            Workout workoutDummy1 = new Workout(Workout.WORKOUT_A, "20150429");
            Workout workoutDummy2 = new Workout(Workout.WORKOUT_B, "20150501");
            Workout workoutDummy3 = new Workout(Workout.WORKOUT_A, "20150503");
            WorkoutDatabaseAdapter dbAdapter = new WorkoutDatabaseAdapter(this);
            dbAdapter.open();
            dbAdapter.insertWorkout(workoutDummy1);
            dbAdapter.insertWorkout(workoutDummy2);
            dbAdapter.insertWorkout(workoutDummy3);
            dbAdapter.close();

            prefs.edit().putBoolean("firstRun", false).apply();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
