package com.example.roey.roeyapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class GraphicsView extends View implements View.OnTouchListener {

    int x = 0;
    int y = 0;
    int r = 255;
    int g = 255;
    int b = 255;
    int radius = 50;
    Paint paint;
    MediaPlayer player = new MediaPlayer();

    private static final String TAG = "Screen1";
    int counter = 0;
    Paint paint2;


    public GraphicsView(Context context, AttributeSet attrs) {
        super(context, attrs);
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
            // TODO
        }
    }



    // public static ArrayList<int[]> circles = new ArrayList<int[]>();
    public ArrayList<Circle> circlesArray = new ArrayList<Circle>();


    @Override
    protected void onDraw(Canvas canvas) {
        //   if(drawEverything) {
        int screenWidth = canvas.getWidth();
        int screenHeight = canvas.getHeight();
        // Option 1 - > using array of int
            /*
            int[] currentCircle = new int[]{r, g, b, x, y, radius};
            circles.add(currentCircle);
            for(int[] circle:circles){
                paint.setARGB(150, circle[0], circle[1], circle[2]);
                canvas.drawCircle(circle[3], circle[4], circle[5], paint);
            }
            */

        // Option 2 -> same thing just more clear
            /*
            for(int i =0; i<circles.size();i++ ){
                int[] circle = circles.get(i);
                paint.setARGB(150, circle[0], circle[1], circle[2]);
                canvas.drawCircle(circle[3], circle[4], circle[5], paint);
            }
            */

        // Option 3 -> same thing using Circle class
        Circle currentCircle = new Circle(r, g, b, x, y, radius);
        circlesArray.add(currentCircle);
        counter++;
        Log.v(TAG, "r=" + r + "  g=" + g + "  b=" + b + "  x=" + x + "  y=" + y + "  radius=" + radius + "  number of drawn circels till far: " + counter);


        for (Circle circle : circlesArray) {
            circle.drawCircle(canvas);

        }

        canvas.drawText("Total Circles:  " + counter, 0, screenHeight, paint2);

        //   }else{
        //       canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        //   }


        // clear the canvas Canvas.drawColor(Color.BLACK)
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
        }

        public void drawCircle(Canvas canvas) {
            paint.setARGB(150, r, g, b);
            canvas.drawCircle(x, y, radius, paint);
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
            /*
            try {
                Thread.sleep(20);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
*/
        x = (int) event.getX();
        y = (int) event.getY();
        r = (int) (Math.random() * 255);
        g = (int) (Math.random() * 255);
        b = (int) (Math.random() * 255);
        invalidate();


        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                player.setLooping(true);
                player.start();


            case MotionEvent.ACTION_MOVE:


                break;

            case MotionEvent.ACTION_UP:
                player.pause();
                break;
        }


        return true;


    }
}
