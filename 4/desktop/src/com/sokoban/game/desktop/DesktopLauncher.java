package com.sokoban.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sokoban.game.SokobanGame;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Sokoban";
		config.width = 2 * 640;
		config.height = 640;
		new LwjglApplication(new SokobanGame(), config);
	}
}
