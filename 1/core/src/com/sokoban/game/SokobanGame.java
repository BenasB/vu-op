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
	private Flag flag;
	private DynamicMap map;
	private BlockReplacer blockReplacer;
	private BlockSelector blockSelector;

	@Override
	public void create() {
		// Set up camera
		camera = new OrthographicCamera();
		final float zoomOutFactor = 1.5f;
		final float translationFactor = (-1f / 2f) * (zoomOutFactor - 1);
		camera.setToOrtho(false, zoomOutFactor * DynamicMap.BLOCK_SIZE.x * DynamicMap.SIZE.x,
				zoomOutFactor * DynamicMap.BLOCK_SIZE.y * DynamicMap.SIZE.y);
		camera.translate(translationFactor * DynamicMap.SIZE.x * DynamicMap.BLOCK_SIZE.x,
				translationFactor * DynamicMap.SIZE.y * DynamicMap.BLOCK_SIZE.y);
		batch = new SpriteBatch();

		IndexMap startingIndexMap = IndexMap.MAP_ONE();
		map = new DynamicMap(startingIndexMap, camera);
		player = new Player(startingIndexMap.playerPosition, map);
		flag = new Flag(startingIndexMap.flagPosition, player);
		blockReplacer = new BlockReplacer(map, player, flag);
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
		flag.render(batch);
		blockReplacer.render(batch);
		blockSelector.render(batch);
		batch.end();

		player.update();
		flag.update();
		blockReplacer.update();
		blockSelector.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
		player.dispose();
		flag.dispose();
		map.dispose();
		blockReplacer.dispose();
		blockSelector.dispose();
	}
}
