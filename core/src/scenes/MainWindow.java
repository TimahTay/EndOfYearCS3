/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import com.timtersigni.endofyearproject.GameInformation;
import com.timtersigni.endofyearproject.GameMain;
import player.Player;

/**
 *
 * @author tjtat
 */
public class MainWindow implements Screen{

    private GameMain game;
    private Player player;
    private World world;
    private OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private Timer timer;
    
    public MainWindow(GameMain game){ //create method for main menu, reuses sprite batch from GameMain
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameInformation.width, GameInformation.height);
        camera.position.set(GameInformation.width / 2f, GameInformation.height / 2f, 0);
        
        debugRenderer = new Box2DDebugRenderer();
        
        //create world
            world = new World(new Vector2(0,0), true);
            //create batch and draw
            //sets scene to main menu
            //setScreen(new MainMenu(this));
            
        //creating tilemap
            tiledMap = new TmxMapLoader().load("TILEMAP.tmx");
            tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        
        //creates new player
            player = new Player(world, Gdx.files.internal("OldMan.png"), GameInformation.width / 2, GameInformation.height / 2);
        //creating player sprite
            player = new Player(world, Gdx.files.internal("OldMan.png"), GameInformation.width / 2, GameInformation.height / 2);
           
            
        //timer
            timer = new Timer();

            
        
        
    }
    
    @Override
    public void show() {
        
    }

    @Override
    public void render(float f) {
        
        player.updatePlayer();
        
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //tilemap
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        
        //batch
        game.getBatch().begin();
        game.getBatch().draw(player, player.getX(), player.getY());
        game.getBatch().end();
        
        //player
        player.move();
        
        //world
        debugRenderer.render(world, camera.combined);
        world.step(Gdx.graphics.getDeltaTime(), 6, 2); //physics step rate and how precise physics are, call at end    
    }
    

    @Override
    public void dispose() {
        player.getTexture().dispose();
        tiledMap.dispose();
    }
    
    @Override
    public void resize(int i, int i1) {
       
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        
    }
}
