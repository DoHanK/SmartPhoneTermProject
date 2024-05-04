package com.example.inversus.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import com.example.inversus.framework.interfaces.IGameObject;

public class GameWord implements IGameObject {


    public static final int MAPSIZEX = 50;
    public static final int MAPSIZEY = 50;
    public static final float CELLSIZE = 2.0F;
    public static final float HALFCELLSIZE = CELLSIZE/2;

    public enum MapState {
        pass,dynamicwall,staticwall,end
    }
    Paint[] BlockColor =new Paint[4];
    public int[] MAPInfo  = new int[MAPSIZEX*MAPSIZEY];
    static public float[] CellCenterX  = new float[MAPSIZEX*MAPSIZEY];
    static public float[] CellCenterY  = new float[MAPSIZEX*MAPSIZEY];

    GameWord(){

        for(int i = 0; i <MAPSIZEX *MAPSIZEY ; ++i){
            MAPInfo[i] = 0;
            CellCenterX[i] = -(MAPSIZEX/2) *CELLSIZE + (i % MAPSIZEX) *CELLSIZE +HALFCELLSIZE;
            CellCenterY[i] =-(MAPSIZEY/2) *CELLSIZE + (i / MAPSIZEX) *CELLSIZE +HALFCELLSIZE;

        }

        BlockColor[0] = new Paint();
        BlockColor[0].setStyle(Paint.Style.FILL);
        BlockColor[0].setColor(Color.WHITE);
        BlockColor[0].setStrokeWidth(0.1f); // 선의 두께를 5픽셀로 설정

        BlockColor[1] = new Paint();
        BlockColor[1].setStyle(Paint.Style.FILL);
        BlockColor[1].setColor(Color.RED);

        BlockColor[2] = new Paint();
        BlockColor[2].setStyle(Paint.Style.FILL);
        BlockColor[2].setColor(Color.CYAN);

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
        //Log.d("플레이어 위치" , "x "+Player.x+"y"+Player.y);

        for(int i = 0; i <MAPSIZEX*MAPSIZEY ; ++i){
            RectF Draw = new RectF();
            Draw.left = CellCenterX[i] -HALFCELLSIZE -Camera.Camera_x;
            Draw.right = CellCenterX[i] + HALFCELLSIZE-Camera.Camera_x;;
            Draw.top = CellCenterY[i] - HALFCELLSIZE-Camera.Camera_y;;
            Draw.bottom = CellCenterY[i] + HALFCELLSIZE-Camera.Camera_y;;

            canvas.drawRect(Draw, BlockColor[MAPInfo[i]]);
            canvas.drawRect(Draw, BlockColor[3]);

        }

    }
}
