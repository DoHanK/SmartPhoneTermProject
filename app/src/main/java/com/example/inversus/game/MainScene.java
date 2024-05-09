package com.example.inversus.game;

import android.util.Log;
import android.view.MotionEvent;

import com.example.inversus.R;
import com.example.inversus.framework.objects.Button;
import com.example.inversus.framework.objects.JoyStick;
import com.example.inversus.framework.objects.VertScrollBackground;
import com.example.inversus.framework.objects.Score;
import com.example.inversus.framework.scene.Scene;
import com.example.inversus.framework.view.Metrics;
import com.example.inversus.game.Camera;
import com.example.inversus.game.Player;

public class MainScene extends Scene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final Player player;
    private final GameWorld gameWord;

    private  final Camera camera;

    Button AttackBtn = new Button(R.mipmap.aim, 16.0f, 7.0f, 2.0f, 2.0f,null);
    Score score; // package private
    private final JoyStick joyStick;
    public enum Layer {
        bg, enemy, bullet, player, ui,touch, controller, COUNT
    }
    public MainScene() {
        initLayers(Layer.COUNT);



        //인터페이스 영역//
        //컨트롤
        this.joyStick = new JoyStick(R.mipmap.joystick_bg, R.mipmap.joystick_thumb);
        joyStick.setRects(1.5f, 1.5f, 1.0f, 0.5f, 1.0f);
        add(Layer.controller, joyStick);


        this.player = new Player(joyStick);
        add(Layer.player,  this.player);


        this.camera = new Camera(this.player);
        add(Layer.controller,camera);



        //게임 로직 관련 추가
        add(Layer.controller, new EnemyGenerator());
        add(Layer.controller, new CollisionChecker(this));
        ///////////////////////////////



        this.gameWord = new GameWorld();
        add(Layer.bg, gameWord);




        this.score = new Score(R.mipmap.number_24x32, 8.5f, 0.5f, 0.6f);
        score.setScore(0);
        add(Layer.ui, score);


        add(Layer.touch, AttackBtn);
    }

    public void addScore(int amount) {
        score.add(amount);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
    }

    @Override

    public boolean onTouch(MotionEvent event) {
        float[] pts = Metrics.fromScreen(event.getX(),event.getY());
        if(pts[0]<8.f) {

            joyStick.onTouch(event);
        }

        if(pts[0]>8.0f){

            if(AttackBtn.onTouchEvent(event)){
                this.player.ShootBullet();
            }
        }


        return true;
    }
}
