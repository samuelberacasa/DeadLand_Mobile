package com.sammyboe.deadland_mobile.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.sammyboe.deadland_mobile.Assets.ZombieAssets;
import com.sammyboe.deadland_mobile.Controls.Direction;
import com.sammyboe.deadland_mobile.Utils.GameVar;

public class Zombie extends Character {
    public enum State{appear, move, dying, stop};
    public Zombie.State state;
    public TextureAtlas zombieAtlas;
    public Animation<TextureRegion> animation;
    public Rectangle rectangleBody;
    private boolean followX = false, followY = false;
    public int lifePoints;

    public Zombie(World world, int x, int y, int id){
        super(world, ZombieAssets.manager.get(ZombieAssets.zombieStandDown, Texture.class), x, y, Type.zombie, id);
        rectangleBody = new Rectangle();
        rectangleBody.x = x;
        rectangleBody.y = y;
        rectangleBody.width = this.texture.getWidth();
        rectangleBody.height = this.texture.getHeight();
        state = Zombie.State.appear;
        lifePoints = 50;
        spriteSelection();
    }

    public void setState(Zombie.State e){
        if(this.state != e){
            this.state = e;
            this.spriteSelection();
        }
    }

    public void spriteSelection(){
        switch (state){
            case appear:
                zombieAtlas = ZombieAssets.manager.get(ZombieAssets.zombieAppears, TextureAtlas.class);
                animation = new Animation(1/5f, zombieAtlas.getRegions());
                break;
            case stop:
                this.timePassed = 0;
            case move:
                switch(direction){
                    case down:
                        zombieAtlas = ZombieAssets.manager.get(ZombieAssets.zombieMovingDown, TextureAtlas.class);
                        break;
                    case left:
                        zombieAtlas = ZombieAssets.manager.get(ZombieAssets.zombieMovingLeft, TextureAtlas.class);
                        break;
                    case right:
                        zombieAtlas = ZombieAssets.manager.get(ZombieAssets.zombieMovingRight, TextureAtlas.class);
                        break;
                    case up:
                        zombieAtlas = ZombieAssets.manager.get(ZombieAssets.zombieMovingUp, TextureAtlas.class);
                        break;
                }
                animation = new Animation(1/5f, zombieAtlas.getRegions());
                break;
            case dying:
                zombieAtlas = ZombieAssets.manager.get(ZombieAssets.zombieDying, TextureAtlas.class);
                animation = new Animation(1/5f, zombieAtlas.getRegions());
                break;
            default:
                break;
        }
    }

    public void updateRectangle(){
        if(rectangleBody!=null){
            rectangleBody.x = (int)posX;
            rectangleBody.y = (int)posY;
        }
    }

    public void followHero(Hero hero){
        if(state == State.move){
            float heroX = hero.body.getPosition().x;
            float heroY = hero.body.getPosition().y;
            float zombieX =this.body.getPosition().x;
            float zombieY = this.body.getPosition().y;
            if(!followX&&!followY){
                float deltaX = Math.abs(zombieX - heroX);
                float deltaY = Math.abs(zombieY - heroY);
                if(deltaX>=deltaY){
                    followX = true;
                    followY = false;
                }else{
                    followX = false;
                    followY = true;
                }
            }
            if(followX){
                if(heroX-(3/GameVar.PPM)<zombieX && heroX+(3/GameVar.PPM)>zombieX){
                    followX=false;
                    followY=true;
                }else if(heroX>zombieX){
                    this.setDirection(Direction.right);
                    this.spriteSelection();
                }else{
                    this.setDirection(Direction.left);
                    this.spriteSelection();
                }
            }else{
                if(heroY-(2/GameVar.PPM)<zombieY && heroY+(2/GameVar.PPM)>zombieY){
                    followX=true;
                    followY=false;
                }else if(heroY>zombieY){
                    this.setDirection(Direction.up);
                    this.spriteSelection();
                }else{
                    this.setDirection(Direction.down);
                    this.spriteSelection();
                }
            }
            this.moveCharacter();
        }else{
            body.setLinearVelocity(0,0);
        }
    }

    public boolean inDistanceBullet(Hero hero){
        return Math.sqrt((hero.posY - this.posY) * (hero.posY - this.posY) + (hero.posX - this.posX) * (hero.posX - this.posX)) < hero.weapon.range;
    }

    public void takeDamage(int damage){
        lifePoints-=damage;
        if(lifePoints<0){
            this.timePassed = 0;
            this.body.setLinearVelocity(0,0);
            this.rectangleBody=null;
            this.setState(State.dying);
        }
    }

    public void moveCharacter(){
        if(this.state == State.move) {
            switch (direction) {
                case down:
                    body.setLinearVelocity(0, -1.5f);
                    break;
                case left:
                    body.setLinearVelocity(-1.5f, 0);
                    break;
                case right:
                    body.setLinearVelocity(1.5f, 0);
                    break;
                case up:
                    body.setLinearVelocity(0, 1.5f);
                    break;
            }
        }
    }
}
