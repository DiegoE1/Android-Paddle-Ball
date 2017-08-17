package com.example.android.android_paddle_ball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.view.SurfaceHolder;

/**
 * Created by diegoespinosa on 8/16/17.
 */

public class GameThread extends Thread {

    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;
    private GameState mState;

    public GameThread(){
        mState = new GameState();
    }

    public GameThread(SurfaceHolder surfaceHolder, Context context, Handler handler){
        mSurfaceHolder = surfaceHolder;
        mPaint = new Paint();
        mState = new GameState();
    }

    @Override
    public void run() {
        while(true){
            Canvas canvas = mSurfaceHolder.lockCanvas();
            mState.update();
            mState.draw(canvas, mPaint);
            mSurfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public GameState getGameState(){
        return mState;
    }
}
