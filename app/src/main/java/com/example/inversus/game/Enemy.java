package com.example.inversus.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.Log;

import com.example.inversus.R;
import com.example.inversus.framework.interfaces.IBoxCollidable;
import com.example.inversus.framework.interfaces.IGameObject;
import com.example.inversus.framework.interfaces.IRecyclable;
import com.example.inversus.framework.objects.AnimSprite;
import com.example.inversus.framework.scene.RecycleBin;
import com.example.inversus.framework.scene.Scene;
import com.example.inversus.framework.view.Metrics;

public class Enemy  implements IBoxCollidable, IRecyclable, IGameObject {
    private static final float SPEED = 5.0f;
    private static final float PLAYERSIZE = 0.8f;
    private static final float BOUNDYSIZE = 2.5f;
    private static final float RESPONETIME = 0.5f;

    private static final int    RESPONECOUNT = 1;
    private static final float FRAMEWIDTH = 6.0f;
    float x ,y , dx ,dy;
    protected RectF EnemyRect = new RectF();
    protected RectF BoundyRect = new RectF();

    protected RectF ResponeRect ;

    float[] ResponeFrameInfo = new float[RESPONECOUNT];
    float ResponeFliptime;

    float Responetime;

    int responecount = 0;
    boolean bRespone;
    Paint BodyColor;
    Paint BoundyColor;

    Paint ResponeColor;


    Enemy (float x, float y) {
        this.x = x;
        this.y = y;
        dx = 0;
        dy = 0;
        BodyColor = new Paint();
        Shader shader = new LinearGradient(0, 0, 0, PLAYERSIZE,
                Color.argb(200,144,0,0),
                Color.argb(255,255,50,50), Shader.TileMode.MIRROR);
        BodyColor.setShader(shader);

        BoundyColor = new Paint();
        BoundyColor.setColor(Color.argb(50,255,0,0));

        ResponeColor = new Paint();
        ResponeColor.setColor(Color.argb(255,255,0,0));
        ResponeColor.setStyle(Paint.Style.STROKE);
        ResponeColor.setStrokeWidth(0.05f);
        this.bRespone = false;
        this.Responetime =0;

        ResponeRect = new RectF();

    }
    protected RectF collisionRect = new RectF();
    private int level;

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }

    @Override
    public void onRecycle() {

    }

    @Override
    public void update(float elapsedSeconds) {


        if(bRespone) {
            dx = Player.x - x;
            dy = Player.y - y;
            float length = (float) Math.sqrt((Player.x - x) * (Player.x - x) + (Player.y - y) * (Player.y - y));
            dx = dx / length;
            dy = dy / length;

            x += dx * elapsedSeconds * SPEED;
            y += dy * elapsedSeconds * SPEED;


            EnemyRect.top = y - PLAYERSIZE - Camera.Camera_y;
            EnemyRect.bottom = y + PLAYERSIZE - Camera.Camera_y;
            EnemyRect.left = x - PLAYERSIZE - Camera.Camera_x;
            EnemyRect.right = x + PLAYERSIZE - Camera.Camera_x;


            BoundyRect.top = y - BOUNDYSIZE - Camera.Camera_y;
            BoundyRect.bottom = y + BOUNDYSIZE - Camera.Camera_y;
            BoundyRect.left = x - BOUNDYSIZE - Camera.Camera_x;
            BoundyRect.right = x + BOUNDYSIZE - Camera.Camera_x;

            // Collision Update
            collisionRect = EnemyRect;
            collisionRect.top = EnemyRect.top;
            collisionRect.bottom = EnemyRect.bottom;
            collisionRect.left = EnemyRect.left;
            collisionRect.right = EnemyRect.right;

        }
        else{

            this.Responetime += elapsedSeconds;
            this.ResponeFliptime += elapsedSeconds;

            if(this.Responetime > RESPONETIME){
                bRespone = true;
            }



            if( ResponeFliptime >0.05){
                for(int i = RESPONECOUNT-1 ;  i > 0 ; --i ) {

                    ResponeFrameInfo[i] = ResponeFrameInfo[i-1];
                }
                ResponeFrameInfo[0]=(1 - (Responetime / RESPONETIME));
                ResponeFliptime = 0;
            }


        }
    }

    @Override
    public void draw(Canvas canvas) {

        if(bRespone) {
            canvas.drawRoundRect(BoundyRect, 0.3f, 0.3f, BoundyColor);

            canvas.drawRoundRect(EnemyRect, 0.3f, 0.3f, BodyColor);
        }
        else{
            for(int i = 0 ;  i < RESPONECOUNT ; ++i ) {
                ResponeRect.top = y - ResponeFrameInfo[i] * FRAMEWIDTH - Camera.Camera_y;
                ResponeRect.right = x + ResponeFrameInfo[i] * FRAMEWIDTH - Camera.Camera_x;
                ResponeRect.left = x - ResponeFrameInfo[i] * FRAMEWIDTH - Camera.Camera_x;
                ResponeRect.bottom = y + ResponeFrameInfo[i] * FRAMEWIDTH - Camera.Camera_y;
                canvas.drawRect(ResponeRect, ResponeColor);

            }
        }
    }
    public int getScore(){
        return 1;
    }
}
