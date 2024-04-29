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
        Camera_y -=  Metrics.GetWidth()/2;
        Width =  Metrics.GetWidth();
        Height =  Metrics.GetHeight();


    }

    @Override
    public void update(float elapsedSeconds) {

        if((m_Player.x -Camera_x)*(m_Player.x -Camera_x)
                +(m_Player.y -Camera_y) *(m_Player.y -Camera_y) >3.0f) {
            float dx = (m_Player.x - Width / 2 - Camera_x) / INTERPOLATIONCOUNT;
            float dy = (m_Player.y - Height / 2 - Camera_y) / INTERPOLATIONCOUNT;
            Camera_x += dx;
            Camera_y += dy;
        }
    }

    @Override
    public void draw(Canvas canvas) {



    }
}
