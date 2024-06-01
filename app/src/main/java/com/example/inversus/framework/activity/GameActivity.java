package com.example.inversus.framework.activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.example.inversus.R;
import com.example.inversus.framework.res.Sound;
import com.example.inversus.framework.view.GameView;

public class GameActivity extends AppCompatActivity {

    public static GameActivity activity;
    private GameView gameView;

    static boolean binit = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        gameView = new GameView(this);
        //gameView.setFullScreen();
        setContentView(gameView);

        if(binit){
            Sound.playEffect(R.raw.playertouch);
            Sound.playEffect(R.raw.fail);
            Sound.playEffect(R.raw.select);
            Sound.playEffect(R.raw.shoot);
            Sound.playEffect(R.raw.bomb);
            binit= false;
        }

        //new MainScene().push();

        getOnBackPressedDispatcher().addCallback(onBackPressedCallback);
    }

    private final OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            gameView.onBackPressed();
        }
    };


    @Override
    protected void onPause() {
        gameView.pauseGame();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resumeGame();
    }

    @Override
    protected void onDestroy() {
        gameView.destroyGame();
        super.onDestroy();
    }
}