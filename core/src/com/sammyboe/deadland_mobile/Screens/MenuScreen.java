package com.sammyboe.deadland_mobile.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.sammyboe.deadland_mobile.Assets.ElementAssets;
import com.sammyboe.deadland_mobile.GameMain;
import com.sammyboe.deadland_mobile.Utils.GameVar;

public class MenuScreen extends AbstractScreen {
    public Texture newGame;

    public MenuScreen(GameMain game) {
        super(game);
        newGame = ElementAssets.manager.get(ElementAssets.newGame);
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
        game.batch.draw(newGame,25,-50);
        game.batch.end();
        if(Gdx.input.isTouched()){
            game.setScreen(new GameScreen(game));
            this.dispose();
        }
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
