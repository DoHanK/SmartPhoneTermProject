package com.example.inversus.game;

import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.inversus.framework.interfaces.IGameObject;
import com.example.inversus.framework.view.Metrics;

public class Player implements IGameObject {

    private static final float PLAYERSIZE = 0.5f;
    private static final int BULLETCOUNT = 5;
    private static final float BULLETOFFSET = 0.3f;
    private static final float BULLETSIZE = 0.1f;
    float x ,  y ;
    RectF DrawRect;
    Paint PlayerBodyColor;
    Paint BulletColor;
    float[] bulletPosX = new float[5];
    float[] bulletPosY = new float[5];
    Player(){
        PlayerBodyColor = new Paint();
     PlayerBodyColor.setColor(Color.BLACK);
        BulletColor = new Paint();
        BulletColor.setColor(Color.RED);

     DrawRect = new RectF();
     x  = 2 ;
     y = 0;

     for(int i = 0 ; i < BULLETCOUNT ;++i){

         bulletPosX[i] = BULLETOFFSET * (float)(Math.cos(toRadians(360.f/BULLETCOUNT)*i));
         bulletPosY[i] = BULLETOFFSET * (float)(Math.sin(toRadians(360.f/BULLETCOUNT)*i));

     }

    }
    public void update(float elapsedSeconds){
        UpdateRect();
        RotateBullet(elapsedSeconds);
    }

    public void RotateBullet(float elapsedSeconds){
        for(int i = 0 ; i < BULLETCOUNT ;++i){
            float X = bulletPosX[i];
            float Y = bulletPosY[i];
            bulletPosX[i] =  X * (float)Math.cos(elapsedSeconds) - Y * (float)Math.sin(elapsedSeconds);
            bulletPosY[i] = X * (float)Math.sin(elapsedSeconds) + Y * (float)Math.cos(elapsedSeconds);
        }
    }
    public void UpdateRect(){

        DrawRect.top = y + PLAYERSIZE;
        DrawRect.bottom =  y- PLAYERSIZE;
        DrawRect.left = x - PLAYERSIZE;
        DrawRect.right = x + PLAYERSIZE;


    }
    public void draw(Canvas canvas){
    canvas.drawRoundRect(DrawRect,0.1f,0.1f,PlayerBodyColor);


        for(int i = 0 ; i < BULLETCOUNT ;++i){

           RectF drawR = new RectF(  bulletPosX[i]- BULLETSIZE +x,
                   bulletPosY[i]+ BULLETSIZE+y,
                   bulletPosX[i]+ BULLETSIZE+x,
                   bulletPosY[i]- BULLETSIZE +y);

           canvas.drawOval(drawR,BulletColor);

        }

    }

}
