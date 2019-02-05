package com.sammyboe.deadland_mobile.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.sammyboe.deadland_mobile.Assets.ElementAssets;
import com.sammyboe.deadland_mobile.Controls.Direction;

public class Bullet extends Rectangle {
    public Texture texture;
    Direction direction;
    float originX, originY;
    float range;
    float speed = 10;

    public Bullet(float x, float y, Direction direction, float range){
        super();
        this.x = this.originX = x;
        this.y = this.originY = y;
        this.direction = direction;
        switch (this.direction){
            case down:
            case up:
                this.texture = ElementAssets.manager.get(ElementAssets.verticalBullet);
                break;
            case right:
            case left:
                this.texture = ElementAssets.manager.get(ElementAssets.horizontalBullet);
                break;
        }
        this.width = this.texture.getWidth();
        this.height = this.texture.getHeight();
        this.range = range;
    }

    public boolean updateBullet(){
        switch (this.direction){
            case up:
                this.y+=30;
                if(Math.abs(this.y - this.originY)>this.range){
                    return true;
                }
                break;
            case left:
                this.x-=30;
                if(Math.abs(this.originX - this.x)>this.range){
                    return true;
                }
                break;
            case right:
                this.x+=30;
                if(Math.abs(this.x - this.originX)>this.range){
                    return true;
                }
                break;
            case down:
                this.y-=30;
                if(Math.abs(this.originY - this.y)>this.range){
                    return true;
                }
                break;
        }
        return false;
    }
}
