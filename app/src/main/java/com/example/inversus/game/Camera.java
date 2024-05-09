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
        Camera_x = 0;
        Camera_y =  0;
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
       if(Camera_x -GameWorld.CELLSIZE/2 < -GameWorld.CELLSIZE * ((float)(GameWorld.MAPSIZEX)/2) ) {
        Camera_x  = -GameWorld.CELLSIZE * ((float)(GameWorld.MAPSIZEX)/2)+GameWorld.CELLSIZE/2;
       }
       //오른쪽
        if(Camera_x + Width + GameWorld.CELLSIZE/2> GameWorld.CELLSIZE * ((float)(GameWorld.MAPSIZEX)/2) ) {
            Camera_x  = GameWorld.CELLSIZE * ((float)(GameWorld.MAPSIZEX)/2) -Width -GameWorld.CELLSIZE/2;
        }
        //상
        if(Camera_y  < -GameWorld.CELLSIZE * ((float)(GameWorld.MAPSIZEY)/2) ) {
            Camera_y  = -GameWorld.CELLSIZE * ((float)(GameWorld.MAPSIZEY)/2);
        }
        //하
        if(Camera_y + Height > GameWorld.CELLSIZE * ((float)(GameWorld.MAPSIZEY)/2) ) {
            Camera_y  = GameWorld.CELLSIZE * ((float)(GameWorld.MAPSIZEY)/2) -Height;
        }

    }

    @Override
    public void draw(Canvas canvas) {



    }
}
