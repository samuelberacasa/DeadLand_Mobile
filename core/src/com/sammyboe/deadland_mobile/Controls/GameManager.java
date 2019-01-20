package com.sammyboe.deadland_mobile.Controls;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.physics.box2d.World;
import com.sammyboe.deadland_mobile.Characters.Character;
import com.sammyboe.deadland_mobile.Characters.Hero;

public class GameManager implements InputProcessor {
    public World world;
    public Hero hero;

    public GameManager(World world, Hero hero){
        this.world = world;
        this.hero = hero;

    }

    public void keyActionDown(int key) {
        switch (key) {
            case Input.Keys.ESCAPE:
                System.exit(0);
                break;
            case Input.Keys.DOWN:
                hero.setDirection(Character.Direction.down);
                hero.setState(Hero.State.move);
                hero.body.setLinearVelocity(0,-5);
                break;
            case Input.Keys.LEFT:
                hero.setDirection(Character.Direction.left);
                hero.setState(Hero.State.move);
                hero.body.setLinearVelocity(-5,0);
                break;
            case Input.Keys.RIGHT:
                hero.setDirection(Character.Direction.right);
                hero.setState(Hero.State.move);
                hero.body.setLinearVelocity(5,0);
                break;
            case Input.Keys.UP:
                hero.setDirection(Character.Direction.up);
                hero.setState(Hero.State.move);
                hero.body.setLinearVelocity(0,5);
                break;
            case Input.Keys.D:
                hero.setState(Hero.State.knife);
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
