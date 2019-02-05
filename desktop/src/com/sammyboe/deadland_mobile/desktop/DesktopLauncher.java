package com.sammyboe.deadland_mobile.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sammyboe.deadland_mobile.GameMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Deadland Mobile";
		config.width = 720;
		config.height = 420;
		config.backgroundFPS = 30;
		config.foregroundFPS = 30;
		new LwjglApplication(new GameMain(), config);
	}
}
