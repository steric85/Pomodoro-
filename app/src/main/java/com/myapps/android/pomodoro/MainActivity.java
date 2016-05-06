package com.myapps.android.pomodoro;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface face = Typeface.createFromAsset(getAssets(),"font1.ttf");
        TextView heading = (TextView) findViewById(R.id.app_name);
        heading.setTypeface(face);
    }

    public void startPomodoro(View view){
        Intent i = new Intent(MainActivity.this,TimerScreen.class);
        i.putExtra("TIME",25);
        startActivity(i);
    }

    public void startShortBreak(View view){
        Intent i = new Intent(MainActivity.this,TimerScreen.class);
        i.putExtra("TIME",5);
        startActivity(i);
    }

    public void startLongBreak(View view){
        Intent i = new Intent(MainActivity.this,TimerScreen.class);
        i.putExtra("TIME",15);
        startActivity(i);
    }
}
