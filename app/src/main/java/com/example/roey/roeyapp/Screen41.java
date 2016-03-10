package com.example.roey.roeyapp;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;



public class Screen41 extends Activity implements SensorEventListener {

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    long lastUpdate = 0;
    public  float xAcc ;
    public  float yAcc ;
    public  float zAcc ;


    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(new GraphicsView(this));
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);




    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           xAcc = event.values[0];
           yAcc = event.values[1];
           zAcc = event.values[2];

         //   xAcc = (int) Math.pow(event.values[1], 2);
         //   yAcc = (int) Math.pow(event.values[2], 2);




        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public class GraphicsView extends View implements View.OnTouchListener {



        int x = 0;
        int y = 0;
        int r = (int) (Math.random() * 255);
        int g = (int) (Math.random() * 255);
        int b = (int) (Math.random() * 255);
        int radius = 50;
        Paint paint;
        MediaPlayer player = new MediaPlayer();

        private static final String TAG = "Screen1";
        int counter = 0;
        Paint paint2;



        public GraphicsView(Context context) {
            super(context);
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setARGB(150, r, g, b);
            setFocusable(true);
            this.setOnTouchListener(this);

            paint2 = new Paint();
            paint2.setColor(Color.BLACK);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setTextSize(150);




            try {
                player = MediaPlayer.create(context, R.raw.horse);
                player.prepare();
            } catch (Exception e) {

            }



        }

        public ArrayList<Circle> circlesArray = new ArrayList<Circle>();

        @Override
        protected void onDraw(Canvas canvas) {

            int screenWidth = canvas.getWidth();
            int screenHeight = canvas.getHeight();

            int xAcc2 = (int)xAcc * 100 ;
            int yAcc2 = (int)yAcc * 100 ;







            // Option 3 -> same thing using Circle class
            Circle currentCircle = new Circle(r, g, b, screenWidth/2 + (int)xAcc2 , screenHeight/2 +(int)yAcc2, radius);
            circlesArray.add(currentCircle);
            counter++;


            Log.v(TAG, "r=" + r + "  g=" + g + "  b=" + b + "  x=" + x + "  y=" + y + "  radius=" + radius + "  number of drawn circels till far: " + counter);


            for (Circle circle : circlesArray) {
                circle.drawCircle(canvas);

            }

            canvas.drawText("Total Circles:  " + counter, 0, screenHeight, paint2);

            Paint myPaint = new Paint();
            myPaint.setColor(Color.rgb(0, 0, 0));
            myPaint.setStrokeWidth(10);
            canvas.drawRect(100, 100, 200, 200, myPaint);


        }


        private class Circle {
            int r, g, b, x, y, radius;

            public Circle(int r, int g, int b, int x, int y, int radius) {
                this.r = r;
                this.g = g;
                this.b = b;
                this.x = x;
                this.y = y;
                this.radius = radius;



                invalidate(); //will trigger the onDraw


            }

            public void drawCircle(Canvas canvas) {
                paint.setARGB(150, r, g, b);
                canvas.drawCircle(x, y, radius, paint);
            }
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    }
}





