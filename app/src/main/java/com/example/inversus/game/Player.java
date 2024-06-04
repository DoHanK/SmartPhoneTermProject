package com.example.inversus.game;

import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.provider.Contacts;
import android.util.Log;

import com.example.inversus.framework.interfaces.IBoxCollidable;
import com.example.inversus.framework.interfaces.IGameObject;
import com.example.inversus.framework.objects.JoyStick;
import com.example.inversus.framework.scene.Scene;
import com.example.inversus.framework.util.CollisionHelper;
import com.example.inversus.framework.view.Metrics;

public class Player implements IGameObject , IBoxCollidable {

    private static final float SPEED = 8.0f;
    private static final float PLAYERSIZE = 0.8f;
    private static final int BULLETCOUNT = 6;
    private static final float BULLETOFFSET = 0.5f;
    private static final float BULLETSIZE = 0.15f;
    private static final float SHOOTCOOLTIME = 0.20f;
    private static final float RELOADTIME = 1.0f;
    private static final float TRAILERCOOLTIME = 0.001f;
    private static final float TRAILERSIZE = 0.92f;
    private static final int SHADOWCOUNT = 20;


    // ph UI
    private static final float PLAYERHPSIZE= 1.3f;
    private static final float HPBULLETSIZE = 0.25f;
    private static final float HPBULLETOFFSET = 0.75f;
    private static final float HPXOFFSET= 3.65f;


    private static final int PLAYERHP = 3;
    RectF[] HPDrawRect= new RectF[PLAYERHP];
    float HPstratX = 15.f;
    float HPstratY = 1.6f;
    float[] HPbulletPosX = new float[BULLETCOUNT*PLAYERHP];
    float[] HPbulletPosY = new float[BULLETCOUNT*PLAYERHP];
    RectF[] HPdrawRBullet = new RectF[BULLETCOUNT*PLAYERHP];

    public static RectF collisionRect = new RectF();
    private final JoyStick joyStick;
    static float x ,  y ,dx, dy;
    private float angle;
    RectF DrawRect;
    RectF[] DrawShadowRect = new RectF[SHADOWCOUNT];
    float[] ShadowX = new float[SHADOWCOUNT];
    float[] ShadowY =new float[SHADOWCOUNT];
    Paint[] ShadowColor = new Paint[SHADOWCOUNT];
    Paint PlayerBodyColor;
    Paint[] BulletColor = new Paint[2];
    float[] bulletPosX = new float[BULLETCOUNT];
    float[] bulletPosY = new float[BULLETCOUNT];
    RectF[] drawRBullet = new RectF[BULLETCOUNT];

    int UseableBullet = BULLETCOUNT;
    float shootTime = 0 ;

    float ReloadTime = 0;
    float trailTime = 0;

    float prex =0;
    float prey =0;

    static int HP = 3;
    Player(JoyStick joyStick){
        HP =3;
        this.joyStick = joyStick;

        PlayerBodyColor = new Paint();
     PlayerBodyColor.setColor(Color.BLACK);
        BulletColor[0] = new Paint();
        BulletColor[1] = new Paint();
        BulletColor[0].setColor(Color.RED);
        BulletColor[1].setColor(Color.WHITE);

     DrawRect = new RectF();
        //UIHP Init
        for(int i = 0  ; i  <PLAYERHP ; ++i) {
            HPDrawRect[i] = new RectF();
        }

        for(int i = 0 ; i < BULLETCOUNT*PLAYERHP ;++i){
            HPbulletPosX[i] = HPBULLETOFFSET * (float)(Math.cos(toRadians(360.f/BULLETCOUNT)*i + 10.f*(float)(i/BULLETCOUNT)));
            HPbulletPosY[i] = HPBULLETOFFSET * (float)(Math.sin(toRadians(360.f/BULLETCOUNT)*i + 10.f*(float)(i/BULLETCOUNT)));


            HPdrawRBullet[i] = new RectF();
        }

     x = 0;
     y = 0;

        UpdateRect();

     for(int i = 0 ; i < BULLETCOUNT ;++i){

         bulletPosX[i] = BULLETOFFSET * (float)(Math.cos(toRadians(360.f/BULLETCOUNT)*i));
         bulletPosY[i] = BULLETOFFSET * (float)(Math.sin(toRadians(360.f/BULLETCOUNT)*i));
         drawRBullet[i] = new RectF();
     }


     for(int i = 0; i <SHADOWCOUNT ; i++){

         DrawShadowRect[i] = new RectF();
         ShadowColor[i] =new Paint();
         ShadowColor[i].setColor( Color.argb(((SHADOWCOUNT-i)*255/SHADOWCOUNT),(i*255/SHADOWCOUNT),(i*255/SHADOWCOUNT),(i*255/SHADOWCOUNT)));
     }



    }
    public void update(float elapsedSeconds){
        shootTime+=elapsedSeconds;
        ReloadTime+=elapsedSeconds;
        trailTime+=elapsedSeconds;


        if(ReloadTime>RELOADTIME){
            ReloadTime =0;
            UseableBullet++;
            if(UseableBullet>BULLETCOUNT){
                UseableBullet = BULLETCOUNT;
            }
        }





        if (joyStick.power > 0) {
            float distance = SPEED * joyStick.power;
            dx = (float) (distance * Math.cos(joyStick.angle_radian));
            dy = (float) (distance * Math.sin(joyStick.angle_radian));
            angle = (float) Math.toDegrees(joyStick.angle_radian);

            float timedDx = dx * elapsedSeconds;
            float timedDy = dy * elapsedSeconds;
            prex =x;
            prey =y;
            x += timedDx;
            y += timedDy;

        } else {
            dx = dy = 0;
        }


        UpdateRect();

            for(int i = 0; i <GameWorld.MAPSIZEX*GameWorld.MAPSIZEY ; ++i) {

                if (GameWorld.MAPInfo[i] != 0) {
                    if (CollisionHelper.collides(GameWorld.collisionRect[i], collisionRect)) {
                        x = prex;
                        y = prey;
                    }

                }


            }








        //왼쪽끝에 다다랐을때
        if(x -PLAYERSIZE < -GameWorld.CELLSIZE * ((float)(GameWorld.MAPSIZEX)/2) ) {
            x   = -GameWorld.CELLSIZE * ((float)(GameWorld.MAPSIZEX)/2)+PLAYERSIZE;
        }
        //오른쪽
        if(x +PLAYERSIZE > GameWorld.CELLSIZE * ((float)(GameWorld.MAPSIZEX)/2) ) {
            x = GameWorld.CELLSIZE * ((float)(GameWorld.MAPSIZEX)/2)  -PLAYERSIZE;
        }
        //상
        if(y  -PLAYERSIZE< -GameWorld.CELLSIZE * ((float)(GameWorld.MAPSIZEY)/2) ) {
            y  = -GameWorld.CELLSIZE * ((float)(GameWorld.MAPSIZEY)/2) +PLAYERSIZE;
        }
        //하
        if(y + PLAYERSIZE > GameWorld.CELLSIZE * ((float)(GameWorld.MAPSIZEY)/2) ) {
            y  = GameWorld.CELLSIZE * ((float)(GameWorld.MAPSIZEY)/2) -PLAYERSIZE;
        }

        RotateBullet(elapsedSeconds);
    }

    public void RotateBullet(float elapsedSeconds){
        for(int i = 0 ; i < BULLETCOUNT ;++i){
            float X = bulletPosX[i];
            float Y = bulletPosY[i];
            bulletPosX[i] =  X * (float)Math.cos(elapsedSeconds) - Y * (float)Math.sin(elapsedSeconds);
            bulletPosY[i] = X * (float)Math.sin(elapsedSeconds) + Y * (float)Math.cos(elapsedSeconds);

            drawRBullet[i].left  = bulletPosX[i]- BULLETSIZE +x-Camera.Camera_x;
            drawRBullet[i].top  = bulletPosY[i]+ BULLETSIZE+y-Camera.Camera_y;
            drawRBullet[i].right  = bulletPosX[i]+ BULLETSIZE+x -Camera.Camera_x;
            drawRBullet[i].bottom = bulletPosY[i]- BULLETSIZE+y -Camera.Camera_y;
        }


        //HP UI
        for(int i = 0 ; i < BULLETCOUNT*PLAYERHP ;++i){
            float X = HPbulletPosX[i];
            float Y = HPbulletPosY[i];
            HPbulletPosX[i] =  X * (float)Math.cos(elapsedSeconds) - Y * (float)Math.sin(elapsedSeconds);
            HPbulletPosY[i] = X * (float)Math.sin(elapsedSeconds) + Y * (float)Math.cos(elapsedSeconds);

            HPdrawRBullet[i].left  = HPbulletPosX[i]- HPBULLETSIZE +HPstratX+HPXOFFSET*(i/BULLETCOUNT) ;
            HPdrawRBullet[i].top  = HPbulletPosY[i]+ HPBULLETSIZE +HPstratY;
            HPdrawRBullet[i].right  = HPbulletPosX[i]+ HPBULLETSIZE +HPstratX+HPXOFFSET*(i/BULLETCOUNT);
            HPdrawRBullet[i].bottom = HPbulletPosY[i]- HPBULLETSIZE+HPstratY;


        }


    }
    public void UpdateRect(){
        //쉐도우 업데이트
        if(trailTime > TRAILERCOOLTIME){
            for(int i = SHADOWCOUNT-1 ; i > 0 ; --i) {
                ShadowX[i] = ShadowX[i-1];
                ShadowY[i] = ShadowY[i-1];

                DrawShadowRect[i].bottom =  ShadowY[i]+(float)Math.pow(TRAILERSIZE,i)-Camera.Camera_y;
                DrawShadowRect[i].top =  ShadowY[i]-(float)Math.pow(TRAILERSIZE,i)-Camera.Camera_y;
                DrawShadowRect[i].left =  ShadowX[i]-(float)Math.pow(TRAILERSIZE,i)-Camera.Camera_x;
                DrawShadowRect[i].right =  ShadowX[i]+(float)Math.pow(TRAILERSIZE,i)-Camera.Camera_x;


            }
            ShadowX[0] = x;
            ShadowY[0] = y;
            DrawShadowRect[0].bottom =  ShadowY[0]+(float)Math.pow(TRAILERSIZE,1)-Camera.Camera_y;
            DrawShadowRect[0].top =  ShadowY[0]+(float)Math.pow(TRAILERSIZE,1)-Camera.Camera_y;
            DrawShadowRect[0].left =  ShadowX[0]+(float)Math.pow(TRAILERSIZE,1)-Camera.Camera_x;
            DrawShadowRect[0].right =  ShadowX[0]+(float)Math.pow(TRAILERSIZE,1)-Camera.Camera_x;


            trailTime =0;
        }


        //HP 본체 드로우

        for(int i  = 0 ; i < PLAYERHP ; ++i) {
            HPDrawRect[i].top = HPstratY - PLAYERHPSIZE;
            HPDrawRect[i].bottom = HPstratY + PLAYERHPSIZE;
            HPDrawRect[i].left = HPstratX - PLAYERHPSIZE+HPXOFFSET*i;
            HPDrawRect[i].right = HPstratX + PLAYERHPSIZE+HPXOFFSET*i;
        }
        DrawRect.top = y - PLAYERSIZE -Camera.Camera_y;
        DrawRect.bottom =  y + PLAYERSIZE - Camera.Camera_y;
        DrawRect.left = x - PLAYERSIZE -Camera.Camera_x;
        DrawRect.right = x + PLAYERSIZE -Camera.Camera_x;
        //유효총알 검사.

        // Collision Update
        collisionRect.top = DrawRect.top;
        collisionRect.bottom =  DrawRect.bottom ;
        collisionRect.left = DrawRect.left;
        collisionRect.right =DrawRect.right ;

    }
    public void draw(Canvas canvas){
        for(int i = SHADOWCOUNT-1 ; i > -1 ; --i) {

            canvas.drawRoundRect(DrawShadowRect[i],0.3f,0.3f,ShadowColor[i]);
        }
    canvas.drawRoundRect(DrawRect,0.3f,0.3f,PlayerBodyColor);

        for(int i = 0 ; i <HP ; ++i) {
            canvas.drawRoundRect(HPDrawRect[i], 0.4f, 0.4f, PlayerBodyColor);
        }


        for(int i = 0 ; i < BULLETCOUNT ;++i){
            if(UseableBullet>i) {
                canvas.drawOval(drawRBullet[i], BulletColor[0]);
            }
            else{
                canvas.drawOval(drawRBullet[i], BulletColor[1]);

            }
        }


        for(int i = 0 ; i < BULLETCOUNT*HP ;++i){
            canvas.drawOval(HPdrawRBullet[i], BulletColor[0]);


        }

    }

    public void ShootBullet(){
        if(shootTime >SHOOTCOOLTIME&& (UseableBullet>0)) {
            Bullet bullet = Bullet.get(x, y, angle);
            Scene.top().add(MainScene.Layer.bullet, bullet);

            this.dx = (float)(Math.cos((float)Math.toRadians(angle)));
            this.dy = (float)(Math.sin((float)Math.toRadians(angle)));
            Camera.Camera_x +=-dx;
            Camera.Camera_y +=-dy;
            UseableBullet--;
            shootTime =0 ;
        }

    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }
}
