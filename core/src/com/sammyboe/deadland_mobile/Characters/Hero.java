package com.sammyboe.deadland_mobile.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.sammyboe.deadland_mobile.Assets.HeroAssets;
import com.sammyboe.deadland_mobile.Elements.Bullet;
import com.sammyboe.deadland_mobile.Elements.Weapon;
import com.sammyboe.deadland_mobile.Utils.GameVar;

public class Hero extends Character {
    public enum State{stand,move,knife,shoot};
    public State state;
    public TextureAtlas heroAtlas;
    public Animation<TextureRegion> animation;
    public Bullet bullet;
    public Weapon weapon;
    public int speed=0;

    public Hero(World world, int x, int y){
        super(world, HeroAssets.manager.get(HeroAssets.heroTextureStandDown, Texture.class),x, y, Type.hero,-1);
        state = State.stand;
        weapon = new Weapon("MR7", 10, 200, 8, 80);
        this.body.setUserData("hero");
        spriteSelection();
    }

    public void setState(State e){
        if(this.state != e){
            this.state = e;
            this.spriteSelection();
        }
    }

    public void spriteSelection(){
        switch (state){
            case knife:
                switch(direction){
                    case down:
                        heroAtlas = HeroAssets.manager.get(HeroAssets.heroAtlasKnifeDown, TextureAtlas.class);
                        break;
                    case left:
                        heroAtlas = HeroAssets.manager.get(HeroAssets.heroAtlasKnifeLeft, TextureAtlas.class);
                        break;
                    case right:
                        heroAtlas = HeroAssets.manager.get(HeroAssets.heroAtlasKnifeRight, TextureAtlas.class);
                        break;
                    case up:
                        heroAtlas = HeroAssets.manager.get(HeroAssets.heroAtlasKnifeUp, TextureAtlas.class);
                        break;
                }
                animation = new Animation(1/8f, heroAtlas.getRegions());
                break;
            case move:
                switch(direction){
                    case down:
                        heroAtlas = HeroAssets.manager.get(HeroAssets.heroAtlasMovingDown, TextureAtlas.class);
                        break;
                    case left:
                        heroAtlas = HeroAssets.manager.get(HeroAssets.heroAtlasMovingLeft, TextureAtlas.class);
                        break;
                    case right:
                        heroAtlas = HeroAssets.manager.get(HeroAssets.heroAtlasMovingRight, TextureAtlas.class);
                        break;
                    case up:
                        heroAtlas = HeroAssets.manager.get(HeroAssets.heroAtlasMovingUp, TextureAtlas.class);
                        break;
                }
                animation = new Animation(1/10f, heroAtlas.getRegions());
                break;
            case stand:
                switch(direction){
                    case down:
                        texture = HeroAssets.manager.get(HeroAssets.heroTextureStandDown, Texture.class);
                        break;
                    case left:
                        texture = HeroAssets.manager.get(HeroAssets.heroTextureStandLeft, Texture.class);
                        break;
                    case right:
                        texture = HeroAssets.manager.get(HeroAssets.heroTextureStandRight, Texture.class);
                        break;
                    case up:
                        texture = HeroAssets.manager.get(HeroAssets.heroTextureStandUp, Texture.class);
                        break;
                }
                break;
        }
    }

    public void shoot(){
        switch (direction){
            case up:
                weapon.shoot(this.body.getPosition().x*GameVar.PPM,
                        this.body.getPosition().y*GameVar.PPM+this.texture.getHeight()/2,
                        direction);
                break;
            case left:
                weapon.shoot(this.body.getPosition().x*GameVar.PPM-this.texture.getWidth()/2,
                        this.body.getPosition().y*GameVar.PPM+this.texture.getHeight()/3,
                        direction);
                break;
            case right:
                weapon.shoot(this.body.getPosition().x*GameVar.PPM+this.texture.getWidth()/2,
                        this.body.getPosition().y*GameVar.PPM+this.texture.getHeight()/3,
                        direction);
                break;
            case down:
                weapon.shoot(this.body.getPosition().x*GameVar.PPM,
                        this.body.getPosition().y*GameVar.PPM,
                        direction);
                break;
        }
    }

    public void updateBullets(){
        if(this.weapon.bulletArray.size > 0){
            for(Bullet bullet : this.weapon.bulletArray){
                if(bullet.updateBullet()){
                    weapon.bulletArray.removeValue(bullet,true);
                }
            }
        }
    }

    public void moveCharacter(){
        switch (direction){
            case down:
                body.setLinearVelocity(0,-5-speed);
                break;
            case left:
                body.setLinearVelocity(-5-speed,0);
                break;
            case right:
                body.setLinearVelocity(5+speed,0);
                break;
            case up:
                body.setLinearVelocity(0,5+speed);
                break;
        }
    }
}
