/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.timtersigni.endofyearproject.GameInformation;
import com.timtersigni.endofyearproject.GameMain;
import input.PlayerInputProcessor;

/**
 *
 * @author tjtat
 */
public class Player extends Sprite {
    
    //Physics
    //physics world is created in GameMain to allow both player and enemy collisions.
    private Body body;
    private World world;
    
    public Player(World world, FileHandle textureFile, float x, float y) {
        super(new Texture(textureFile));
        this.world = world;
        setPosition(x - getWidth() / 2, y - getHeight() / 2); //sets position from center
        createBody();
    }
    
    private void createBody() {
        //tells if body is static, dynamic, or kinematic. Sets position of bodyrelative to player
        BodyDef bodyDef = new BodyDef();
        //kinematic because player moves, but there is no gravity in 2D
        bodyDef.type = BodyDef.BodyType.KinematicBody; 
        bodyDef.position.set(getX() , getY());
        
        body = world.createBody(bodyDef);
        
        //hitbox
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2, (getHeight() / 2));
        
        //FixtureDef, defines fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;
        //states player can not be rotated
        fixtureDef.restitution = (float) 0;
        
        //Fixture, assigns hitbox to player
        Fixture fixture = body.createFixture(fixtureDef);
        
        shape.dispose();
    }
    
    public void updatePlayer() {
        //updates position of sprite to position of physics body
        this.setPosition((float) (body.getPosition().x - (getWidth() / 2)), (float) (body.getPosition().y - (getHeight() / 2)));
    }
    
    public void move() {
        float vX = 0;
        float vY = 0;
        if(Gdx.input.isKeyPressed(Keys.W)) {
            vY = 80;
            System.out.println("W");
        }
        
        if(Gdx.input.isKeyPressed(Keys.S)) {
            vY  = -80;
        }
        
        if(Gdx.input.isKeyPressed(Keys.D)) {
            vX = 80;
        }
        
        if(Gdx.input.isKeyPressed(Keys.A)) {
            vX = -80;
        }
        
        if(Gdx.input.isKeyPressed(Keys.W) && Gdx.input.isKeyPressed(Keys.D)) {
            vX = 65.8f;
            vY = 65.8f;
        }
        
        if(Gdx.input.isKeyPressed(Keys.W) && Gdx.input.isKeyPressed(Keys.A)) {
            vX = -65.8f;
            vY = 65.8f;
        }
        
        if(Gdx.input.isKeyPressed(Keys.S) && Gdx.input.isKeyPressed(Keys.D)) {
            vX = 65.8f;
            vY = -65.8f;
        }
        
        if(Gdx.input.isKeyPressed(Keys.S) && Gdx.input.isKeyPressed(Keys.A)) {
            vX = -65.8f;
            vY = -65.8f;
        }
        
        body.setLinearVelocity(vX, vY);
    }
}
