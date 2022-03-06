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
	private BlockReplacer blockReplacer;

	@Override
	public void create() {
		// Set up camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Map.BLOCK_SIZE.x * Map.WIDTH.x, Map.BLOCK_SIZE.y * Map.WIDTH.y);
		batch = new SpriteBatch();

		map = new Map(IndexMap.MapOne);
		player = new Player();
		blockReplacer = new BlockReplacer(camera, map, player);
	}

	@Override
	public void render() {
		ScreenUtils.clear(new Color(0xaa733cff));
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		map.render(batch);
		player.render(batch);
		blockReplacer.render(batch);
		batch.end();

		player.update(map);
		blockReplacer.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
		player.dispose();
		map.dispose();
	}
}
