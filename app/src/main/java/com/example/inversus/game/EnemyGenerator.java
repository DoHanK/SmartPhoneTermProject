package com.example.inversus.game;

import android.graphics.Canvas;
import android.util.Log;

import java.util.Random;

import com.example.inversus.framework.interfaces.IGameObject;
import com.example.inversus.framework.scene.Scene;

public class EnemyGenerator implements IGameObject {
    private static final String TAG = EnemyGenerator.class.getSimpleName();
    public static final float GEN_INTERVAL = 1.0f;
    private final Random random = new Random();
    private float enemyTime = 0;
    private int wave;
    @Override
    public void update(float elapsedSeconds) {
        enemyTime -= elapsedSeconds;
        if (enemyTime < 0) {
            generate();
            enemyTime = GEN_INTERVAL;
        }
    }

    private void generate() {
        Scene scene = Scene.top();
        if (scene == null) return;

        scene.add(MainScene.Layer.enemy, new Enemy());
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
