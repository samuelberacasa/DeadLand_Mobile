package com.sammyboe.deadland_mobile.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.sammyboe.deadland_mobile.Assets.ZombieAssets;
import com.sammyboe.deadland_mobile.Utils.GameVar;

public class Zombie extends Character {
    public enum State{appear, move, dying,dead};
    public Zombie.State state;
    public Texture zombieTexture;
    public TextureAtlas zombieAtlas;
    public Animation<TextureRegion> animation;
    private boolean followX = false, followY = false;

    public Zombie(World world, int x, int y){
        super(world, ZombieAssets.manager.get(ZombieAssets.zombieStandDown, Texture.class), x, y, Type.zombie);
        zombieTexture = this.getTexture();
        state = Zombie.State.appear;
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
                }else{
                    this.setDirection(Direction.left);
                }
            }else{
                if(heroY-(2/GameVar.PPM)<zombieY && heroY+(2/GameVar.PPM)>zombieY){
                    followX=true;
                    followY=false;
                }else if(heroY>zombieY){
                    this.setDirection(Direction.up);
                }else{
                    this.setDirection(Direction.down);
                }
            }
            this.moveCharacter();
        }
    }
}
