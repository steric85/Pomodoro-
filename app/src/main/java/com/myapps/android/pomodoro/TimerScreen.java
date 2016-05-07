package com.myapps.android.pomodoro;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class TimerScreen extends AppCompatActivity {

    int time;
    private TextView time_left;
    private ImageButton button1;
    private ImageButton button2;
    private  MediaPlayer alarm_song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_screen);

        Typeface face = Typeface.createFromAsset(getAssets(),"font2.ttf");
        time_left= (TextView) findViewById(R.id.time_left);
        time_left.setTypeface(face);

        button1=(ImageButton)findViewById(R.id.button1);
        button2=(ImageButton)findViewById(R.id.button2);
        alarm_song = MediaPlayer.create(this,R.raw.winter_wonderland);

        Intent i = getIntent();
        time = i.getIntExtra("TIME", 0);

        time_left.setText(time + ":00");

    }

    public void startTimer(View view) {

        String duration = (String) time_left.getText();
        String parts[] = duration.split(":");
        int minutes = Integer.parseInt(parts[0]);

        new CountDownTimer(minutes * 60 * 1000, 1000) {

            public void onTick(long millisUntilFinished) {

                time_left.setText(String.format("%d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                time_left.setText("done!");
            }
        }.start();
    }

}

