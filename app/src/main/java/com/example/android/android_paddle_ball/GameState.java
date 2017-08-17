package com.example.android.android_paddle_ball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by diegoespinosa on 8/16/17.
 */

public class GameState {
    //screen
    final int mScreenWidth = 710; //385
    final int mScreenHeight= 820; //420

    //ball
    final int mBallSize = 20;
    int mBallX = 380;
    int mBallY = 400;
    int mBallVelocityX = 5;
    int mBallVelocityY = -10;

    //bat
    final int mBatLength = 150;
    final int mBatHeight = 25;
    public int mBatX =  340;
    final int mBatY = 780;
    final int mBatSpeed = 3;

    Paint gameOverPaint = new Paint();
    Paint gameLivesPaint = new Paint();
    Paint gamePointsPaint = new Paint();

    public boolean isGameOver = false;

    private Rect r = new Rect();

    public int numberLives = 5;
    public int numberPoints = 0;


    public GameState() {

    }

    public  boolean onTouch(int x, int y){

        if(x > 0){
            mBatX = x;
        }

        return true;
    }


    public void update(){
        mBallX += mBallVelocityX;
        mBallY += mBallVelocityY;

        if(mBallY >= mScreenHeight || numberLives == 0){
            if(numberLives !=0){numberLives--; Log.e("GameOver", mBallX + " " + mBallY);}
            mBallX = 380; mBallY =400;
            if(numberLives == 0)isGameOver = true;

        }
        if(mBallX >= mScreenWidth || mBallX <= 0 ){
            mBallVelocityX *=-1;
        }

        if(mBallY <= 0){
            mBallVelocityY *=-1;
        }


        if(mBallX >= mBatX && mBallX <= mBatX + mBatLength && mBallY >= mBatY){
            mBallVelocityY *=-1;
            numberPoints++;
        }
    }


    public void draw(Canvas canvas, Paint paint){
        canvas.drawRGB(20,20,20);

        paint.setARGB(200,0,200,0);

        // left, top, right, bottom

        canvas.drawRect(new Rect(mBallX /* xMovement*/, mBallY, mBallX + mBallSize /*+ xMovement*/, mBallY + mBallSize), paint);


        canvas.drawRect(new Rect(mBatX, mBatY, mBatX + mBatLength, mBatY + mBatHeight), paint);

        if(isGameOver == true && numberLives == 0){
            gameOverPaint.setColor(Color.WHITE);
            gameOverPaint.setTextSize(100);
            drawGameOverText(canvas, gameOverPaint, "Game Over");
            mBallVelocityY = 0; mBallVelocityX = 0;
            mBallX = 360; mBallY =400;
            drawLivesText(canvas, gameLivesPaint, String.valueOf(numberLives));
            drawPointsText(canvas, gamePointsPaint, String.valueOf(numberPoints));
        } else {
            gameSetPaint();

            drawLivesText(canvas, gameLivesPaint, String.valueOf(numberLives));
            drawPointsText(canvas, gamePointsPaint, String.valueOf(numberPoints));
        }
    }

    private void gameSetPaint(){
        gameLivesPaint.setColor(Color.WHITE);
        gamePointsPaint.setColor(Color.WHITE);

        gameLivesPaint.setTextSize(25);
        gamePointsPaint.setTextSize(25);

    }

    private void drawGameOverText(Canvas canvas, Paint paint, String text){
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth /2f - r.width() / 2f - r.left;
        float y = cHeight / 2f - r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }

    private void drawLivesText(Canvas canvas, Paint paint, String text){
        canvas.drawText(text, 200, 25 , paint);
    }

    private void drawPointsText(Canvas canvas, Paint paint, String text){
        canvas.drawText(text, 650, 25, paint);
    }

}



