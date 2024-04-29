package com.example.inversus.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.inversus.framework.interfaces.IGameObject;

public class Bullet implements IGameObject {
    private static final float BULLETSIZEX = 0.2f;
    private static final float BULLETSIZEY = 0.1f;

    private static final float BULLETSPEED = 0.8f;
    float dx, dy ,x,y;
    float angle;
    RectF DrawRect= new RectF();

    Paint BulletColor;

    Bullet(float x , float y, float angle){

        BulletColor = new Paint(Color.BLACK);
        this.x = x;
        this.y = y;
        DrawRect.top = y -BULLETSIZEY;
        DrawRect.bottom = y +BULLETSIZEY;
        DrawRect.left = x -BULLETSIZEY;
        DrawRect.right = x +BULLETSIZEY;

       // bulletPosX[i] =  X * (float)Math.cos(elapsedSeconds) - Y * (float)Math.sin(elapsedSeconds);
        //bulletPosY[i] = X * (float)Math.sin(elapsedSeconds) + Y * (float)Math.cos(elapsedSeconds);

        this.dx = (float)(Math.cos((float)Math.toRadians(angle)))*BULLETSPEED;
        this.dy = (float)(Math.sin((float)Math.toRadians(angle)))*BULLETSPEED;


        this.angle = angle;
    }
    @Override
    public void update(float elapsedSeconds) {
        x+=dx;
        y+=dy;

        DrawRect.top = y -BULLETSIZEY-Camera.Camera_y;
        DrawRect.bottom = y +BULLETSIZEY-Camera.Camera_y;
        DrawRect.left = x -BULLETSIZEX-Camera.Camera_x;
        DrawRect.right = x +BULLETSIZEX -Camera.Camera_x;
    }

    @Override
    public void draw(Canvas canvas) {


        canvas.save();
        canvas.rotate(angle  , x-Camera.Camera_x, y-Camera.Camera_y);
        canvas.drawRect(DrawRect,BulletColor);
        canvas.restore();
    }
}
