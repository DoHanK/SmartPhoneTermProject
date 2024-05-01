package com.example.inversus.game;

import android.graphics.Canvas;
import android.util.Log;

import com.example.inversus.framework.interfaces.IGameObject;
import com.example.inversus.framework.view.Metrics;

public class Camera implements IGameObject {

    Player m_Player;
    static float Camera_x, Camera_y ;
    float Width, Height;

    private static final float INTERPOLATIONCOUNT = 10.f;


    Camera(Player pOwner){
        m_Player = pOwner;
        Camera_x -=  Metrics.GetWidth()/2;
        Camera_y -=  Metrics.GetHeight()/2;
        Width =  Metrics.GetWidth();
        Height =  Metrics.GetHeight();


    }

    @Override
    public void update(float elapsedSeconds) {
        float dx ,dy;

             dx = (m_Player.x - (Width / 2) - Camera_x) / INTERPOLATIONCOUNT;

             dy = (m_Player.y - (Height / 2) - Camera_y) / INTERPOLATIONCOUNT;




            Camera_x += dx;
            Camera_y += dy;


        //왼쪽끝에 다다랐을때
       if(Camera_x -GameWord.CELLSIZE/2 < -GameWord.CELLSIZE * ((float)(GameWord.MAPSIZEX)/2) ) {
        Camera_x  = -GameWord.CELLSIZE * ((float)(GameWord.MAPSIZEX)/2)+GameWord.CELLSIZE/2;
       }
       //오른쪽
        if(Camera_x + Width + GameWord.CELLSIZE/2> GameWord.CELLSIZE * ((float)(GameWord.MAPSIZEX)/2) ) {
            Camera_x  = GameWord.CELLSIZE * ((float)(GameWord.MAPSIZEX)/2) -Width -GameWord.CELLSIZE/2;
        }
        //상
        if(Camera_y  < -GameWord.CELLSIZE * ((float)(GameWord.MAPSIZEY)/2) ) {
            Camera_y  = -GameWord.CELLSIZE * ((float)(GameWord.MAPSIZEY)/2);
        }
        //하
        if(Camera_y + Height > GameWord.CELLSIZE * ((float)(GameWord.MAPSIZEY)/2) ) {
            Camera_y  = GameWord.CELLSIZE * ((float)(GameWord.MAPSIZEY)/2) -Height;
        }

    }

    @Override
    public void draw(Canvas canvas) {



    }
}
