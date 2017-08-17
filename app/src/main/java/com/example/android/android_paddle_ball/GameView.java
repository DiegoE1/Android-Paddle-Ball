package com.example.android.android_paddle_ball;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by diegoespinosa on 8/9/17.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback

{


    private static final String TAG = GameView.class.getName();
    private GameThread mThread;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);

        mThread = new GameThread(holder, context, new Handler());

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mThread.getGameState().isGameOver == false) {
            Log.d(TAG, "onTouchEvent() called with: event = [" + event + "]");
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    int x = (int) event.getX();
                    int y = (int) event.getY();
                    mThread.getGameState().onTouch(x, y);
            }
        }
//        int x = (int)event.getX();
//        int y = (int)event.getY();
//        mThread.getGameState().onTouch(x,y);
        return super.onTouchEvent(event);
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
