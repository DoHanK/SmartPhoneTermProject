package com.example.inversus.game;

import android.graphics.Canvas;
import android.util.Log;

import java.util.Random;

import com.example.inversus.framework.interfaces.IGameObject;
import com.example.inversus.framework.scene.Scene;

public class EnemyGenerator implements IGameObject {
    private static final String TAG = EnemyGenerator.class.getSimpleName();
    public static final float GEN_INTERVAL = 0.1f;
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
        float WorldSizeX = GameWorld.CELLSIZE*(float)GameWorld.MAPSIZEX/2;

        float WorldSizeY = GameWorld.CELLSIZE*(float)GameWorld.MAPSIZEY/2;

        //Log.d("월드 정보" , "X :"+ WorldSizeX + "Y  :"+WorldSizeY +"랜덤수 "+(float)kotlin.random.Random.Default.nextDouble(-WorldSizeX, WorldSizeX));
       scene.add(MainScene.Layer.enemy, new Enemy((float)kotlin.random.Random.Default.nextDouble(-WorldSizeX, WorldSizeX),
               (float) kotlin.random.Random.Default.nextDouble(-WorldSizeY, WorldSizeY)));

        //scene.add(MainScene.Layer.enemy, new Enemy(0, 0));
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
