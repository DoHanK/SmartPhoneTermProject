package com.example.inversus.game;

import android.view.MotionEvent;

import com.example.inversus.R;
import com.example.inversus.framework.objects.VertScrollBackground;
import com.example.inversus.framework.objects.Score;
import com.example.inversus.framework.scene.Scene;
import com.example.inversus.game.Player;

public class MainScene extends Scene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final Player player;
    Score score; // package private

    public enum Layer {
        bg, enemy, bullet, player, ui, controller, COUNT
    }
    public MainScene() {
        initLayers(Layer.COUNT);

        add(Layer.controller, new EnemyGenerator());
        add(Layer.controller, new CollisionChecker(this));

        add(Layer.bg, new VertScrollBackground(R.mipmap.bg_city, 0.2f));
        add(Layer.bg, new VertScrollBackground(R.mipmap.clouds, 0.4f));

         this.player = new Player();
        add(Layer.player,  this.player);

        this.score = new Score(R.mipmap.number_24x32, 8.5f, 0.5f, 0.6f);
        score.setScore(0);
        add(Layer.ui, score);
    }

    public void addScore(int amount) {
        score.add(amount);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        //return player.onTouch(event);
        return false;
    }
}
