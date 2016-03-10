package com.example.roey.roeyapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;
import android.widget.FrameLayout;

public class Screen43 extends Activity  implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;

    private float xp=100;
    private float yp=100;

    FrameLayout main;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen43);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),mSensorManager.SENSOR_DELAY_NORMAL);
        main = (FrameLayout) findViewById(R.id.main_view);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        // Take the screen size
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int XMAX = size.x;
        int YMAX = size.y;


        final float THR = (float) 1.5;
        final int ballRadius = 80;
        final float ballSize = (float)ballRadius*((float)2);



        if(yp < (YMAX - ballSize - 20))
            if(event.values[1]>(float)THR)
            {
                yp = yp +2*event.values[1];
                main.addView(new BackGround(this));
                main.addView(new Ball(this,xp,yp, ballRadius));
            }

        if(yp > ballSize -10)
            if(event.values[1]<(float)-THR)
            {
                yp = yp -2*Math.abs(event.values[1]);
                main.addView(new BackGround(this));
                main.addView(new Ball(this,xp,yp, ballRadius));
            }

        if(xp > ballSize)
            if(event.values[0]>(float)THR)
            {
                xp = xp -2*Math.abs(event.values[0]);
                main.addView(new BackGround(this));
                main.addView(new Ball(this,xp,yp, ballRadius));
            }

        if(xp < (XMAX - ballSize))
            if(event.values[0]<(float)-THR)
            {
                xp = xp +2*Math.abs(event.values[0]);
                main.addView(new BackGround(this));
                main.addView(new Ball(this,xp,yp, ballRadius));
            }
    }
}













