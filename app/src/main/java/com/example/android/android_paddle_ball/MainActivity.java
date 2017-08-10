package com.example.android.android_paddle_ball;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;

    private Button mPlayGame;
    private TextView mTextLives;
    private TextView mTextPoints;
    private ImageView mTopBorder;
    private MyImageView mGameCanvas;
    private GameView mGanvas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        mPlayGame = (Button) findViewById(R.id.button_play_game);
        mTextLives = (TextView) findViewById(R.id.tv_number_lives);
        mTextPoints = (TextView) findViewById(R.id.tv_number_points);
        mTopBorder = (ImageView) findViewById(R.id.image_top_screen);
        mGameCanvas = (MyImageView) findViewById(R.id.game_canvas);
        mGanvas = (GameView) findViewById(R.id.ganvas);

        mPlayGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewsVisible();
            }
        });
    }

    private void setViewsVisible(){
        mPlayGame.setVisibility(View.INVISIBLE);
        mTextLives.setVisibility(View.VISIBLE);
        mTextPoints.setVisibility(View.VISIBLE);
        mTopBorder.setVisibility(View.VISIBLE);
        mGameCanvas.setVisibility(View.VISIBLE);
        mGanvas.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
