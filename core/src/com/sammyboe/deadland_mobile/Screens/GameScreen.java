package com.sammyboe.deadland_mobile.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.sammyboe.deadland_mobile.Characters.Character;
import com.sammyboe.deadland_mobile.Characters.Hero;
import com.sammyboe.deadland_mobile.Characters.Zombie;
import com.sammyboe.deadland_mobile.Controls.GameManager;
import com.sammyboe.deadland_mobile.GameMain;
import com.sammyboe.deadland_mobile.Utils.GameVar;

import java.awt.event.KeyEvent;

public class GameScreen extends AbstractScreen implements ContactListener {
    public BitmapFont font;
    public Hero hero;
    public Zombie zombie;
    public World world;
    public GameManager manager;
    private Box2DDebugRenderer b2dr;
    private float timePassed=0;
    private float timePassedHero = 0;
    private float timePassedZombie = 0;

    public GameScreen(GameMain game) {
        super(game);
        b2dr = new Box2DDebugRenderer();
        font = new BitmapFont();
        world = new World(new Vector2(0,0),true);
        hero = new Hero(this.world, -50, -50);
        zombie = new Zombie(this.world, 100, 100);
        manager = new GameManager(world, hero);
        Gdx.input.setInputProcessor(manager);
        world.setContactListener(this);
    }

    @Override
    public void show() {
        camera.setToOrtho(false,
                GameVar.GAME_WIDTH/GameVar.GAME_SCALE,
                GameVar.GAME_HEIGHT/GameVar.GAME_SCALE);
    }

    @Override
    public void render(float delta) {
        this.updateGame(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        timePassed+=delta;
        timePassedHero+=delta;
        switch (hero.state){
            case stand:
                game.batch.draw(hero.heroTexture,hero.getX(),hero.getY());
                break;
            default:
                game.batch.draw(hero.animation.getKeyFrame(timePassed,true),hero.getX(),hero.getY());
        }
        switch (zombie.state){
            case appear:
                game.batch.draw(zombie.animation.getKeyFrame(timePassedZombie,false),zombie.getX(),zombie.getY());
                timePassedZombie+=delta;
                if(zombie.animation.isAnimationFinished(timePassedZombie)){
                    timePassedZombie=0;
                    zombie.setState(Zombie.State.move);
                }
                break;
            case move:
                game.batch.draw(zombie.animation.getKeyFrame(timePassed,true),zombie.getX(),zombie.getY());
                break;
            case dying:
                game.batch.draw(zombie.animation.getKeyFrame(timePassedZombie,false),zombie.getX(),zombie.getY());
                timePassedZombie+=delta;
                if(zombie.animation.isAnimationFinished(timePassedZombie)){
                    timePassedZombie=0;
                    zombie.setState(Zombie.State.dead);
                }
                break;
            default:
                break;
        }
        game.batch.end();
        b2dr.render(world,camera.combined.scl(GameVar.PPM));

        world.step(1/30f,6,2);
    }

    public void updateGame(float delta){
        hero.updateCharacter();
        zombie.followHero(hero);
        zombie.updateCharacter();
        cameraUpdate(delta);
        game.batch.setProjectionMatrix(camera.combined);
    }

    public void cameraUpdate(float delta){
        Vector3 position = camera.position;
        position.x = hero.body.getPosition().x * GameVar.PPM;
        position.y = hero.body.getPosition().y * GameVar.PPM;
        camera.position.set(position);
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false,
                GameVar.GAME_WIDTH/GameVar.GAME_SCALE,
                GameVar.GAME_HEIGHT/GameVar.GAME_SCALE);
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
        font.dispose();
        hero.getTexture().dispose();
        world.dispose();
        b2dr.dispose();
        hero.heroAtlas.dispose();
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture bodyA, bodyB;

        if(contact.getFixtureA().getUserData() == "hero"){
            bodyA = contact.getFixtureA();
            bodyB = contact.getFixtureB();
        }else{
            bodyB = contact.getFixtureA();
            bodyA = contact.getFixtureB();
        }

        System.out.println(bodyA.getUserData());
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
