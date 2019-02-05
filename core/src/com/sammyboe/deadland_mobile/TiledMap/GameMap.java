package com.sammyboe.deadland_mobile.TiledMap;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.sammyboe.deadland_mobile.Utils.GameVar;

public class GameMap {
    public OrthogonalTiledMapRenderer mapRenderer;
    public TiledMap tiledMap;
    public MapProperties properties;

    public void parseTileObjectLayer(World world, MapObjects objects){
        for(MapObject object : objects){
            Shape shape;
            if(object instanceof PolylineMapObject){
                shape = createPolyline((PolylineMapObject) object);
            }else{
                continue;
            }
            Body body;
            BodyDef bodydef = new BodyDef();
            bodydef.type = BodyDef.BodyType.StaticBody;
            body = world.createBody(bodydef);
            body.createFixture(shape, 1);
            shape.dispose();
        }
    }

    private Shape createPolyline(PolylineMapObject polyline) {
        float[] vertices = polyline.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length/2];
        for(int i = 0; i< worldVertices.length; i++){
            worldVertices[i] = new Vector2(vertices[i*2]/GameVar.PPM,vertices[i*2+1]/ GameVar.PPM);
        }
        ChainShape cs = new ChainShape();
        cs.createChain(worldVertices);
        return cs;
    }

    public GameMap(){
        tiledMap = new TmxMapLoader().load("Map/TestMap/testMap.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        properties = tiledMap.getProperties();
    }

    public void dispose(){
        tiledMap.dispose();
        mapRenderer.dispose();
    }

    public int getWidth(){
        int mapWidth = properties.get("width", Integer.class);
        int tilePixelWidth = properties.get("tilewidth", Integer.class);
        return mapWidth * tilePixelWidth;
    }

    public int getHeight(){
        int mapHeight = properties.get("height", Integer.class);
        int tilePixelHeight = properties.get("tileheight", Integer.class);
        return mapHeight * tilePixelHeight;
    }
}
