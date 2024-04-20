package com.example.inversus.game;

import android.graphics.RectF;

import com.example.inversus.R;
import com.example.inversus.framework.interfaces.IBoxCollidable;
import com.example.inversus.framework.interfaces.IRecyclable;
import com.example.inversus.framework.objects.Sprite;
import com.example.inversus.framework.scene.RecycleBin;
import com.example.inversus.framework.scene.Scene;

public class Bullet extends Sprite implements IBoxCollidable, IRecyclable {
    private static final float BULLET_WIDTH = 0.68f;
    private static final float BULLET_HEIGHT = BULLET_WIDTH * 40 / 28;
    private static final float SPEED = 20.0f;

    private Bullet(float x, float y) {
        super(R.mipmap.laser_1);
        setPosition(x, y, BULLET_WIDTH, BULLET_HEIGHT);
        dy = -SPEED;
    }
    public static Bullet get(float x, float y) {
        Bullet bullet = (Bullet) RecycleBin.get(Bullet.class);
        if (bullet != null) {
            bullet.setPosition(x, y, BULLET_WIDTH, BULLET_HEIGHT);
            return bullet;
        }
        return new Bullet(x, y);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
        if (dstRect.bottom < 0) {
            Scene.top().remove(MainScene.Layer.bullet, this);
        }
    }

    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }

    @Override
    public void onRecycle() {

    }
}
