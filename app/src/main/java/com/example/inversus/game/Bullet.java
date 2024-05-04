package com.example.inversus.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.inversus.framework.interfaces.IGameObject;
import com.example.inversus.framework.interfaces.IRecyclable;
import com.example.inversus.framework.scene.RecycleBin;
import com.example.inversus.framework.scene.Scene;

public class Bullet implements IGameObject, IRecyclable {
    private static final float BULLETSIZEX = 0.2f;
    private static final float BULLETSIZEY = 0.2f;

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

    public static Bullet get(float x, float y, float angle) {
        Bullet bullet = (Bullet) RecycleBin.get(Bullet.class);
        if (bullet != null) {
            bullet.x = x;
            bullet.y = y;
            bullet.angle = angle;
            return bullet;
        }
        return new Bullet(x, y, angle);
    }
    @Override
    public void update(float elapsedSeconds) {
        x+=dx;
        y+=dy;

        DrawRect.top = y -BULLETSIZEY-Camera.Camera_y;
        DrawRect.bottom = y +BULLETSIZEY-Camera.Camera_y;
        DrawRect.left = x -BULLETSIZEX-Camera.Camera_x;
        DrawRect.right = x +BULLETSIZEX -Camera.Camera_x;

        boolean boutline = false;
        //왼쪽끝에 다다랐을때
        if(x -BULLETSIZEY < -GameWord.CELLSIZE * ((float)(GameWord.MAPSIZEX)/2) ) {
            boutline =true;
        }
        //오른쪽
        if(x +BULLETSIZEY > GameWord.CELLSIZE * ((float)(GameWord.MAPSIZEX)/2) ) {
            boutline =true;
        }
        //상
        if(y  -BULLETSIZEY< -GameWord.CELLSIZE * ((float)(GameWord.MAPSIZEY)/2) ) {
            boutline =true;
        }
        //하
        if(y + BULLETSIZEY > GameWord.CELLSIZE * ((float)(GameWord.MAPSIZEY)/2) ) {
            boutline = true;
        }

        if(boutline){
            Scene scene = MainScene.top();
            scene.remove(MainScene.Layer.bullet,this);
            RecycleBin.collect(this);


        }
    }

    @Override
    public void draw(Canvas canvas) {


        canvas.save();
        canvas.rotate(angle  , x-Camera.Camera_x, y-Camera.Camera_y);
        canvas.drawRect(DrawRect,BulletColor);
        canvas.restore();
    }

    @Override
    public void onRecycle() {

    }
}
