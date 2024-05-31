package com.example.inversus.game;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.MotionEvent;

import com.example.inversus.R;
import com.example.inversus.framework.activity.GameActivity;
import com.example.inversus.framework.objects.Button;
import com.example.inversus.framework.objects.Sprite;
import com.example.inversus.framework.scene.Scene;
import com.example.inversus.framework.view.Metrics;

public class PausedScene extends Scene {
    private static final String TAG = PausedScene.class.getSimpleName();
    public enum Layer {
        bg, title, touch, COUNT
    }

    Button ResumeBtn;
    Button ExitBtn;

    private float angle;
    public PausedScene() {
        initLayers(Layer.COUNT);
        float w = Metrics.width, h = Metrics.height;
        float cx = w / 2, cy = h / 2;

        add(Layer.bg, new Sprite(R.mipmap.graybg, 0, 0, 40.00f, 18.f));
        add(Layer.bg, new Sprite(R.mipmap.logo, cx, cy, 12.00f, 6.75f));


        ResumeBtn =new Button(R.mipmap.restart, 8.f, 7.0f, 2.0f, 2.0f, null);

        ExitBtn = new Button(R.mipmap.exit, 11.f , 7.0f , 2.0f, 2.0f, null);


        add(PausedScene.Layer.touch, ExitBtn);
        add(PausedScene.Layer.touch, ResumeBtn);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);

    }

    @Override
    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }

    @Override
    public boolean isTransparent() {
        return true;
    }

    @Override
    public boolean onTouch(MotionEvent event) {



            if (ResumeBtn.onTouchEvent(event)) {
                pop();
            }
            if (ExitBtn.onTouchEvent(event)) {
                new AlertDialog.Builder(GameActivity.activity)
                        .setTitle("Confirm")
                        .setMessage("Do you really want to exit the game?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finishActivity();
                            }
                        })
                        .create()
                        .show();
                return false;
            }

            return true;


    }

    @Override
    public  String getTAG() {
        return TAG;
    }
}