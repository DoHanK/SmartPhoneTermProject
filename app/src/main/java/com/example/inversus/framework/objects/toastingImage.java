package com.example.inversus.framework.objects;

import static androidx.core.math.MathUtils.clamp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import com.example.inversus.framework.interfaces.IRecyclable;
import com.example.inversus.framework.res.BitmapPool;
import com.example.inversus.framework.scene.RecycleBin;
import com.example.inversus.framework.scene.Scene;
import com.example.inversus.framework.view.Metrics;
import com.example.inversus.game.Effect;
import com.example.inversus.game.MainScene;

import kotlin.random.Random;


public class toastingImage extends Sprite implements IRecyclable {
    private float ElapsedTime = 0.0f;
    public float SettingTime ;
    Paint paint = new Paint();
    @Override
    public void onRecycle() {

    }

    public enum Action {
        pressed, released,
    }
    public interface Callback {
        public boolean onTouch(Action action);
    }
    private static final String TAG = Button.class.getSimpleName();
    private boolean processedDown;
    public toastingImage(int bitmapResId, float cx, float cy, float width, float height ,float SetTime) {
        super(bitmapResId, cx, cy, width, height);
        SettingTime = SetTime;

    }

    public static toastingImage get(int bitmapResId, float cx, float cy, float width, float height ,float SetTime){
        toastingImage TI = (toastingImage) RecycleBin.get(toastingImage.class);
        if (TI != null) {

            TI.SettingTime = SetTime;
            TI.ElapsedTime =0.0f;
            if (bitmapResId != 0) {
                TI.bitmap = BitmapPool.get(bitmapResId);
            }
            TI.setPosition(cx, cy, width, height);

            return TI;


        }

        return new toastingImage( bitmapResId,  cx,  cy,  width,  height , SetTime);
    }

    @Override
    public void update(float elapsedSeconds) {
        ElapsedTime+=elapsedSeconds;
        float alpha =255.f - 255.f*( ElapsedTime/SettingTime);
        int ialpha= (int)alpha;
        if(ialpha%2==0) {
            paint.setColor(Color.argb(ialpha
                    , 255
                    , 255
                    , 255));
        }



        if(ElapsedTime >SettingTime) {

            Scene scene = MainScene.top();
            scene.remove(MainScene.Layer.screeneffect,this);
            RecycleBin.collect((IRecyclable) this);
        }

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null , dstRect, paint);
    }
}