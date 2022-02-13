package com.sokoban.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class SokobanGame extends ApplicationAdapter {
	private Texture playerImage;
	private Texture groundImage;
	private Texture blockImage;
	private OrthographicCamera camera;
	private SpriteBatch batch;

	private Rectangle player;

	@Override
	public void create() {
		// Load textures
		playerImage = new Texture(Gdx.files.internal("player.png"));
		groundImage = new Texture(Gdx.files.internal("ground.png"));
		blockImage = new Texture(Gdx.files.internal("player.png"));

		// Set up camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Map.BLOCK_SIZE.x * Map.WIDTH.x, Map.BLOCK_SIZE.y * Map.WIDTH.y);
		batch = new SpriteBatch();

		// Create a player "game object" (rectangle)
		player = new Rectangle();
		player.x = 0;
		player.y = 0;
		player.width = 64;
		player.height = 64;
	}

	@Override
	public void render() {
		ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(playerImage, player.x, player.y);
		batch.end();

		if (Gdx.input.isKeyJustPressed(Keys.LEFT))
			player.x -= 64;
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT))
			player.x += 64;

		if (Gdx.input.isKeyJustPressed(Keys.DOWN))
			player.y -= 64;
		if (Gdx.input.isKeyJustPressed(Keys.UP))
			player.y += 64;
	}

	@Override
	public void dispose() {
		batch.dispose();
		playerImage.dispose();
		groundImage.dispose();
		blockImage.dispose();
	}
}
