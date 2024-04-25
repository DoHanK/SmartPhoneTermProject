package com.example.inversus.framework.objects;

import android.view.MotionEvent;
import com.example.inversus.framework.interfaces.ITouchable;
import com.example.inversus.framework.view.Metrics;


public class Button extends Sprite implements ITouchable {
    public enum Action {
        pressed, released,
    }
    public interface Callback {
        public boolean onTouch(Action action);
    }
    private static final String TAG = Button.class.getSimpleName();
    private final Callback callback;
    private boolean processedDown;
    public Button(int bitmapResId, float cx, float cy, float width, float height, Callback callback) {
        super(bitmapResId, cx, cy, width, height);
        this.callback = callback;
    }
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float[] pts = Metrics.fromScreen(e.getX(), e.getY());
        if (!dstRect.contains(pts[0], pts[1])) {
            return false;
        }


        return true;
    }
}


