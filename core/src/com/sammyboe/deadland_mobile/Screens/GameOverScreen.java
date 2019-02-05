package com.sammyboe.deadland_mobile.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.sammyboe.deadland_mobile.Assets.ElementAssets;
import com.sammyboe.deadland_mobile.GameMain;

public class GameOverScreen extends AbstractScreen{

    public Texture gameOver;

    public GameOverScreen(GameMain game) {
        super(game);
        gameOver = ElementAssets.manager.get(ElementAssets.gameOver);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(gameOver,25,0);
        game.batch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
