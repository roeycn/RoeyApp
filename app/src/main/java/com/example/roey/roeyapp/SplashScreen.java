package com.example.roey.roeyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by roey on 07/02/2016.
 */
public class SplashScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);




        Thread timer = new Thread() {
            public void run(){
                try {
    //                sleep(3000);
                    sleep(500);

                } catch (InterruptedException e){
                    e.printStackTrace();

                }finally {
                    Intent openMainActivity = new Intent("com.example.roey.roeyapp.INTROSCREEN");
                    startActivity(openMainActivity);
                }
            }
        };
        timer.start();




    }
}
