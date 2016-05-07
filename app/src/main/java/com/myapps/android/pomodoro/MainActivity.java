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

        TextView button1 = (TextView) findViewById(R.id.start_pom);
        TextView button2 = (TextView) findViewById(R.id.shrt_brk);
        TextView button3 = (TextView) findViewById(R.id.long_brk);
        Typeface face2 = Typeface.createFromAsset(getAssets(),"font3.otf");
        button1.setTypeface(face2);
        button2.setTypeface(face2);
        button3.setTypeface(face2);
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
