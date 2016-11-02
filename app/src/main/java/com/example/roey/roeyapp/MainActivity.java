package com.example.roey.roeyapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

/**
 * Main screen after splash screens ,
 * each button contain an animation , and timer that waits till the animation ends and then going to the next activity.
 *
 * Screen1- PAINT - custom view inside layout . paint circelrs via array of circels.
 *                  save - via external storage - images will add to folder inside the phone gallery .
 *                         save dialog appear ( diffrenet activity (manifest set for dialog) that has xml.
 *                  send via mail - set the view as bitmap , attach it to the mail and send it.
 *
 * Screen2 - COUNTER - counter example.
 *
 * Screen3 - Selecting from the list view - will open the proper wikipedia page for it via webview.
 *           - when the user clicks first it check for internet connection - if there is not connection - it will notify the user about it.
 *
 * Screen4 - ACCELEROMETER SENSOR   - first screen display the x y z values live.
 *                                    the other buttons - games to be implemnted .
 *
 * Screen5 - BroadcastReceiver example \ incoming call class with text view and button.
 *           + at the main screen - when call is arriving it will notify with toast about it and will open
 *           Screen 5 activity , it will take the incoming call number and set it as textview.
 *
 *
 *
 *
 *
 * project seems to be good at home laptop
 *
 */

// commit 02.11.2016 -2

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton iButton1 ;
    ImageButton iButton2 ;
    ImageButton iButton3 ;
    ImageButton iButton4 ;
    ImageButton iButton5 ;
    Animation animRotate ;
    Animation animFadeout;
    Animation zoomOut;
    Animation bounce;
    Animation translate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        iButton1 = (ImageButton) findViewById(R.id.imageButton1) ;
        iButton1.setOnClickListener(this);

        iButton2 = (ImageButton) findViewById(R.id.imageButton2) ;
        iButton2.setOnClickListener(this);

        iButton3 = (ImageButton) findViewById(R.id.imageButton3) ;
        iButton3.setOnClickListener(this);

        iButton4 = (ImageButton) findViewById(R.id.imageButton4) ;
        iButton4.setOnClickListener(this);

        iButton5 = (ImageButton) findViewById(R.id.imageButton5) ;
        iButton5.setOnClickListener(this);




        animRotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
        animFadeout = AnimationUtils.loadAnimation(this , R.anim.abc_fade_out);
        zoomOut = AnimationUtils.loadAnimation(this , R.anim.zoom_out);
        bounce = AnimationUtils.loadAnimation(this ,R.anim.anim_bounce);
        translate = AnimationUtils.loadAnimation(this ,R.anim.anim_translate);



    }

    @Override
    protected void onResume(){
    super.onResume();

        ComponentName component=new ComponentName(this, IncomingCall.class);
        getPackageManager()
                .setComponentEnabledSetting(component,
                        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                        PackageManager.DONT_KILL_APP);
    }

    @Override
    public void onPause(){
    super.onPause();

        ComponentName component=new ComponentName(this, IncomingCall.class);
        getPackageManager()
                .setComponentEnabledSetting(component,
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.imageButton1:


                v.startAnimation(animRotate);



                Thread timer = new Thread() {
                    public void run(){
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            Intent openMainActivity = new Intent("com.example.roey.roeyapp.SCREEN1");
                            startActivity(openMainActivity);
                        }
                    }
                };
                timer.start();
         break;





            case R.id.imageButton2:
               v.startAnimation(animFadeout);


                Thread timer2 = new Thread() {
                    public void run(){
                        try {
                            sleep(700);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            Intent openMainActivity = new Intent("com.example.roey.roeyapp.SCREEN2");
                            startActivity(openMainActivity);
                        }
                    }
                };
                timer2.start();


                break;

            case R.id.imageButton3:
                v.startAnimation(zoomOut);


                Thread timer3 = new Thread() {
                    public void run(){
                        try {
                            sleep(700);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            Intent openMainActivity = new Intent("com.example.roey.roeyapp.SCREEN3");
                            startActivity(openMainActivity);
                        }
                    }
                };
                timer3.start();


                break;

            case R.id.imageButton4:
                v.startAnimation(bounce);


                Thread timer4 = new Thread() {
                    public void run(){
                        try {
                            sleep(700);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            Intent openMainActivity = new Intent("com.example.roey.roeyapp.SCREEN4");
                            startActivity(openMainActivity);
                        }
                    }
                };
                timer4.start();


                break;


            case R.id.imageButton5:
                v.startAnimation(translate);


                Thread timer5 = new Thread() {
                    public void run(){
                        try {
                            sleep(700);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            Intent openMainActivity = new Intent("com.example.roey.roeyapp.SCREEN5");
                            startActivity(openMainActivity);
                        }
                    }
                };
                timer5.start();


                break;



        }

    }













    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }






}



