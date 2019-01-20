package com.sammyboe.deadland_mobile.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.sammyboe.deadland_mobile.GameMain;
import com.sammyboe.deadland_mobile.Utils.GameVar;

public abstract class Character extends Sprite {
    public enum Direction{down,left,right, up}
    public enum Type{hero,zombie}
    public Direction direction;
    public World world;
    public Body body;
    public Type type;
    protected GameMain game;


    public Character(World world, Texture tex, int x, int y, Type type){
        super(tex);
        this.world = world;
        this.type = type;
        this.direction = Direction.down;
        this.setPosition(x,y);
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
        bodyDef.position.set(this.getX()/GameVar.PPM, this.getY()/GameVar.PPM);
        bodyDef.fixedRotation = true;

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.getWidth() / 4 / GameVar.PPM, this.getHeight() / 4 / GameVar.PPM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;
        Fixture fixture = body.createFixture(fixtureDef);
        switch (type){
            case hero:
                fixture.setUserData("hero");
                break;
            case zombie:
                fixture.setUserData("zombie");
                break;
        }

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

    public void moveCharacter(){
        switch (direction){
            case down:
                if(type == Type.hero){ body.setLinearVelocity(0,-5);}else{body.setLinearVelocity(0,-3);}
                break;
            case left:
                if(type == Type.hero){ body.setLinearVelocity(-5,0);}else{body.setLinearVelocity(-3,0);}
                break;
            case right:
                if(type == Type.hero){ body.setLinearVelocity(5,0);}else{body.setLinearVelocity(3,0);}
                break;
            case up:
                if(type == Type.hero){ body.setLinearVelocity(0,5);}else{body.setLinearVelocity(0,3);}
                break;
        }
    }
}
