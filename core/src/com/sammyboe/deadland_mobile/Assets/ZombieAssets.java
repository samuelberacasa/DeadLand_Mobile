package com.sammyboe.deadland_mobile.Assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class ZombieAssets {
    public static final AssetManager manager = new AssetManager();

    public static final String
            zombieAppears = "Zombie/zombieAppears.atlas",
            zombieDying = "Zombie/zombieDying.atlas",
            zombieMovingDown = "Zombie/zombieMovingDown.atlas",
            zombieMovingLeft = "Zombie/zombieMovingLeft.atlas",
            zombieMovingRight = "Zombie/zombieMovingRight.atlas",
            zombieMovingUp = "Zombie/zombieMovingUp.atlas",
            zombieStandDown = "Zombie/zombieStandDown.png";

    public static void load(){
        manager.load(zombieAppears, TextureAtlas.class);
        manager.load(zombieDying, TextureAtlas.class);
        manager.load(zombieMovingDown, TextureAtlas.class);
        manager.load(zombieMovingLeft, TextureAtlas.class);
        manager.load(zombieMovingRight, TextureAtlas.class);
        manager.load(zombieMovingUp, TextureAtlas.class);
        manager.load(zombieStandDown, Texture.class);
    }

    public static void dispose(){
        manager.dispose();
    }
}
