package com.example.android.android_paddle_ball;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by diegoespinosa on 8/16/17.
 */

public class GameState {
    //screen
    final int mScreenWidth = 320;
    final int mScreenHeight= 420;

    //ball
    final int mBallSize = 20;
    int mBallX = 350;
    int mBallY = 400;
    int mBallVelocityX = 0;
    int mBallVelocityY = 0;

    //bat
    final int mBatLength = 75;
    final int mBatHeight = 20;
    int mBottomBatX =  325; //(mScreenWidth/2) - (mBatLength/2);
    final int mBottomBatY = 700;
    final int mBatSpeed = 3;

    private long lastUpdate;
    private long currentTime;
    private final long minimumUpdateTime = 100;
    private int xMovement = 0;

    public GameState(){

    }

    public void update(){
        mBallX += mBallVelocityX;
        mBallY += mBallVelocityY;

        if(mBallY > mScreenHeight || mBallY < 0){
            Log.e("GameOver", mBallX + " " + mBallY);
            mBallX = 325; mBallY =690;
        }
        if(mBallX > mScreenWidth || mBallX < 0){
            mBallVelocityX *=1;
        }

        if(mBallX > mBottomBatX && mBallX < mBottomBatX + mBatLength && mBallY > mBottomBatY){
            mBallVelocityY *=1;
        }
    }

//    public boolean keyPressed(int keyCode, KeyEvent msg){
//
//        if(keyCode = )
//
//        return true;
//    }

    public void draw(Canvas canvas, Paint paint){
        canvas.drawRGB(20,20,20);

        paint.setARGB(200,0,200,0);

        canvas.drawRect(new Rect(mBallX /* xMovement*/, mBallY, mBallX + mBallSize /*+ xMovement*/, mBallY + mBallSize), paint);


        canvas.drawRect(new Rect(mBottomBatX, mBottomBatY, mBottomBatX + mBatLength, mBottomBatY + mBatLength), paint);

        currentTime = System.currentTimeMillis();
        if(currentTime - lastUpdate > minimumUpdateTime){
            Log.d("time", String.valueOf(currentTime));
            lastUpdate = currentTime;
            xMovement +=3;

        }
    }

    public void startGame(){
        mBallVelocityY = 3;
        mBallVelocityX = 3;
    }
}
