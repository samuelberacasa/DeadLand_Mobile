package com.sammyboe.deadland_mobile.Elements;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.sammyboe.deadland_mobile.Controls.Direction;

public class Weapon {
    public String name;
    public int damage;
    public float range;
    public int bulletsMagazine;
    public int magazineCapacity;
    public int reloadBullets;
    public int reloadCapacity;
    public Array<Bullet> bulletArray;

    public Weapon(String name, int damage, float range, int magazineCapacity, int reloadCapacity) {
        this.name = name;
        this.damage = damage;
        this.range = range;
        this.bulletsMagazine = this.magazineCapacity = magazineCapacity;
        this.reloadBullets = this.reloadCapacity = reloadCapacity;
        this.bulletArray = new Array<Bullet>();
    }

    public void reload(){
        if(bulletsMagazine<magazineCapacity && reloadBullets>0){
            int tempBullets = 0;
            if(reloadBullets>magazineCapacity){
                 tempBullets = magazineCapacity;
            }else{
                tempBullets = reloadBullets;
            }
            bulletsMagazine = tempBullets;
            reloadBullets-=tempBullets;
        }
    }

    public void fill(){
        if(reloadBullets<reloadCapacity){
            reloadBullets = reloadCapacity;
        }
    }

    public void shoot(float x, float y, Direction direction){
        Bullet bullet = new Bullet(x,y,direction, this.range);
        bulletArray.add(bullet);
    }
}
