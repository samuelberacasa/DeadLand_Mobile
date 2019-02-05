package com.sammyboe.deadland_mobile.Assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class ElementAssets {
    public static final AssetManager manager = new AssetManager();
    public static final String
            horizontalBullet = "Element/HorizontalBullet.png",
            verticalBullet = "Element/VerticalBullet.png",
            staminaUp = "Element/PerkLightWeight.png",
            juggernaut = "Element/PerkHardline.png",
            level1 = "Element/Level1.png",
            level2 = "Element/Level2.png",
            level3 = "Element/Level3.png",
            letterM = "Element/LetterM.png",
            letterR = "Element/LetterR.png",
            letter7 = "Element/Letter7.png",
            screen = "Element/Screen.png",
            newGame = "Element/new-game.png",
            gameOver = "Element/game-over.png",
            blood = "Element/blood-on-screen.png";

    public static void load(){
        manager.load(horizontalBullet, Texture.class);
        manager.load(verticalBullet, Texture.class);
        manager.load(staminaUp, Texture.class);
        manager.load(juggernaut, Texture.class);
        manager.load(level1, Texture.class);
        manager.load(level2, Texture.class);
        manager.load(level3, Texture.class);
        manager.load(screen, Texture.class);
        manager.load(letterM, Texture.class);
        manager.load(letterR, Texture.class);
        manager.load(letter7, Texture.class);
        manager.load(newGame, Texture.class);
        manager.load(gameOver, Texture.class);
        manager.load(blood, Texture.class);
    }

    public static void dispose(){
        manager.dispose();
    }
}
