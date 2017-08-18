package com.example.android.android_paddle_ball;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static android.view.MotionEvent.INVALID_POINTER_ID;

/**
 * Created by diegoespinosa on 8/9/17.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread mThread;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);

        mThread = new GameThread(holder, context, new Handler());
    }

    //Touch event listener that gives data to GameState
    float mLastTouchX;
    float mLastTouchY;
    int mActivePointerId = INVALID_POINTER_ID;
    int mPosX, mPosY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mThread.getGameState().isGameOver == false) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:{
                    final int pointerIndex = MotionEventCompat.getActionIndex(event);
                    final float x = MotionEventCompat.getX(event, pointerIndex);
                    final float y = MotionEventCompat.getY(event, pointerIndex);

                    mLastTouchX = x;
                    mLastTouchY = y;

                    mActivePointerId = MotionEventCompat.getPointerId(event, 0);
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    final int pointerIndex = MotionEventCompat.findPointerIndex(event, mActivePointerId);

                    final float x = MotionEventCompat.getX(event, pointerIndex);
                    final float y = MotionEventCompat.getY(event, pointerIndex);

                    final float dx = x - mLastTouchX;
                    final float dy = y - mLastTouchY;

                    mPosX += dx;
                    mPosY += dy;

                    mThread.getGameState().onTouch(mPosX, mPosY);

                    mLastTouchX = x;
                    mLastTouchY = y;
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    mActivePointerId = INVALID_POINTER_ID;
                    break;
                }

                case MotionEvent.ACTION_CANCEL: {
                    mActivePointerId = INVALID_POINTER_ID;
                    break;
                }

                case MotionEvent.ACTION_POINTER_UP: {

                    final int pointerIndex = MotionEventCompat.getActionIndex(event);
                    final int pointerId = MotionEventCompat.getPointerId(event, pointerIndex);

                    if (pointerId == mActivePointerId) {
                        final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                        mLastTouchX = MotionEventCompat.getX(event, newPointerIndex);
                        mLastTouchY = MotionEventCompat.getY(event, newPointerIndex);
                        mActivePointerId = MotionEventCompat.getPointerId(event, newPointerIndex);
                    }
                    break;
                }
            }
        }
        return true;
    }

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
