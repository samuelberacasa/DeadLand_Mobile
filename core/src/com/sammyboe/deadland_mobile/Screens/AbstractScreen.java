package com.sammyboe.deadland_mobile.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.sammyboe.deadland_mobile.GameMain;
import com.sammyboe.deadland_mobile.Utils.GameVar;

public abstract class AbstractScreen implements Screen {
    final GameMain game;
    OrthographicCamera camera;

    public AbstractScreen(final GameMain game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,
                GameVar.GAME_WIDTH,
                GameVar.GAME_HEIGHT);
    }

    @Override
    public void resize(int width, int height) {

    }


}
