package com.example.inversus.app;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.example.inversus.framework.activity.GameActivity;
import com.example.inversus.game.MainScene;

public class InversusActivity extends GameActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        new MainScene().push();
    }

}
