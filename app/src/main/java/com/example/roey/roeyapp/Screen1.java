package com.example.roey.roeyapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;



public class Screen1 extends Activity {

    public static boolean drawEverything = true;
    GraphicsView graphics ;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
  //      setContentView(new GraphicsView(this));
        setContentView(R.layout.screen1);
        TextView tv1 = (TextView) findViewById(R.id.text);
        graphics = (GraphicsView) findViewById(R.id.snake);

        drawEverything = true;

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        drawEverything = false;
    }
}
