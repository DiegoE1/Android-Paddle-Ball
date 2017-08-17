package com.example.android.android_paddle_ball;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

/**
 * Created by diegoespinosa on 8/17/17.
 */

public class PaddleTouchControl extends View {
    public PaddleTouchControl(Context context) {
        super(context);
    }

    //private mPaddle;

    private static final int INVALID_POINTER_ID = -1;

    private ScaleGestureDetector mScaleDetector;

    private float mLastTouchX;
    private float mLastTouchY;
    private int mActivePointerId = INVALID_POINTER_ID;
    private float mPosX;
    private float mPosY;
    private float mScaleFactor = 1.f;

    GameState gameState = new GameState();

//    public  boolean onTouchEvent(MotionEvent event){
//        switch(event.getAction()){
//            case MotionEvent.ACTION_MOVE:
//                gameState.mBatX = (int)event.getX();
//
//        }
//        return true;
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //mScaleDetector.onTouchEvent(event);
        Log.d("event", String.valueOf(event));

        final int action = event.getAction();
        switch (action & MotionEvent.ACTION_MASK){
//            case MotionEvent.ACTION_DOWN: {
//                final float x = event.getX();
//                final float y = event.getY();
//
//                mLastTouchX = x;
//                mLastTouchY = y;
//                mActivePointerId = event.getPointerId(0);
//                break;
//            }
            case MotionEvent.ACTION_MOVE: {
                final int pointerIndex = event.findPointerIndex(mActivePointerId);
                final float x = event.getX(pointerIndex);
                final float y = event.getY(pointerIndex);

//                if(!mScaleDetector.isInProgress()){
//                    final float dx = x - mLastTouchX;
//                    final float dy = y - mLastTouchY;
//
//                    mPosX += dx;
//                    mPosY += dy;
//
//                    invalidate();
//                }

                mLastTouchX = x;
                mLastTouchY = y;

                break;
            }
//            case MotionEvent.ACTION_UP: {
//                mActivePointerId = INVALID_POINTER_ID;
//                break;
//            }
            case MotionEvent.ACTION_CANCEL: {
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }
            case MotionEvent.ACTION_POINTER_UP: {
                final int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK)
                        >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                final int pointerId = event.getPointerId(pointerIndex);
                if(pointerId == mActivePointerId) {
                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mLastTouchX = event.getX(newPointerIndex);
                    mLastTouchY = event.getY(newPointerIndex);
                    mActivePointerId = event.getPointerId(newPointerIndex);
                }
                break;
            }
        }
        return true;
    }
//
//    public void invalidate() {
//
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //int counter = 0;
//        if(counter == 0){
//            canvas.translate(initX, initY);
//            counter++;
//        }

//        canvas.save();
//        Log.d("DEBUG" , "X: " +mPosX + " Y: " + mPosY);
        canvas.translate(mPosX, mPosY);
        //canvas.scale(mScaleFactor, mScaleFactor);
        //mImageBall.draw(canvas);
//        .draw(canvas);
        canvas.restore();
    }

//    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
//        @Override
//        public boolean onScale(ScaleGestureDetector detector) {
//            mScaleFactor *= detector.getScaleFactor();
//
//            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
//            invalidate();
//            return true;
//        }
//    }
//}
}
