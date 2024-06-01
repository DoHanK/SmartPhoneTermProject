package com.example.inversus.game;

import static com.google.android.material.color.utilities.Score.score;

import android.graphics.Canvas;

import java.util.ArrayList;

import com.example.inversus.R;
import com.example.inversus.framework.interfaces.IGameObject;
import com.example.inversus.framework.objects.toastingImage;
import com.example.inversus.framework.res.Sound;
import com.example.inversus.framework.scene.Scene;
import com.example.inversus.framework.util.CollisionHelper;
import com.example.inversus.framework.view.Metrics;
import com.google.android.material.color.utilities.Score;

import kotlin.random.Random;

public class CollisionChecker implements IGameObject {
    private static final String TAG = CollisionChecker.class.getSimpleName();
    private final MainScene scene;

    public CollisionChecker(MainScene scene) {
        this.scene = scene;
    }

    @Override
    public void update(float elapsedSeconds) {

        //적과 총알 그리고 배경
        ArrayList<IGameObject> enemies = scene.objectsAt(MainScene.Layer.enemy);
        ArrayList<IGameObject> world = scene.objectsAt(MainScene.Layer.bg);

        GameWorld cell = (GameWorld) world.get(0);
        boolean Crush = false;
        for (int e = enemies.size() - 1; e >= 0; e--) {
            Enemy enemy = (Enemy) enemies.get(e);
            ArrayList<IGameObject> bullets = scene.objectsAt(MainScene.Layer.bullet);

            for (int b = bullets.size() - 1; b >= 0; b--) {

                Bullet bullet = (Bullet) bullets.get(b);


                if (CollisionHelper.collides(enemy, bullet)) {
                    Crush = true;
                    scene.remove(MainScene.Layer.enemy, enemy);

                    scene.addScore(enemy.getScore());
                    Effect effect = Effect.get(enemy.x, enemy.y);
                    Scene.top().add(MainScene.Layer.effect, effect);


                    for (int i = 0; i < cell.MAPSIZEX * cell.MAPSIZEY; ++i) {

                        //벽돌과 총알 검사
                        if (cell.MAPInfo[i] != GameWorld.MapState.pass.ordinal()
                                && cell.MAPInfo[i] != GameWorld.MapState.staticwall.ordinal()) {
                            if (CollisionHelper.collides(cell.collisionRect[i], bullet.collisionRect)) {

                                cell.MAPInfo[i] = 0;

                            }
                        }

                        //이펙트로 인한 벽돌 뿌시
                        float dis = (enemy.x - cell.CellCenterX[i]) * (enemy.x - cell.CellCenterX[i]) +
                                (enemy.y - cell.CellCenterY[i]) * (enemy.y - cell.CellCenterY[i]);
                        if (dis < 25) {
                            if (cell.MAPInfo[i] != GameWorld.MapState.staticwall.ordinal()) {

                                cell.MAPInfo[i] = 0;

                            }
                        }

                    }


                }


                //총알 벽돌 충돌 처리
                for (int i = 0; i < cell.MAPSIZEX * cell.MAPSIZEY; ++i) {

                    //벽돌과 총알 검사
                    if (cell.MAPInfo[i] != GameWorld.MapState.pass.ordinal()
                            && cell.MAPInfo[i] != GameWorld.MapState.staticwall.ordinal()) {
                        if (CollisionHelper.collides(cell.collisionRect[i], bullet.collisionRect)) {

                            cell.MAPInfo[i] = 0;

                        }
                    }


                }
            }
        }
        if (Crush) {
            Sound.playEffect(R.raw.bomb);
            Camera.Camera_x += Random.Default.nextDouble(-3.0f, +3.0f);
            Camera.Camera_y += Random.Default.nextDouble(-3.0f, +3.0f);
        }

        //적과 배경

        enemies = scene.objectsAt(MainScene.Layer.enemy);
        for (int e = enemies.size() - 1; e >= 0; e--) {
            Enemy enemy = (Enemy) enemies.get(e);

            for (int i = 0; i < cell.MAPSIZEX * cell.MAPSIZEY; ++i) {

                if (cell.MAPInfo[i] != GameWorld.MapState.staticwall.ordinal()) {
                    if (CollisionHelper.collides(cell.collisionRect[i], enemy.collisionRect)) {
                        if (!CollisionHelper.collides(cell.collisionRect[i], Player.collisionRect)) {
                            cell.MAPInfo[i] = 1;
                            break;
                        }

                    }
                }


            }

        }

        boolean Bcrush =false;
        ArrayList<Enemy> removeList = new ArrayList<>();
        //적과 플레이어의 충돌 처리
        enemies = scene.objectsAt(MainScene.Layer.enemy);
        for (int e = enemies.size() - 1; e >= 0; e--) {
            Enemy enemy = (Enemy) enemies.get(e);


            if (CollisionHelper.collides(Player.collisionRect, enemy.collisionRect)) {

                Bcrush =true;

                for (int i = 0; i < cell.MAPSIZEX * cell.MAPSIZEY; ++i) {

                    //이펙트로 인한 벽돌 뿌시
                    float dis = (enemy.x - cell.CellCenterX[i]) * (enemy.x - cell.CellCenterX[i]) +
                            (enemy.y - cell.CellCenterY[i]) * (enemy.y - cell.CellCenterY[i]);
                    if (dis < 25) {
                        if (cell.MAPInfo[i] != GameWorld.MapState.staticwall.ordinal()) {

                            cell.MAPInfo[i] = 0;

                        }
                    }

                }

                for (int e2 = enemies.size() - 1; e2 >= 0; e2--) {
                    Enemy enemy2 = (Enemy) enemies.get(e2);


                    if(enemy == enemy2) continue;

                    float dis = (enemy.x - enemy2.x) * (enemy.x - enemy2.x) +
                            (enemy.y - enemy2.y) * (enemy.y - enemy2.y);
                    if (dis < 25) {
                        removeList.add(enemy2);

                        Effect effect = Effect.get(enemy2.x, enemy2.y);
                        Scene.top().add(MainScene.Layer.effect, effect);
                    }

                }


                Effect effect = Effect.get(enemy.x, enemy.y);
                Scene.top().add(MainScene.Layer.effect, effect);
                removeList.add(enemy);



            }
        }

        if(Bcrush){
            Camera.Camera_x += Random.Default.nextDouble(-3.0f, +3.0f);
            Camera.Camera_y += Random.Default.nextDouble(-3.0f, +3.0f);
            toastingImage TI = toastingImage.get(R.mipmap.redfade, Metrics.width / 2 ,Metrics.height / 2 ,18.00f, 9.f,1.0f);
            Scene.top().add(MainScene.Layer.screeneffect, TI);
            Player.HP -=1;
            Sound.playEffect(R.raw.playertouch);
            if(Player.HP<1){
                new EndScene().push();
                Sound.playEffect(R.raw.fail);
                EndScene.score.setScore(scene.score.score);
                }
        }

       for(  Enemy L:  removeList){
           Scene.top().remove(MainScene.Layer.enemy,L);

       }

    }

    @Override
    public void draw(Canvas canvas) {


    }



}
