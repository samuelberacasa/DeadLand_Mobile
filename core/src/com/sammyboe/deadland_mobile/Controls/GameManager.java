package com.sammyboe.deadland_mobile.Controls;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.physics.box2d.World;
import com.sammyboe.deadland_mobile.Characters.Character;
import com.sammyboe.deadland_mobile.Characters.Hero;
import com.sammyboe.deadland_mobile.GameMain;
import com.sammyboe.deadland_mobile.Screens.GameOverScreen;

public class GameManager implements InputProcessor {
    public World world;
    public Hero hero;
    public GameMain game;

    public GameManager(World world, Hero hero, GameMain game){
        this.world = world;
        this.hero = hero;
        this.game = game;
    }

    public void keyActionDown(int key) {
        switch (key) {
            case Input.Keys.ESCAPE:
                System.exit(0);
                break;
            case Input.Keys.DOWN:
                hero.setDirection(Direction.down);
                hero.setState(Hero.State.move);
                hero.moveCharacter();
                break;
            case Input.Keys.LEFT:
                hero.setDirection(Direction.left);
                hero.setState(Hero.State.move);
                hero.moveCharacter();
                break;
            case Input.Keys.RIGHT:
                hero.setDirection(Direction.right);
                hero.setState(Hero.State.move);
                hero.moveCharacter();
                break;
            case Input.Keys.UP:
                hero.setDirection(Direction.up);
                hero.setState(Hero.State.move);
                hero.moveCharacter();
                break;
            case Input.Keys.A:
                hero.setState(Hero.State.knife);
                break;
            case Input.Keys.F:
                hero.shoot();
                break;
            case Input.Keys.D:
                game.setScreen(new GameOverScreen(game));
                break;
        }
    }

    public void keyActionUp(int key) {
        switch (key) {
            case Input.Keys.DOWN:
            case Input.Keys.LEFT:
            case Input.Keys.RIGHT:
            case Input.Keys.UP:
            case Input.Keys.D:
                hero.setState(Hero.State.stand);
                hero.body.setLinearVelocity(0,0);
                break;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        keyActionDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        keyActionUp(keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
