package com.sammyboe.deadland_mobile.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.sammyboe.deadland_mobile.GameMain;
import com.sammyboe.deadland_mobile.Utils.GameVar;

public abstract class Character extends Sprite {
    public enum Direction{down,left,right, up}
    public Direction direction;
    public World world;
    public Body body;
    protected GameMain game;


    public Character(World world, Texture tex, int x, int y){
        super(tex);
        this.world = world;
        this.direction = Direction.down;
        this.setPosition(x,y);
        this.createBody();
    }

    public void createBody(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(this.getX()/GameVar.PPM, this.getY()/GameVar.PPM);
        bodyDef.fixedRotation = true;

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.getWidth() / 4 / GameVar.PPM, this.getHeight() / 4 / GameVar.PPM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;
        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();
    }

    public void setDirection (Direction direction){
        if(this.direction != direction){
            this.direction = direction;
        }
    }

    public void updateCharacter(){
        this.setPosition(
                this.body.getPosition().x*GameVar.PPM - (this.getWidth() / 2),
                this.body.getPosition().y*GameVar.PPM - (this.getWidth() / 3));
    }
}
