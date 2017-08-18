package com.example.android.android_paddle_ball;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mPlayGame;
    private TextView mTextLives;
    private TextView mTextPoints;
    private ImageView mTopBorder;
    private GameView mGanvas;
    private Button mRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayGame = (Button) findViewById(R.id.button_play_game);
        mRestart = (Button) findViewById(R.id.button_restart_game);

        mTextLives = (TextView) findViewById(R.id.tv_number_lives);
        mTextPoints = (TextView) findViewById(R.id.tv_number_points);

        mTopBorder = (ImageView) findViewById(R.id.image_top_screen);

        mGanvas = (GameView) findViewById(R.id.ganvas);

        mPlayGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewsVisible();
            }
        });

        //Future restart button
//        if(mGameThread.getGameState().isGameOver == true){
//            mRestart.setVisibility(View.VISIBLE);
//            mRestart.setOnClickListener(new View.OnClickListener(){
//
//                @Override
//                public void onClick(View v) {
//                    gameView = new GameView(MainActivity.this, null);
//                }
//            });
//        }
    }
    //Sets texts and GameView visible
    private void setViewsVisible(){
        mPlayGame.setVisibility(View.INVISIBLE);
        mTextLives.setVisibility(View.VISIBLE);
        mTextPoints.setVisibility(View.VISIBLE);
        mTopBorder.setVisibility(View.VISIBLE);
        mGanvas.setVisibility(View.VISIBLE);
    }

    //Future update score for top bar (outside of GameView)
//    public void updateScores(int lives, int points){
//        mTextLives.setText(getString(R.string.number_lives) + " " + String.valueOf(lives));
//        mTextPoints.setText(getString(R.string.number_points) + " " + String.valueOf(points));
//    }

}
