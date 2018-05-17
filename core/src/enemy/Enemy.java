/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enemy;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author Admin
 */
public class Enemy extends Sprite{
    //Physics
    //physics world is created in GameMain to allow both enemy and enemy collisions.
    private Body body;
    private World world;
    
    public Enemy(World world, FileHandle textureFile, float x, float y) {
        super(new Texture(textureFile));
        this.world = world;
        setPosition(x - getWidth() / 2, y - getHeight() / 2); //sets position from center
        createBody();
    }
    
    private void createBody() {
        //tells if body is static, dynamic, or kinematic. Sets position of bodyrelative to enemy
        BodyDef bodyDef = new BodyDef();
        //kinematic because enemy moves, but there is no gravity in 2D
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
        //states enemy can not be rotated
        fixtureDef.restitution = (float) 0;
        
        //Fixture, assigns hitbox to enemy
        Fixture fixture = body.createFixture(fixtureDef);
        
        shape.dispose();
    }
    
    public void updateEnemy() {
        //updates position of sprite to position of physics body
        this.setPosition((float) (body.getPosition().x - (getWidth() / 2)), (float) (body.getPosition().y - (getHeight() / 2)));
    }
}
