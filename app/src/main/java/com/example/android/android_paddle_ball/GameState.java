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
    /**
     *
     * Bugs:
     * (1)Ball bounces off lower part of paddle
     * (2)Paddle when first touch is detected will move far left
     *
     */

    //Screen
    final int mScreenWidth = 710; //385
    final int mScreenHeight = 820; //420

    //Ball
    final int mBallSize = 20;
    int mBallX = 380;
    int mBallY = 400;
    int mBallVelocityX = 5;
    int mBallVelocityY = -10;

    //Bat
    final int mBatLength = 150;
    final int mBatHeight = 25;
    public int mBatX = 340;
    final int mBatY = 780;

    //Paint
    private Paint gameOverPaint = new Paint();
    private Paint gameLivesPaint = new Paint();
    private Paint gamePointsPaint = new Paint();

    //Boolean
    public boolean isGameOver = false;

    //Scoring
    public int numberLives = 5;
    public int numberPoints = 0;

    private Rect r = new Rect();

    public GameState() {

    }

    //Paddle position
    public boolean onTouch(int x, int y) {

        mBatX = x;

        return true;
    }

    //Update ball physics and game state
    public void update() {
        mBallX += mBallVelocityX;
        mBallY += mBallVelocityY;

        if (mBallY >= mScreenHeight || numberLives == 0) {

            if (numberLives != 0) {
                numberLives--;
                Log.e("GameOver", mBallX + " " + mBallY);
                mBallX = 380;
                mBallY = 400;
            } else {
                isGameOver = true;
            }

        }
        if (mBallX >= mScreenWidth || mBallX <= 0) {
            mBallVelocityX *= -1;
        }

        if (mBallY <= 0) {
            mBallVelocityY *= -1;
        }

        if (mBallX >= mBatX && mBallX <= mBatX + mBatLength && mBallY == mBatY) {
            mBallVelocityY *= -1;
            numberPoints++;
        }
    }

    //Draw method to draw the text within the GameView
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawRGB(20, 20, 20);

        paint.setARGB(200, 0, 200, 0);

        canvas.drawRect(new Rect(mBallX, mBallY, mBallX + mBallSize, mBallY + mBallSize), paint);


        canvas.drawRect(new Rect(mBatX, mBatY, mBatX + mBatLength, mBatY + mBatHeight), paint);

        if (isGameOver == true && numberLives == 0) {
            gameOverPaint.setColor(Color.WHITE);
            gameOverPaint.setTextSize(100);
            drawGameOverText(canvas, gameOverPaint, "Game Over");
            mBallVelocityY = 0;
            mBallVelocityX = 0;
            mBallX = 360;
            mBallY = 400;
            drawLivesText(canvas, gameLivesPaint, String.valueOf(numberLives));
            drawPointsText(canvas, gamePointsPaint, String.valueOf(numberPoints));
        } else {
            gameSetPaint();

            drawLivesText(canvas, gameLivesPaint, String.valueOf(numberLives));
            drawPointsText(canvas, gamePointsPaint, String.valueOf(numberPoints));
        }
    }

    //Method to set the color and text size of paint objects
    private void gameSetPaint() {
        gameLivesPaint.setColor(Color.WHITE);
        gamePointsPaint.setColor(Color.WHITE);

        gameLivesPaint.setTextSize(25);
        gamePointsPaint.setTextSize(25);

    }

    //Method to draw GameOver in the GameView, which will be centered in GameView
    private void drawGameOverText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f - r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }

    //Method to draw the lives text
    private void drawLivesText(Canvas canvas, Paint paint, String text) {
        canvas.drawText(text, 200, 25, paint);
    }

    //Method to draw the points text
    private void drawPointsText(Canvas canvas, Paint paint, String text) {
        canvas.drawText(text, 650, 25, paint);
    }

}



