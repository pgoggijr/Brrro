package com.sounds_good.brrro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MenuActivity extends AppCompatActivity {

    public void checkHistory(View view) {
        System.out.println("checking history");
        Intent intent = new Intent(MenuActivity.this, HistoryActivity.class);
        intent.putExtra("type",1);
        MenuActivity.this.startActivity(intent);
    }

    public void logWorkout(View view) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Intent intent = new Intent(MenuActivity.this, WorkoutAActivity.class);
        System.out.println(sdf.format(new Date()));
        intent.putExtra("date",sdf.format(new Date()));
        MenuActivity.this.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Workout test = new Workout(0,"20150501");
        Exercise[] exercises = test.getExercises();
        exercises[0].updateSet(0);
        exercises[0].updateSet(0);
        exercises[0].updateSet(1);
        test.printWorkout();
        WorkoutDatabaseAdapter adapter = new WorkoutDatabaseAdapter(this);
        adapter.open();
        adapter.insertWorkout(test);
        System.out.println("first item in db: " + adapter.getWorkouts()[0]);
        //adapter.getWorkout(adapter.getWorkouts()[0]).printWorkout();
        adapter.close();
        System.out.println("Adapter closed");
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
        System.out.println("BLAHHH");
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
