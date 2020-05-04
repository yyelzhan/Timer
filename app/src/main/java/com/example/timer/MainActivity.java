package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView Timer;
    private int seconds = 0;
    private boolean isRunning = false;
    private boolean wasRunning =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer = findViewById(R.id.Timer);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("isRunning");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }runTimer();
    }






    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds",seconds);
        outState.putBoolean("isRunning",isRunning);
        outState.putBoolean("wasRunning",wasRunning);
    }

    public void onStart(View view) {
        isRunning =true;
    }

    public void onStop(View view) {
        isRunning= false;
    }

    public void onRestart(View view) {
        isRunning = false;
        seconds = 0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isRunning = false;
        wasRunning = isRunning;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning = wasRunning;
    }


    private void runTimer(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
             int hourse = seconds/3600;
             int minutes =(seconds%3600)/60;
             int sec = seconds % 60;
             String timer = String.format(Locale.getDefault(),"%d:%02d:%02d",hourse,minutes,sec);
             Timer.setText(timer);
             if (isRunning){
                 seconds++;
             }
             handler.postDelayed(this,1000);
            }


        });

    }

}
