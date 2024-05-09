package com.example.inversus.framework.interfaces;

import android.graphics.Canvas;
import android.graphics.RectF;

public interface IGameObject {


    public void update(float elapsedSeconds);
    public void draw(Canvas canvas);
}
