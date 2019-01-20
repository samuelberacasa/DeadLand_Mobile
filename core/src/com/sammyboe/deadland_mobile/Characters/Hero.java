package com.sammyboe.deadland_mobile.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.sammyboe.deadland_mobile.Assets.HeroAssets;

public class Hero extends Character {
    public enum State{stand,move,knife,shoot};
    public State state;
    public Texture heroTexture;
    public TextureAtlas heroAtlas;
    public Animation<TextureRegion> animation;

    public Hero(World world, int x, int y){
        super(world, HeroAssets.manager.get(HeroAssets.heroTextureStandDown, Texture.class),x, y);
        heroTexture = this.getTexture();
        state = State.stand;
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
                        heroTexture = HeroAssets.manager.get(HeroAssets.heroTextureStandDown, Texture.class);
                        break;
                    case left:
                        heroTexture = HeroAssets.manager.get(HeroAssets.heroTextureStandLeft, Texture.class);
                        break;
                    case right:
                        heroTexture = HeroAssets.manager.get(HeroAssets.heroTextureStandRight, Texture.class);
                        break;
                    case up:
                        heroTexture = HeroAssets.manager.get(HeroAssets.heroTextureStandUp, Texture.class);
                        break;
                }
                break;
        }
    }
}
