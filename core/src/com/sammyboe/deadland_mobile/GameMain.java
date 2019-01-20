package com.sammyboe.deadland_mobile;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sammyboe.deadland_mobile.Assets.HeroAssets;
import com.sammyboe.deadland_mobile.Assets.ZombieAssets;
import com.sammyboe.deadland_mobile.Screens.MenuScreen;

public class GameMain extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		HeroAssets.load();
		HeroAssets.manager.finishLoading();
		ZombieAssets.load();
		ZombieAssets.manager.finishLoading();
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
		//System.out.println("Rendering...");
	}

	@Override
	public void dispose () {
		batch.dispose();
		HeroAssets.dispose();
	}
}