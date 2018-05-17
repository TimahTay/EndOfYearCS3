package com.timtersigni.endofyearproject;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import input.PlayerInputProcessor;
import scenes.MainWindow;

public class GameMain extends Game{

    private SpriteBatch batch;    
    private final float pixelsToMeters = 32f;

    
	
	@Override
	public void create () {
            //create batch and draw
            batch = new SpriteBatch();
            //sets scene to main menu
            setScreen(new MainWindow(this));
            //sets input processing to game input processor
            PlayerInputProcessor inputProcessor = new PlayerInputProcessor();
            Gdx.input.setInputProcessor(inputProcessor);
	}

	@Override
	public void render () {
        //passes render method to all screens
        super.render();
    }
        @Override
        public void dispose(){
            
        }

    public SpriteBatch getBatch() {
        return batch;
    }
}
