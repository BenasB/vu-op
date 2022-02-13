package com.sokoban.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.ScreenUtils;

public class SokobanGame extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;

	private Player player;

	@Override
	public void create() {
		player = new Player("player.png", new GridPoint2(0, 0));

		// Set up camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Map.BLOCK_SIZE.x * Map.WIDTH.x, Map.BLOCK_SIZE.y * Map.WIDTH.y);
		batch = new SpriteBatch();
	}

	@Override
	public void render() {
		ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		player.render(batch);
		batch.end();

		player.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
		player.dispose();
	}
}
