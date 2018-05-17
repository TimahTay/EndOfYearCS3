package com.timtersigni.endofyearproject.desktop;
      
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.timtersigni.endofyearproject.GameMain;
import com.timtersigni.endofyearproject.GameInformation;
import input.PlayerInputProcessor;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                
                config.width = GameInformation.width;
                config.height = GameInformation.height;
		new LwjglApplication(new GameMain(), config);
	}
}
