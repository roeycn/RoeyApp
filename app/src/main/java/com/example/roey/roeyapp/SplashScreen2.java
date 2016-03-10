package com.example.roey.roeyapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;


/**
 * Created by roey on 07/02/2016.
 */
public class SplashScreen2 extends Activity  {

    Intro ourIntro ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ourIntro = new Intro(this);
        setContentView(ourIntro);

        Thread timer = new Thread() {
            public void run(){
                try {
  //                  sleep(4000);
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent openMainActivity = new Intent("com.example.roey.roeyapp.FIRSTSCREEN");
                    startActivity(openMainActivity);
                }
            }
        };
        timer.start();

    }
}

/**
 * Android Application Development Tutorial - 63 - Using a Constructor to pass Context
 */

 class Intro extends View {

    Bitmap missile;
    float changingY =0;
    float changingX =0;
    Typeface font;
    MediaPlayer mpMissile ;
    Random rnd = new Random();



    public Intro(Context context) {
        super(context);

        missile = BitmapFactory.decodeResource(getResources(), R.drawable.missile3);
        font = Typeface.createFromAsset(context.getAssets(), "gunit.TTF");

        mpMissile = MediaPlayer.create(context, R.raw.missile);
        mpMissile.start();

    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //set Background
        canvas.drawColor(Color.WHITE);
        int screenWidth = canvas.getWidth();
        int screenHeight = canvas.getHeight();
        //Text using (gunit.ttf file)
        Paint textPaint = new Paint();
        textPaint.setARGB(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(200);
        //keep the current default size but scale it according to the screen density,
       // textPaint.setTextSize(textPaint.getTextSize() *  youractivity. getApplicationContext(this).getResources().getDisplayMetrics().density);
        textPaint.setTypeface(font);
        canvas.drawText("--Roey Games--", screenWidth / 2, 200, textPaint);


        //Missile animation
        canvas.drawBitmap(missile,changingX, (screenHeight)/2, null);
        if (changingX < screenWidth){
            changingX += 15;
        }

        int screenWidth1 = (int) ((int) screenWidth*0.8);
        int screenWidth2 = (int) ((int) screenWidth*0.6);
        int screenHeightbottomblue = (int) ((int) screenHeight*0.5);
        int screenHeighttopblue = (int) ((int) screenHeight*0.45);
        int screenHeightbottomgreen = (int) ((int) screenHeight*0.45);
        int screenHeighttopgreen = (int) ((int) screenHeight*0.40);
        //blue Rectangle
        Rect blueRect = new Rect();
        blueRect.set(0, screenHeighttopblue, screenWidth, screenHeightbottomblue);
        Paint ourBlue = new Paint();
        ourBlue.setColor(Color.BLUE);
        canvas.drawRect(blueRect, ourBlue);

        //green Rectangle
        Rect greenRect2 = new Rect();
        greenRect2.set(0, screenHeighttopgreen, screenWidth, screenHeightbottomgreen);
        Paint ourBlue2 = new Paint();
        ourBlue2.setColor(Color.GREEN);
        canvas.drawRect(greenRect2 , ourBlue2);

        invalidate();




        }


    }

