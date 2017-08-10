package com.example.android.android_paddle_ball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by diegoespinosa on 8/9/17.
 */

public class GameView extends View{
    //private Paint rect;
    private Paint circ;
    private final int radius = 25;
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        //setWillNotDraw(false);

    }

    private void init() {
        //rect = new Paint();
        circ = new Paint();

        //rect.setStyle(Paint.Style.FILL);
        circ.setStyle(Paint.Style.FILL);

        //rect.setColor(Color.BLACK);
        circ.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//left, top, right, bottom
        //canvas.drawRect(250, 700, 550, 750, rect);
//        x, y,
        canvas.drawCircle(360, 675, radius, circ);

    }

    private void launchGame(){
        //to do
        //invisible button to press with text asking to press
        //send ball off to collide with walls
    }
}
