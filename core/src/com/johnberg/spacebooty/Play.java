package com.johnberg.spacebooty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Space-Booty
 * Created by john on 12/31/15.
 */
public class Play implements Screen {
    private Batch batch;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer renderer;
    private TiledMapTileLayer gameLayer;
    private TiledMapTileLayer markerLayer;
    private TiledMap tiledMap;
    private TiledMapTileLayer.Cell[] cell;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.setToOrtho(false, 23, 15);

        batch.setProjectionMatrix(camera.combined);
        renderer.setView(camera);

        renderer.getSpriteBatch().begin();
        renderer.renderTileLayer(gameLayer);
        renderer.getSpriteBatch().end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera();

        tiledMap = new TmxMapLoader().load("fortress.tmx");

        //Create the Game Layer
        gameLayer = (TiledMapTileLayer)tiledMap.getLayers().get("Game");

        //Create the Marker layer
        markerLayer = (TiledMapTileLayer)tiledMap.getLayers().get("Marker");
        int column = markerLayer.getWidth();
        int row = markerLayer.getHeight();
        cell = new TiledMapTileLayer.Cell[0];

        //Create the renderer
        float unitScale = 1f / 48f;
        renderer = new OrthogonalTiledMapRenderer(tiledMap, unitScale);
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        tiledMap.dispose();
        batch.dispose();
        renderer.dispose();
    }
}
