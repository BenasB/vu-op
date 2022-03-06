package com.sokoban.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class SokobanGame extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;

	private Player player;
	private Map map;

	@Override
	public void create() {
		map = new Map(IndexMap.MapTwo);
		player = new Player();

		// Set up camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Map.BLOCK_SIZE.x * Map.WIDTH.x, Map.BLOCK_SIZE.y * Map.WIDTH.y);
		batch = new SpriteBatch();
	}

	@Override
	public void render() {
		ScreenUtils.clear(new Color(0xaa733cff));
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		map.render(batch);
		player.render(batch);
		batch.end();

		player.update(map);
	}

	@Override
	public void dispose() {
		batch.dispose();
		player.dispose();
		map.dispose();
	}
}
