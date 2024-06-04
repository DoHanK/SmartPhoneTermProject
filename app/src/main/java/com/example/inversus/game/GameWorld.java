package com.example.inversus.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import com.example.inversus.framework.interfaces.IGameObject;
import com.example.inversus.framework.util.CollisionHelper;

import kotlin.random.Random;

public class  GameWorld implements IGameObject {


    public static final int MAPSIZEX = 50;
    public static final int MAPSIZEY = 50;
    public static final float CELLSIZE = 2.0F;
    public static final float HALFCELLSIZE = CELLSIZE/2;

    public enum MapState {
        pass,dynamicwall,staticwall,end
    }
    Paint[] BlockColor =new Paint[4];
    static public int[] MAPInfo  = new int[MAPSIZEX*MAPSIZEY];
    static public float[] CellCenterX  = new float[MAPSIZEX*MAPSIZEY];
    static public float[] CellCenterY  = new float[MAPSIZEX*MAPSIZEY];
    static RectF[] collisionRect = new RectF[MAPSIZEX*MAPSIZEY];
    GameWorld(){
        Log.d("재생성", "게임월드 재생성입니다.");
        for(int i = 0; i <MAPSIZEX *MAPSIZEY ; ++i){

            MAPInfo[i] =  MapState.pass.ordinal();

            CellCenterX[i] = -(MAPSIZEX/2) *CELLSIZE + (i % MAPSIZEX) *CELLSIZE +HALFCELLSIZE;
            CellCenterY[i] =-(MAPSIZEY/2) *CELLSIZE + (i / MAPSIZEX) *CELLSIZE +HALFCELLSIZE;
            collisionRect[i] = new RectF();
            collisionRect[i].left = CellCenterX[i] -HALFCELLSIZE ;
            collisionRect[i].right = CellCenterX[i] + HALFCELLSIZE;;
            collisionRect[i].top = CellCenterY[i] - HALFCELLSIZE;;
            collisionRect[i].bottom = CellCenterY[i] + HALFCELLSIZE;;

            if(CellCenterX[i] >= -2 && CellCenterX[i] <= 2
                    && CellCenterY[i] >= -2 && CellCenterY[i] <= 2 ) {
                Log.d("재생성", "보호구역입니다..");
                continue;

            }
            if(Random.Default.nextInt(0,20) == 0 ) {
                if (!CollisionHelper.collides(collisionRect[i], Player.collisionRect))
                    MAPInfo[i] = MapState.staticwall.ordinal();
            }


        }

        BlockColor[0] = new Paint();
        BlockColor[0].setStyle(Paint.Style.FILL);
        BlockColor[0].setColor(Color.WHITE);
        BlockColor[0].setStrokeWidth(0.1f); // 선의 두께를 5픽셀로 설정

        BlockColor[1] = new Paint();
        BlockColor[1].setStyle(Paint.Style.FILL);
        BlockColor[1].setColor(Color.GRAY);

        BlockColor[2] = new Paint();
        BlockColor[2].setStyle(Paint.Style.FILL);
        BlockColor[2].setColor(Color.BLACK);

        BlockColor[3] = new Paint();
        BlockColor[3].setStyle(Paint.Style.STROKE);
        BlockColor[3].setStrokeWidth(0.1f);
        BlockColor[3].setColor(Color.BLACK);
    }


    @Override
    public void update(float elapsedSeconds) {

    }

    @Override
    public void draw(Canvas canvas) {

        for(int i = 0; i <MAPSIZEX*MAPSIZEY ; ++i){
            RectF Draw = new RectF();
            collisionRect[i].left = CellCenterX[i] -HALFCELLSIZE -Camera.Camera_x;
            collisionRect[i].right = CellCenterX[i] + HALFCELLSIZE-Camera.Camera_x;;
            collisionRect[i].top = CellCenterY[i] - HALFCELLSIZE-Camera.Camera_y;;
            collisionRect[i].bottom = CellCenterY[i] + HALFCELLSIZE-Camera.Camera_y;;

            canvas.drawRect( collisionRect[i], BlockColor[MAPInfo[i]]);
            canvas.drawRect( collisionRect[i], BlockColor[3]);

        }

    }
}
