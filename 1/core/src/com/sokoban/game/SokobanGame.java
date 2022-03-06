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
	private BlockSelector blockSelector;

	@Override
	public void create() {
		// Set up camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 2 * Map.BLOCK_SIZE.x * Map.SIZE.x, 2 * Map.BLOCK_SIZE.y * Map.SIZE.y);
		camera.translate(-1 / 2f * Map.SIZE.x * Map.BLOCK_SIZE.x, -1 / 2f * Map.SIZE.y * Map.BLOCK_SIZE.y);
		batch = new SpriteBatch();

		map = new Map(IndexMap.MAP_ONE, camera);
		player = new Player();
		blockReplacer = new BlockReplacer(map, player);
		blockSelector = new BlockSelector(blockReplacer, map);
	}

	@Override
	public void render() {
		ScreenUtils.clear(new Color(0x333333ff)); // 0xaa733cff
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		map.render(batch);
		player.render(batch);
		blockReplacer.render(batch);
		blockSelector.render(batch);
		batch.end();

		player.update(map);
		blockReplacer.update();
		blockSelector.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
		player.dispose();
		map.dispose();
		blockReplacer.dispose();
		blockSelector.dispose();
	}
}
