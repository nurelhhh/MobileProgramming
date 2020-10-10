package com.example.nurelstopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;
    private TextView timeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("timer_seconds");
            running = savedInstanceState.getBoolean("timer_running");
            wasRunning = savedInstanceState.getBoolean("timer_was_running");
        }

        timeText = findViewById(R.id.time_text);
        runTimer();
    }

    private void runTimer() {
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hour = seconds / 3600;
                int minute = (seconds % 3600) / 60;
                int sec = seconds % 60;
                String time = String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, minute, sec);

                if (running) {
                    timeText.setText(time);
                    seconds++;
                }

                handler.postDelayed(this, 1000);
            }
        });
    }

    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickReset(View view) {
        running = false;
        seconds = 0;
        timeText.setText(null);
        timeText.setHint(getString(R.string.time_text_hint));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("timer_seconds", seconds);
        outState.putBoolean("timer_running", running);
        outState.putBoolean("timer_was_running", wasRunning);
    }

    @Override
    protected void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        running = wasRunning;
    }
}