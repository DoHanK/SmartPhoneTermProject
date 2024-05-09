package com.example.inversus.framework.util;

import android.graphics.RectF;
import android.util.Log;

import com.example.inversus.framework.interfaces.IBoxCollidable;

public class CollisionHelper {
    public static boolean collides(IBoxCollidable obj1, IBoxCollidable obj2) {
        RectF r1 = obj1.getCollisionRect();
        RectF r2 = obj2.getCollisionRect();

        if (r1.left > r2.right) return false;
        if (r1.top > r2.bottom) return false;
        if (r1.right < r2.left) return false;
        if (r1.bottom < r2.top) return false;
        Log.d("collision", "Collision !!");
        return true;
    }

    public static boolean collides(RectF obj1, RectF obj2) {
        RectF r1 = obj1;
        RectF r2 = obj2;

        if (r1.left > r2.right) return false;
        if (r1.top > r2.bottom) return false;
        if (r1.right < r2.left) return false;
        if (r1.bottom < r2.top) return false;
        Log.d("collision", "Collision !!");
        return true;
    }
}
