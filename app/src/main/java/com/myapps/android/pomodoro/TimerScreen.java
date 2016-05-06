package com.myapps.android.pomodoro;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class TimerScreen extends AppCompatActivity {

    int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_screen);

        Intent i = getIntent();
        time = i.getIntExtra("TIME", 0);
        startTimer();
    }

    private void startTimer() {
        final TextView time_left = (TextView) findViewById(R.id.time_left);
        time_left.setText(time + ":00");
        String duration = (String) time_left.getText();
        String parts[] = duration.split(":");
        int minutes = Integer.parseInt(parts[0]);

        new CountDownTimer(minutes * 60 * 1000, 1000) {

            public void onTick(long millisUntilFinished) {

                time_left.setText(String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                time_left.setText("done!");
            }
        }.start();
    }

    public void startCountDown(View view){
        startTimer();
    }
}


