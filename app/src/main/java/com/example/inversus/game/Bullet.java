package com.example.inversus.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.inversus.framework.interfaces.IBoxCollidable;
import com.example.inversus.framework.interfaces.IGameObject;
import com.example.inversus.framework.interfaces.IRecyclable;
import com.example.inversus.framework.scene.RecycleBin;
import com.example.inversus.framework.scene.Scene;

public class Bullet implements IGameObject, IRecyclable, IBoxCollidable {
    private static final float BULLETSIZEX = 0.2f;
    private static final float BULLETSIZEY = 0.2f;
    private static final int SHADOWCOUNT = 10;
    private static final float BULLETSPEED = 8.0f;
    private static final float TRAILERSIZE = 0.95f;

    private static final float TRAILERCOOLTIME = 0.001f;
    float dx, dy ,x,y;
    float angle;
    RectF DrawRect= new RectF();

    Paint BulletColor;
    float trailTime = 0;

    RectF[] DrawShadowRect = new RectF[SHADOWCOUNT];
    float[] ShadowX = new float[SHADOWCOUNT];
    float[] ShadowY =new float[SHADOWCOUNT];
    Paint[] ShadowColor = new Paint[SHADOWCOUNT];

    protected RectF collisionRect = new RectF();
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


        for(int i = 0; i <SHADOWCOUNT ; i++){

            DrawShadowRect[i] = new RectF();
            ShadowColor[i] =new Paint();
            ShadowColor[i].setColor(
                    Color.argb(((SHADOWCOUNT-i)*255/SHADOWCOUNT),(i*255/SHADOWCOUNT),0,0));
        }


        this.angle = angle;
    }

    public static Bullet get(float x, float y, float angle) {
        Bullet bullet = (Bullet) RecycleBin.get(Bullet.class);
        if (bullet != null) {
            bullet.x = x;
            bullet.y = y;
            bullet.angle = angle;
            bullet.dx = (float)(Math.cos((float)Math.toRadians(angle)))*BULLETSPEED;
            bullet.dy = (float)(Math.sin((float)Math.toRadians(angle)))*BULLETSPEED;
            return bullet;
        }
        return new Bullet(x, y, angle);
    }
    @Override
    public void update(float elapsedSeconds) {
        x+=dx*elapsedSeconds*BULLETSPEED;
        y+=dy*elapsedSeconds*BULLETSPEED;
        trailTime+=elapsedSeconds;

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

        if(trailTime > TRAILERCOOLTIME) {
            for (int i = SHADOWCOUNT - 1; i > 0; --i) {
                ShadowX[i] = ShadowX[i - 1];
                ShadowY[i] = ShadowY[i - 1];

                DrawShadowRect[i].bottom = ShadowY[i] + BULLETSIZEY * (float) Math.pow(TRAILERSIZE, i) - Camera.Camera_y;
                DrawShadowRect[i].top = ShadowY[i] - BULLETSIZEY * (float) Math.pow(TRAILERSIZE, i) - Camera.Camera_y;
                DrawShadowRect[i].left = ShadowX[i] - BULLETSIZEY * (float) Math.pow(TRAILERSIZE, i) - Camera.Camera_x;
                DrawShadowRect[i].right = ShadowX[i] + BULLETSIZEY * (float) Math.pow(TRAILERSIZE, i) - Camera.Camera_x;


            }
            ShadowX[0] = x;
            ShadowY[0] = y;
            DrawShadowRect[0].bottom = ShadowY[0] + BULLETSIZEY * (float) Math.pow(TRAILERSIZE, 0) - Camera.Camera_y;
            DrawShadowRect[0].top = ShadowY[0] - BULLETSIZEY * (float) Math.pow(TRAILERSIZE, 0) - Camera.Camera_y;
            DrawShadowRect[0].left = ShadowX[0] - BULLETSIZEY * (float) Math.pow(TRAILERSIZE, 0) - Camera.Camera_x;
            DrawShadowRect[0].right = ShadowX[0] + BULLETSIZEY * (float) Math.pow(TRAILERSIZE, 0) - Camera.Camera_x;


            trailTime = 0;
        }




        collisionRect = DrawRect;

    }

    @Override
    public void draw(Canvas canvas) {
        for(int i = SHADOWCOUNT-1 ; i > -1 ; --i) {

            canvas.drawOval(DrawShadowRect[i],ShadowColor[i]);
        }

        canvas.drawOval(DrawRect,BulletColor);

    }

    @Override
    public void onRecycle() {

    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }
}
