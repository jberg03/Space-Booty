/*
 * Copyright (c) 2016.  John Berg
 * Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.johnberg.spacebooty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;

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
    private Array <TiledMapTileLayer.Cell> cell;
    private boolean levelCreation = true;

    Sprite background;
    Texture tex;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.setToOrtho(false, 23, 15);

        //batch.setProjectionMatrix(camera.combined);

        batch.begin();
            background.draw(batch);
        batch.end();

        renderer.setView(camera);

        renderer.getSpriteBatch().begin();
        renderer.renderTileLayer(gameLayer);

        if(levelCreation)
            renderer.renderTileLayer(markerLayer);

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
        markerLayer = (TiledMapTileLayer)tiledMap.getLayers().get("Markers");

        cell = new Array<TiledMapTileLayer.Cell>();
        cell.add(markerLayer.getCell(2, 3));
        cell.add(markerLayer.getCell(6, 3));
        cell.add(markerLayer.getCell(8, 3));
        cell.add(markerLayer.getCell(12, 3));
        cell.add(markerLayer.getCell(1, 14));
        cell.add(markerLayer.getCell(2, 14));
        cell.add(markerLayer.getCell(6, 14));
        cell.add(markerLayer.getCell(8, 14));
        cell.add(markerLayer.getCell(12, 14));
        cell.add(markerLayer.getCell(13, 14));

        //Create the renderer
        float unitScale = 1f / 48f;
        renderer = new OrthogonalTiledMapRenderer(tiledMap, unitScale);

        tex = new Texture("background.png");
        background = new Sprite(tex);
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
