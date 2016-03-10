package com.example.roey.roeyapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by roey on 03/03/2016.
 */
public class Screen4 extends Activity implements SensorEventListener, View.OnClickListener {

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;

    TextView tvXaxis ;
    TextView tvYaxis ;
    TextView tvZaxis ;

    Button bStartGame1;
    Button bStartGame2;
    Button bStartGame3;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen4);

        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        tvXaxis = (TextView) findViewById(R.id.tvXaxis);
        tvYaxis = (TextView) findViewById(R.id.tvYaxis);
        tvZaxis = (TextView) findViewById(R.id.tvZaxis);

        bStartGame1 = (Button) findViewById(R.id.bStartGame1);
        bStartGame2 = (Button) findViewById(R.id.bStartGame2);
        bStartGame3 = (Button) findViewById(R.id.bStartGame3);

        bStartGame1.setOnClickListener(this);
        bStartGame2.setOnClickListener(this);
        bStartGame3.setOnClickListener(this);



    }








    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];


            //  We store the system's current time (in milliseconds) store it in curTime
            // and check whether more than 500 milliseconds have passed since the last time onSensorChanged was invoked.
            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 500) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

            // setting the x,y,z float values as a string
            String xAxisText=Float.toString(x);
            String yAxisText=Float.toString(y);
            String zAxisText=Float.toString(z);

            // display the strings
            tvXaxis.setText("x axis :" +xAxisText);
            tvYaxis.setText("y axis :" +yAxisText);
            tvZaxis.setText("z axis :" +zAxisText);

            }


        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }













    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onClick(View v) {

        switch(v.getId()) {

            case R.id.bStartGame1 :


                Intent openGameActivity1 = new Intent("com.example.roey.roeyapp.SCREEN41");
                startActivity(openGameActivity1);



                break;

            case R.id.bStartGame2 :


                Intent openGameActivity2 = new Intent("com.example.roey.roeyapp.SCREEN42");
                startActivity(openGameActivity2);



                break;

            case R.id.bStartGame3 :


                Intent openGameActivity3 = new Intent("com.example.roey.roeyapp.SCREEN43");
                startActivity(openGameActivity3);



                break;
        }
    }
}
