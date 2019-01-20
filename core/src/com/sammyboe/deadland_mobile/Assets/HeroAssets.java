package com.sammyboe.deadland_mobile.Assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class HeroAssets {
    public static final AssetManager manager = new AssetManager();
    public static final String
            heroAtlasKnifeDown = "Hero/HeroKnifeDown.atlas",
            heroAtlasKnifeLeft = "Hero/HeroKnifeLeft.atlas",
            heroAtlasKnifeRight = "Hero/HeroKnifeRight.atlas",
            heroAtlasKnifeUp = "Hero/HeroKnifeUp.atlas",
            heroAtlasMovingDown = "Hero/HeroMovingDown.atlas",
            heroAtlasMovingLeft = "Hero/HeroMovingLeft.atlas",
            heroAtlasMovingRight = "Hero/HeroMovingRight.atlas",
            heroAtlasMovingUp = "Hero/HeroMovingUp.atlas",
            heroTextureStandDown = "Hero/HeroStandDown.png",
            heroTextureStandLeft = "Hero/HeroStandLeft.png",
            heroTextureStandRight = "Hero/HeroStandRight.png",
            heroTextureStandUp = "Hero/HeroStandUp.png";

    public static void load(){
        manager.load(heroAtlasKnifeDown, TextureAtlas.class);
        manager.load(heroAtlasKnifeLeft, TextureAtlas.class);
        manager.load(heroAtlasKnifeRight, TextureAtlas.class);
        manager.load(heroAtlasKnifeUp, TextureAtlas.class);
        manager.load(heroAtlasMovingDown, TextureAtlas.class);
        manager.load(heroAtlasMovingLeft, TextureAtlas.class);
        manager.load(heroAtlasMovingRight, TextureAtlas.class);
        manager.load(heroAtlasMovingUp, TextureAtlas.class);
        manager.load(heroTextureStandDown, Texture.class);
        manager.load(heroTextureStandLeft, Texture.class);
        manager.load(heroTextureStandRight, Texture.class);
        manager.load(heroTextureStandUp, Texture.class);

    }

    public static void dispose(){
        manager.dispose();
    }
}
