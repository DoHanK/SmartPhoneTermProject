package com.example.inversus.game;

import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.inversus.framework.interfaces.IGameObject;
import com.example.inversus.framework.objects.JoyStick;
import com.example.inversus.framework.scene.Scene;
import com.example.inversus.framework.view.Metrics;

public class Player implements IGameObject {

    private static final float SPEED = 8.0f;

    private static final float PLAYERSIZE = 0.8f;
    private static final int BULLETCOUNT = 5;
    private static final float BULLETOFFSET = 0.42f;
    private static final float BULLETSIZE = 0.15f;
    private static final float SHOOTCOOLTIME = 0.15f;
    private static final float RELOADTIME = 0.5f;

    private final JoyStick joyStick;
    float x ,  y ,dx, dy;
    private float angle;
    RectF DrawRect;
    Paint PlayerBodyColor;
    Paint BulletColor;
    float[] bulletPosX = new float[BULLETCOUNT];
    float[] bulletPosY = new float[BULLETCOUNT];
    RectF[] drawRBullet = new RectF[BULLETCOUNT];
    float shootCooltime = 0;
    Player(JoyStick joyStick){

        this.joyStick = joyStick;

        PlayerBodyColor = new Paint();
     PlayerBodyColor.setColor(Color.BLACK);
        BulletColor = new Paint();
        BulletColor.setColor(Color.RED);

     DrawRect = new RectF();
     x  = 0 ;
     y = 0;

     for(int i = 0 ; i < BULLETCOUNT ;++i){

         bulletPosX[i] = BULLETOFFSET * (float)(Math.cos(toRadians(360.f/BULLETCOUNT)*i));
         bulletPosY[i] = BULLETOFFSET * (float)(Math.sin(toRadians(360.f/BULLETCOUNT)*i));
         drawRBullet[i] = new RectF();
     }

    }
    public void update(float elapsedSeconds){

        if (joyStick.power > 0) {
            float distance = SPEED * joyStick.power;
            dx = (float) (distance * Math.cos(joyStick.angle_radian));
            dy = (float) (distance * Math.sin(joyStick.angle_radian));
            angle = (float) Math.toDegrees(joyStick.angle_radian);

            float timedDx = dx * elapsedSeconds;
            float timedDy = dy * elapsedSeconds;
            x += timedDx;
            y += timedDy;

        } else {
            dx = dy = 0;
        }



        UpdateRect();
        RotateBullet(elapsedSeconds);
    }

    public void RotateBullet(float elapsedSeconds){
        for(int i = 0 ; i < BULLETCOUNT ;++i){
            float X = bulletPosX[i];
            float Y = bulletPosY[i];
            bulletPosX[i] =  X * (float)Math.cos(elapsedSeconds) - Y * (float)Math.sin(elapsedSeconds);
            bulletPosY[i] = X * (float)Math.sin(elapsedSeconds) + Y * (float)Math.cos(elapsedSeconds);

            drawRBullet[i].left  = bulletPosX[i]- BULLETSIZE +x-Camera.Camera_x;
            drawRBullet[i].top  = bulletPosY[i]+ BULLETSIZE+y-Camera.Camera_y;
            drawRBullet[i].right  = bulletPosX[i]+ BULLETSIZE+x -Camera.Camera_x;
            drawRBullet[i].bottom = bulletPosY[i]- BULLETSIZE+y -Camera.Camera_y;
        }
    }
    public void UpdateRect(){

        DrawRect.top = y + PLAYERSIZE -Camera.Camera_y;
        DrawRect.bottom =  y- PLAYERSIZE - Camera.Camera_y;
        DrawRect.left = x - PLAYERSIZE -Camera.Camera_x;
        DrawRect.right = x + PLAYERSIZE -Camera.Camera_x;


    }
    public void draw(Canvas canvas){
    canvas.drawRoundRect(DrawRect,0.3f,0.3f,PlayerBodyColor);
        for(int i = 0 ; i < BULLETCOUNT ;++i){

          canvas.drawOval(drawRBullet[i],BulletColor);

        }

    }

    public void ShootBullet(){
        Scene.top().add(MainScene.Layer.bullet ,new Bullet(x ,y,angle));
    }
}
