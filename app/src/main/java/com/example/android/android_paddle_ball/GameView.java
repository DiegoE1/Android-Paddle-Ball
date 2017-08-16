package com.example.android.android_paddle_ball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by diegoespinosa on 8/9/17.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    //private Paint rect;
    private Paint circ;
    private final int radius = 25;
    private int startingX;
    private int startingY;
    private int randX;
    private int randY;

//    private long lastUpdate;
//    private long currentTime;
//    private final long minimumUpdateTime = 3000;

    private GameThread mThread;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //init();

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);

        mThread = new GameThread(holder, context, new Handler());

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
//        currentTime = System.currentTimeMillis();
//        if(currentTime - lastUpdate > minimumUpdateTime){
//            Log.d("time", String.valueOf(currentTime));
//            lastUpdate = currentTime;
//
//        }
//        else{
//            Log.d("not updating", String.valueOf(currentTime));
//        }

//      left, top, right, bottom
        //canvas.drawRect(250, 700, 550, 750, rect);
//        x, y,             360, 675
//        getRectWidth();
//        canvas.drawCircle(startingX, startingY, radius, circ);
//        tempMoveBall(canvas);
//        canvas.translate((float)randX, (float)randY);

    }

    private void getRectWidth(){
        int width = 340;
        startingX = width;
        startingY = 675;
    }

//    private void tempMoveBall(Canvas canvas){
//        Random r = new Random();
//        randX = (r.nextInt(400) + 10);
//        randY = (r.nextInt(600) + 10);
//        Log.d("Random numbers", String.valueOf(randX + " " + randY));
//    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent msg) {
//        //return mThread.getGameState().keyPressed(keyCode, msg);
//    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mThread.stop();
    }
}
