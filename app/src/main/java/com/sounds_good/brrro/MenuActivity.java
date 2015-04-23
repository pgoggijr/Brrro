package com.sounds_good.brrro;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;


public class MenuActivity extends ActionBarActivity {

    public void checkHistory(View view) {
        System.out.println("checking history");
        Intent intent = new Intent(MenuActivity.this, HistoryActivity.class);
        MenuActivity.this.startActivity(intent);
    }

    public void logWorkout(View view) {
        System.out.println("logging workout");
        Intent intent = new Intent(MenuActivity.this, WorkoutActivity.class);
        MenuActivity.this.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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
