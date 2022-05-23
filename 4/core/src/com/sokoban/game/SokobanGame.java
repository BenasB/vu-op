// Benas Budrys 5 grupe

package com.sokoban.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class SokobanGame extends ApplicationAdapter {
	private GameCamera camera;
	private SpriteBatch batch;

	private Player player;
	private Flag flag;
	private DynamicMap map;
	private UIManager uiManager;

	@Override
	public void create() {
		OrthographicCamera internalCamera = new OrthographicCamera();
		batch = new SpriteBatch();

		uiManager = new UIManager();

		map = new DynamicMap(internalCamera);
		player = new Player(map);
		camera = new GameCamera(internalCamera, player);
		flag = new Flag(player);
		new GameManager(camera, player, flag, map);
	}

	@Override
	public void render() {
		ScreenUtils.clear(new Color(0x333333ff));
		camera.internal.update();
		batch.setProjectionMatrix(camera.internal.combined);

		batch.begin();
		map.render(batch);
		player.render(batch);
		flag.render(batch);
		uiManager.render();
		batch.end();

		player.update();
		camera.update();
		flag.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
		player.dispose();
		flag.dispose();
		map.dispose();
		uiManager.dispose();
	}
}
