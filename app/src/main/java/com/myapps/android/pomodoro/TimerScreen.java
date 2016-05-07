package com.myapps.android.pomodoro;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class TimerScreen extends AppCompatActivity {

    private int time, count, flag,stop;
    private long totalTime;
    private TextView time_left;
    private ImageButton button1;
    private ImageButton button2;
    private MediaPlayer alarm_song;
    private CountDownTimer cdTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_screen);

        count = 0;
        flag = 0;
        stop=0;

        Typeface face = Typeface.createFromAsset(getAssets(), "font2.ttf");
        time_left = (TextView) findViewById(R.id.time_left);
        time_left.setTypeface(face);

        button1 = (ImageButton) findViewById(R.id.button1);
        button2 = (ImageButton) findViewById(R.id.button2);

        Intent i = getIntent();
        time = i.getIntExtra("TIME", 0);

        time_left.setText(time + ":00");
        totalTime = time * 60 * 1000;
    }

    public void startTimer(View view) {

        if (flag == 1) {

            flag--;
            pauseTimer();
            return;
        }

        flag++;
        button1.setImageResource(R.drawable.pause);
        cdTimer = new CountDownTimer(totalTime, 1000) {

            public void onTick(long millisUntilFinished) {

                totalTime = millisUntilFinished;
                time_left.setText(String.format("%d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                time_left.setText("0:00");
                count++;
                switch (count) {
                    case 1:
                        ((ImageView) findViewById(R.id.tomato1)).setImageResource(R.drawable.tomato);
                        break;
                    case 2:
                        ((ImageView) findViewById(R.id.tomato2)).setImageResource(R.drawable.tomato);
                        break;
                    case 3:
                        ((ImageView) findViewById(R.id.tomato3)).setImageResource(R.drawable.tomato);
                        break;
                    case 4:
                        ((ImageView) findViewById(R.id.tomato4)).setImageResource(R.drawable.tomato);
                        break;
                    default:
                        ((ImageView) findViewById(R.id.tomato2)).setImageResource(R.drawable.tomatob);
                        ((ImageView) findViewById(R.id.tomato3)).setImageResource(R.drawable.tomatob);
                        ((ImageView) findViewById(R.id.tomato4)).setImageResource(R.drawable.tomatob);
                        count = 1;
                }
                button1.setImageResource(R.drawable.play);
                button2.setImageResource(R.drawable.alarm_off);
                alarm_song = MediaPlayer.create(TimerScreen.this, R.raw.winter_wonderland);
                alarm_song.start();
                stop++;
                flag--;
                totalTime=time*60*1000;
            }
        }.start();
    }

    private void pauseTimer() {
        button1.setImageResource(R.drawable.play);
        cdTimer.cancel();
    }

    public void stopTimer(View view){

        time_left.setText(time+":00");

        if(stop==1){
            stop--;
            alarm_song.release();
            button2.setImageResource(R.drawable.stop);
            return;
        }

        button1.setImageResource(R.drawable.play);
        if(flag!=0){
            flag--;
            cdTimer.cancel();
        }
        totalTime=time*60*1000;

    }
}

