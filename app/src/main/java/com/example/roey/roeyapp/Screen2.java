package com.example.roey.roeyapp;


 import android.app.Activity;
 import android.os.Bundle;
 import android.os.Handler;
 import android.os.SystemClock;
 import android.view.View;
 import android.widget.Button;
 import android.widget.ProgressBar;
 import android.widget.TextView;

public class Screen2 extends Activity {

    private Button startButton;
    private Button pauseButton;
    private TextView timerValue;
    private TextView userValue;
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;

    ProgressBar progressBar;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen2);

        timerValue = (TextView) findViewById(R.id.timerValue);
        userValue = (TextView) findViewById(R.id.userValue);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        pauseButton = (Button) findViewById(R.id.pauseButton);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);
                progressBar.setVisibility(View.INVISIBLE);

            }
        });




    }


    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);



           // if (secs==0){
              userValue.setText("" +String.format("%02d", secs));
        //    }

            timerValue.setText("" + mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds));




            customHandler.postDelayed(this, 0);
        }
    };
}
