package com.example.inversus.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import com.example.inversus.R;
import com.example.inversus.framework.interfaces.IBoxCollidable;
import com.example.inversus.framework.interfaces.IGameObject;
import com.example.inversus.framework.interfaces.IRecyclable;
import com.example.inversus.framework.objects.AnimSprite;
import com.example.inversus.framework.scene.RecycleBin;
import com.example.inversus.framework.scene.Scene;
import com.example.inversus.framework.view.Metrics;

public class Enemy  implements IBoxCollidable, IRecyclable, IGameObject {
    private static final float SPEED = 3.0f;
    private static final float PLAYERSIZE = 0.8f;

    float x ,y , dx ,dy;
    protected RectF EnemyRect = new RectF();
    Paint BodyColor;
    Enemy () {
        x = 0;
        y = 0;
        dx = 0;
        dy = 0;
        BodyColor = new Paint();
        Shader shader = new LinearGradient(0, 0, 0, PLAYERSIZE,
                Color.argb(200,144,0,0),
                Color.argb(255,255,50,50), Shader.TileMode.MIRROR);
        BodyColor.setShader(shader);
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
    dx =  Player.x -x;
    dy = Player.y -y;
    float length = (float)Math.sqrt((Player.x -x) *(Player.x -x) +(Player.y -y)*(Player.y -y));
     dx = dx/length;
     dy = dy/length;

     x += dx*elapsedSeconds*SPEED;
     y += dy*elapsedSeconds*SPEED;


        EnemyRect.top = y - PLAYERSIZE -Camera.Camera_y;
        EnemyRect.bottom =  y +PLAYERSIZE - Camera.Camera_y;
        EnemyRect.left = x - PLAYERSIZE -Camera.Camera_x;
        EnemyRect.right = x + PLAYERSIZE -Camera.Camera_x;


        // Collision Update
        collisionRect =EnemyRect;
        collisionRect.top =   EnemyRect.top;
        collisionRect.bottom=  EnemyRect.bottom ;
        collisionRect.left =       EnemyRect.left;
        collisionRect.right =     EnemyRect.right ;
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawRoundRect(EnemyRect,0.3f,0.3f,BodyColor);

    }
    public int getScore(){
        return 1;
    }
}
