package com.sammyboe.deadland_mobile.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.sammyboe.deadland_mobile.Assets.ElementAssets;
import com.sammyboe.deadland_mobile.Characters.Hero;
import com.sammyboe.deadland_mobile.Characters.Zombie;
import com.sammyboe.deadland_mobile.Controls.GameManager;
import com.sammyboe.deadland_mobile.Elements.Bullet;
import com.sammyboe.deadland_mobile.GameMain;
import com.sammyboe.deadland_mobile.TiledMap.GameMap;
import com.sammyboe.deadland_mobile.Utils.GameVar;

import java.awt.event.KeyEvent;

public class GameScreen extends AbstractScreen implements ContactListener {
    public BitmapFont font;
    public Hero hero;
    public Array<Zombie> zombieArray;
    public World world;
    public GameManager manager;
    private Box2DDebugRenderer b2dr;
    public GameMap map;
    private int level;
    public Texture staminaUp;
    public Texture juggernaut;
    public Texture levelTexture;
    public Texture screenPlayer;
    public Texture letterM;
    public Texture letterR;
    public Texture letter7;
    public Texture bloodScreen;
    public float startLevelTimePassed;
    public float recoverTimePassed;
    private int lifePoints;

    public GameScreen(GameMain game) {
        super(game);
        b2dr = new Box2DDebugRenderer();
        font = new BitmapFont();
        world = new World(new Vector2(0,0),true);
        map = new GameMap();
        map.parseTileObjectLayer(world,map.tiledMap.getLayers().get("maplimits").getObjects());
        hero = new Hero(this.world, this.map.getWidth()/2, this.map.getHeight()/2);
        zombieArray = new Array<Zombie>();
        manager = new GameManager(world, hero, game);
        Gdx.input.setInputProcessor(manager);
        world.setContactListener(this);
        level = 1;
        staminaUp = ElementAssets.manager.get(ElementAssets.staminaUp);
        juggernaut = ElementAssets.manager.get(ElementAssets.juggernaut);
        levelTexture = ElementAssets.manager.get(ElementAssets.level1);
        screenPlayer = ElementAssets.manager.get(ElementAssets.screen);
        letterM = ElementAssets.manager.get(ElementAssets.letterM);
        letterR = ElementAssets.manager.get(ElementAssets.letterR);
        letter7 = ElementAssets.manager.get(ElementAssets.letter7);
        bloodScreen = ElementAssets.manager.get(ElementAssets.blood);
        startLevelTimePassed=0;
        recoverTimePassed=0;
        lifePoints = 5;
        fillZombies();
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
        map.mapRenderer.render();
        game.batch.begin();
        hero.timePassed+=delta;
        if(hero.weapon.bulletArray.size > 0){
            for(Bullet bullet : hero.weapon.bulletArray){
                game.batch.draw(bullet.texture, bullet.x, bullet.y);
            }
        }
        for(Zombie zombie:zombieArray){
            if(zombie.posY>hero.posY){
                switch (zombie.state){
                    case appear:
                        game.batch.draw(zombie.animation.getKeyFrame(zombie.timePassed,false),zombie.posX,zombie.posY);
                        zombie.timePassed+=delta;
                        if(zombie.animation.isAnimationFinished(zombie.timePassed)){
                            zombie.timePassed=0;
                            zombie.setState(Zombie.State.move);
                        }
                        break;
                    case move:
                        game.batch.draw(zombie.animation.getKeyFrame(zombie.timePassed,true),zombie.posX,zombie.posY);
                        zombie.timePassed+=delta;
                        break;
                    case stop:
                        game.batch.draw(zombie.animation.getKeyFrame(zombie.timePassed,true),zombie.posX,zombie.posY);
                        zombie.timePassed+=delta;
                        if(zombie.timePassed > 0.5){
                            zombie.setState(Zombie.State.move);
                        }
                        break;
                    case dying:
                        game.batch.draw(zombie.animation.getKeyFrame(zombie.timePassed,false),zombie.posX,zombie.posY);
                        zombie.timePassed+=delta;
                        if(zombie.animation.isAnimationFinished(zombie.timePassed)){
                            zombie.timePassed=0;
                            world.destroyBody(zombie.body);
                            zombieArray.removeValue(zombie,true);
                            if(zombieArray.size==0){
                                this.setNextLevel();
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        switch (hero.state){
            case stand:
                game.batch.draw(hero.texture,hero.posX,hero.posY);
                break;
            default:
                game.batch.draw(hero.animation.getKeyFrame(hero.timePassed,true),hero.posX,hero.posY);
        }
        for(Zombie zombie:zombieArray){
            if(zombie.posY<hero.posY){
                switch (zombie.state){
                    case appear:
                        game.batch.draw(zombie.animation.getKeyFrame(zombie.timePassed,false),zombie.posX,zombie.posY);
                        zombie.timePassed+=delta;
                        if(zombie.animation.isAnimationFinished(zombie.timePassed)){
                            zombie.timePassed=0;
                            zombie.setState(Zombie.State.move);
                        }
                        break;
                    case move:
                        game.batch.draw(zombie.animation.getKeyFrame(zombie.timePassed,true),zombie.posX,zombie.posY);
                        zombie.timePassed+=delta;
                        break;
                    case stop:
                        game.batch.draw(zombie.animation.getKeyFrame(zombie.timePassed,true),zombie.posX,zombie.posY);
                        zombie.timePassed+=delta;
                        if(zombie.timePassed > 0.5){
                            zombie.setState(Zombie.State.move);
                        }
                        break;
                    case dying:
                        game.batch.draw(zombie.animation.getKeyFrame(zombie.timePassed,false),zombie.posX,zombie.posY);
                        zombie.timePassed+=delta;
                        if(zombie.animation.isAnimationFinished(zombie.timePassed)){
                            zombie.timePassed=0;
                            world.destroyBody(zombie.body);
                            zombieArray.removeValue(zombie,true);
                            if(zombieArray.size==0){
                                this.setNextLevel();
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        game.batch.draw(screenPlayer, hero.posX-325, hero.posY+150);
        game.batch.draw(levelTexture, hero.posX-190, hero.posY+140);
        if (level > 1) {
            game.batch.draw(juggernaut, hero.posX-275, hero.posY+200);
        }else if(level > 2){
            game.batch.draw(staminaUp, hero.posX-235, hero.posY+200);
        }
        if(startLevelTimePassed>0&&startLevelTimePassed<4){
            startLevelTimePassed+=delta;
            if(level == 2){
                font.draw(game.batch, "NEW SKILL - HARDLINE (+ HEALTH)", hero.posX-320, hero.posY+140);
            }
            if(level == 3){
                font.draw(game.batch, "NEW SKILL - STAMINA UP (+ SPEED)", hero.posX-320, hero.posY+140);
            }
            if(startLevelTimePassed>3){
                startLevelTimePassed=0;
            }
        }
        game.batch.draw(letterM, hero.posX-265, hero.posY+150);
        game.batch.draw(letterR, hero.posX-240, hero.posY+150);
        game.batch.draw(letter7, hero.posX-215, hero.posY+150);
        if (lifePoints < 5){
            game.batch.draw(bloodScreen, hero.posX-330, hero.posY-190);
        }
        game.batch.end();
        //b2dr.render(world,camera.combined.scl(GameVar.PPM));

        world.step(1/30f,6,2);
        if(lifePoints<5){
            recoverTimePassed+=delta;
            if(recoverTimePassed>3){
                lifePoints=5;
                recoverTimePassed=0;
            }
        }
    }

    private void setNextLevel() {
        this.level++;
        this.startLevelTimePassed+=0.00000001;
        if(this.level == 2){
            levelTexture = ElementAssets.manager.get(ElementAssets.level2);
        }if(this.level == 3){
            levelTexture = ElementAssets.manager.get(ElementAssets.level3);
            hero.speed=1;
        }
        this.fillZombies();
    }

    public void updateGame(float delta){
        hero.updatePosition();
        hero.updateBullets();
        this.updateZombies();
        this.checkCollision();
        cameraUpdate(delta);
        map.mapRenderer.setView(camera);
        game.batch.setProjectionMatrix(camera.combined);
    }

    public void checkCollision(){
        if(hero.weapon.bulletArray.size > 0){
            for(Bullet bullet : hero.weapon.bulletArray){
                for(Zombie zombie:zombieArray){
                    if(zombie.inDistanceBullet(this.hero)&&zombie.rectangleBody!=null){
                        if(bullet.overlaps(zombie.rectangleBody)){
                            zombie.takeDamage(hero.weapon.damage);
                        }
                    }
                }
            }
        }
    }

    public void fillZombies(){
        if(level < 3){
            for(int i = 0; i< level*4; i++){
                int posX = (int) (Math.random()*this.map.getWidth()/2)+this.map.getWidth()/4;
                int posY = (int) (Math.random()*this.map.getHeight()/2)+this.map.getHeight()/4;
                zombieArray.add(new Zombie(this.world, posX, posY, i));
            }
        }else{
            for(int i = 0; i< level*10; i++){
                int posX = (int) (Math.random()*this.map.getWidth()-50)+50;
                int posY = (int) (Math.random()*this.map.getHeight()-50)+50;
                zombieArray.add(new Zombie(this.world, posX, posY, i));
            }
        }
    }

    public void updateZombies(){
        for(Zombie zombie:zombieArray){
            zombie.followHero(hero);
            zombie.updatePosition();
            zombie.updateRectangle();
        }
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
        world.dispose();
        b2dr.dispose();
        map.dispose();
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture hero, zombie;

        if(contact.getFixtureA().getUserData() == "hero"){
            hero = contact.getFixtureA();
            zombie = contact.getFixtureB();
        }else{
            zombie = contact.getFixtureA();
            hero = contact.getFixtureB();
        }
        for(Zombie zombieObject : zombieArray){
            if(zombieObject.body.getFixtureList().first().getUserData() == zombie.getUserData()){
                if(zombieObject.state!= Zombie.State.dying){
                    zombieObject.setState(Zombie.State.stop);
                    this.reduceLifePoints();
                }
            }
        }
    }

    private void reduceLifePoints() {
        this.lifePoints--;
        this.recoverTimePassed=0;
        if(lifePoints<1){
            game.setScreen(new GameOverScreen(game));
        }
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
