package com.sammyboe.deadland_mobile.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.sammyboe.deadland_mobile.Controls.Direction;
import com.sammyboe.deadland_mobile.GameMain;
import com.sammyboe.deadland_mobile.Utils.GameVar;

public abstract class Character{
    public enum Type{hero,zombie}
    public Direction direction;
    public World world;
    public Texture texture;
    public Body body;
    public Type type;
    public float posX, posY;
    public float timePassed;
    protected GameMain game;
    public int id;


    public Character(World world, Texture tex, int x, int y, Type type, int id){
        texture = tex;
        this.world = world;
        this.type = type;
        this.direction = Direction.down;
        posX = x;
        posY = y;
        this.timePassed = 0;
        this.id=id;
        this.createBody(type);
    }

    public void createBody(Type type){
        BodyDef bodyDef = new BodyDef();
        switch (type){
            case hero:
                bodyDef.type = BodyDef.BodyType.DynamicBody;
                break;
            case zombie:
                bodyDef.type = BodyDef.BodyType.KinematicBody;
                break;
        }
        bodyDef.position.set(posX/GameVar.PPM, posY/GameVar.PPM);
        bodyDef.fixedRotation = true;

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(texture.getWidth() / 4 / GameVar.PPM, texture.getHeight() / 4 / GameVar.PPM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;
        Fixture fixture = body.createFixture(fixtureDef);
        switch (type){
            case hero:
                fixture.setUserData("hero");
                break;
            case zombie:
                fixture.setUserData("zombie:"+this.id);
                break;
        }

        shape.dispose();
    }

    public void setDirection (Direction direction){
        if(this.direction != direction){
            this.direction = direction;
        }
    }

    public void updatePosition(){
        posX = this.body.getPosition().x*GameVar.PPM - (texture.getWidth() / 2);
        posY = this.body.getPosition().y*GameVar.PPM - (texture.getHeight() / 3);
    }
}
