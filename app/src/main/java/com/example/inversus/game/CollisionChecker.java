package com.example.inversus.game;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import java.util.ArrayList;

import com.example.inversus.framework.interfaces.IGameObject;
import com.example.inversus.framework.util.CollisionHelper;

public class CollisionChecker implements IGameObject {
    private static final String TAG = CollisionChecker.class.getSimpleName();
    private final MainScene scene;

    public CollisionChecker(MainScene scene) {
        this.scene = scene;
    }

    @Override
    public void update(float elapsedSeconds) {

        //적과 총알
        ArrayList<IGameObject> enemies = scene.objectsAt(MainScene.Layer.enemy);
        ArrayList<IGameObject> world = scene.objectsAt(MainScene.Layer.bg);

        GameWorld cell = (GameWorld) world.get(0);

        for (int e = enemies.size() - 1; e >= 0; e--) {
            Enemy enemy = (Enemy) enemies.get(e);
            ArrayList<IGameObject> bullets = scene.objectsAt(MainScene.Layer.bullet);

            for (int b = bullets.size() - 1; b >= 0; b--) {

                Bullet bullet = (Bullet) bullets.get(b);


                if (CollisionHelper.collides(enemy, bullet)) {

                    scene.remove(MainScene.Layer.enemy, enemy);

                    scene.addScore(enemy.getScore());


                }

                for (int i = 0; i < cell.MAPSIZEX * cell.MAPSIZEY; ++i) {

                    if (cell.MAPInfo[i] != GameWorld.MapState.pass.ordinal()
                            &&cell.MAPInfo[i] != GameWorld.MapState.staticwall.ordinal() ) {
                        if (CollisionHelper.collides(cell.collisionRect[i], bullet.collisionRect)) {

                            cell.MAPInfo[i] = 0;

                        }
                    }


                }
            }
        }
        //적과 배경
        enemies = scene.objectsAt(MainScene.Layer.enemy);
        for (int e = enemies.size() - 1; e >= 0; e--) {
            Enemy enemy = (Enemy) enemies.get(e);

                for (int i = 0; i < cell.MAPSIZEX * cell.MAPSIZEY; ++i) {

                    if (cell.MAPInfo[i] != GameWorld.MapState.staticwall.ordinal() ) {
                        if (CollisionHelper.collides(cell.collisionRect[i], enemy.collisionRect)) {
                            if (!CollisionHelper.collides(cell.collisionRect[i], Player.collisionRect)) {
                                cell.MAPInfo[i] = 1;
                                break;
                            }

                        }
                    }


              }

         }


    }

    @Override
    public void draw(Canvas canvas) {


    }



}
