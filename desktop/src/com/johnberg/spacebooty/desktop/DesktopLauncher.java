package com.johnberg.spacebooty.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.johnberg.spacebooty.SpaceBooty;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1080;
		config.height = 720;
        config.title = SpaceBooty.TITLE;
		new LwjglApplication(new SpaceBooty(), config);
	}
}
