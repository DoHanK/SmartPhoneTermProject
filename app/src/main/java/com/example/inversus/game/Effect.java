package com.example.inversus.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import com.example.inversus.framework.interfaces.IBoxCollidable;
import com.example.inversus.framework.interfaces.IGameObject;
import com.example.inversus.framework.interfaces.IRecyclable;
import com.example.inversus.framework.scene.RecycleBin;
import com.example.inversus.framework.scene.Scene;

import kotlin.random.Random;

public class Effect implements IGameObject, IRecyclable {

    private static final int PARTICLECOUNT = 3; //파티클 갯수
    private static final float PARTICLESIZE= 1.0f; //파티클 사이즈
    private static final float PARTICLESPEED= 1.0f; //파티클 사이즈

    private static final float PARTICLEGENRANGE= 1.8f;
    private static final float PARTICLEGLASTTIME= 1.0f;
    float x , y ;
    float[] elapsedTime = new float[PARTICLECOUNT];

    Paint[] ParticleColor = new Paint[PARTICLECOUNT];
    float[] ParticleX = new float[PARTICLECOUNT];
    float[] ParticleY = new float[PARTICLECOUNT];
    RectF[] ParticleRect= new RectF[PARTICLECOUNT];

    float time = 0;
    Effect(float x, float y ){

        this.x = x;
        this.y = y;
        time = 0;
        for(int i = 0 ; i < PARTICLECOUNT ; ++i){

            elapsedTime[i] = PARTICLESIZE;
            ParticleColor[i] = new Paint();

            ParticleColor[i].setColor(Color.argb(255,10,10,10));

            ParticleX[i] = x+(float) Random.Default.nextDouble(-PARTICLEGENRANGE,PARTICLEGENRANGE);
            ParticleY[i] = y+(float) Random.Default.nextDouble(-PARTICLEGENRANGE,PARTICLEGENRANGE);
            ParticleRect[i] =new RectF();
        }

    }

    public static Effect get(float px, float py ){
        Effect effect = (Effect) RecycleBin.get(Effect.class);
        if (effect != null) {
            effect.x = px;
            effect.y = py;
            effect.time = 0.0f;

            for(int i = 0 ; i < PARTICLECOUNT ; ++i){

                effect.elapsedTime[i] = PARTICLESIZE;
               effect.ParticleColor[i] = new Paint();

                effect.ParticleColor[i].setColor(Color.argb(255,10,10,10));
                effect.ParticleX[i] = effect.x +(float)Random.Default.nextDouble(-PARTICLEGENRANGE,PARTICLEGENRANGE);
                effect.ParticleY[i] = effect.y +(float)Random.Default.nextDouble(-PARTICLEGENRANGE,PARTICLEGENRANGE);
                effect.ParticleRect[i] = new RectF();
            }

            return effect;
        }

        return new Effect(px, py);
    }


    @Override
    public void update(float elapsedSeconds) {
        time +=elapsedSeconds;

        for(int i = 0 ; i < PARTICLECOUNT ; ++i)  elapsedTime[i] -= elapsedSeconds * PARTICLESPEED;
        UpdateRect();


        if(time >PARTICLEGLASTTIME){

            Scene scene = MainScene.top();
            scene.remove(MainScene.Layer.effect,this);
            RecycleBin.collect(this);
        }

    }


    public void UpdateRect(){

        for(int i = 0 ; i < PARTICLECOUNT ; ++i){


            ParticleRect[i].left =  ParticleX[i] -PARTICLESIZE*elapsedTime[i]-Camera.Camera_x ;
            ParticleRect[i].right =  ParticleX[i] +PARTICLESIZE*elapsedTime[i]-Camera.Camera_x ;

            ParticleRect[i].top =  ParticleY[i] -PARTICLESIZE*elapsedTime[i]-Camera.Camera_y ;
            ParticleRect[i].bottom =  ParticleY[i] +PARTICLESIZE*elapsedTime[i]-Camera.Camera_y ;


        }


    }

    @Override
    public void draw(Canvas canvas) {

        for(int i = 0 ; i < PARTICLECOUNT ; ++i){

           canvas.drawOval(ParticleRect[i],ParticleColor[i]);
        }




    }





    @Override
    public void onRecycle() {

    }
}
