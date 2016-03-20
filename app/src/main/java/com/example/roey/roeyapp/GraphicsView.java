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
import android.widget.Toast;

import java.util.ArrayList;

public class GraphicsView extends View implements View.OnTouchListener {

    int x = 0;
    int y = 0;
    int r = 255;
    int g = 0;
    int b = 0;
    int radius = 50;
    Paint selectedPaint;
    MediaPlayer player = new MediaPlayer();

    boolean drawEnabled = false;



    private static final String TAG = "Screen1";
    int counter = 0;
    Paint paintText;

    // select random colors
    int random = 0;
    // clean the canvas
    int clean = 0;


    public GraphicsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        selectedPaint = new Paint();
        selectedPaint.setAntiAlias(true);
        selectedPaint.setARGB(150, r, g, b);
        setFocusable(true);
        this.setOnTouchListener(this);



        paintText = new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTextSize(80);


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
        if (drawEnabled) {
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

            canvas.drawText("Total Circles:  " + counter, 0, screenHeight, paintText);


            //   }else{
            //       canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            //   }


            // clear the canvas Canvas.drawColor(Color.BLACK)
        }else {
  //          Toast toast = Toast.makeText(getContext(),"please select a color", Toast.LENGTH_SHORT );
  //          toast.show();
        }

        if (clean != 0) {
            // clear the canvas
            canvas.drawColor(Color.WHITE);
            circlesArray.clear();
            counter = 1;
        }

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
            selectedPaint.setARGB(150, r, g, b);
            canvas.drawCircle(x, y, radius, selectedPaint);
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

        if (random == 1) {
               r = (int) (Math.random() * 255);
               g = (int) (Math.random() * 255);
               b = (int) (Math.random() * 255);
        }
        invalidate();

        if (drawEnabled) {
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
        }

        return true;


    }
}
