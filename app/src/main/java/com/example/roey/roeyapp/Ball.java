package com.example.roey.roeyapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Ball extends View {

    private final float x;
    private final float y;
    private final int r;
    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Ball(Context context, float x, float y, int r) {
        super(context);

        mPaint.setColor(0xFFFF0000);
        this.x = x;
        this.y = y;
        this.r = r;

    }

    protected void onDraw(Canvas canvas)
    {
        int screenWidth = canvas.getWidth();
        int screenHeight = canvas.getHeight();

        super.onDraw(canvas);
        canvas.drawCircle(screenWidth / 2 + x, screenHeight / 2 + y, r, mPaint);

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(75);

        Paint paint2 = new Paint();
        paint2.setColor(Color.BLACK);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setTextSize(75);

        canvas.drawText("Screen size:  " + screenWidth + "*" + screenHeight,0,screenHeight , paint);
        canvas.drawText("ballX:  "+ screenWidth / 2 + x, 0, screenHeight/10, paint2);
        canvas.drawText("ballY:  "+ screenHeight / 2 + y, 0, screenHeight/7, paint2);
    }
}




class BackGround extends View {
    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public BackGround(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        mPaint.setColor(0xFF66FF33);
    }
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawPaint(mPaint);


    }
}