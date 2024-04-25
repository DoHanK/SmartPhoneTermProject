package com.example.inversus.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.inversus.framework.interfaces.IGameObject;
import com.example.inversus.framework.view.Metrics;

public class Player implements IGameObject {

    private static final float PLAYERSIZE = 1;

    float x ,  y ;
    RectF DrawRect;
    Paint  PlayerBodyColor;
    float radius ;

    Player(){
        PlayerBodyColor = new Paint();
     PlayerBodyColor.setColor(Color.BLACK);
     DrawRect = new RectF();
     x  = 0 ;
     y = 0 ;
        radius = Math.min(PLAYERSIZE, PLAYERSIZE) / 2;
    }
    public void update(float elapsedSeconds){
        UpdateRect();
    }
    public void UpdateRect(){

        DrawRect.top = y + PLAYERSIZE;
        DrawRect.bottom =  y- PLAYERSIZE;
        DrawRect.left = x - PLAYERSIZE;
        DrawRect.right = x + PLAYERSIZE;


    }
    public void draw(Canvas canvas){
    canvas.drawRect(DrawRect,PlayerBodyColor);

    }

}
