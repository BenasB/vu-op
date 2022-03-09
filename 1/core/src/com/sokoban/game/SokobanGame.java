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

	private GameManager gameManager;
	private Player player;
	private Flag flag;
	private DynamicMap map;
	private BlockReplacer blockReplacer;
	private BlockSelector blockSelector;

	@Override
	public void create() {
		OrthographicCamera internalCamera = new OrthographicCamera();
		batch = new SpriteBatch();

		map = new DynamicMap(internalCamera);
		player = new Player(map);
		camera = new GameCamera(internalCamera, player);
		flag = new Flag(player);
		gameManager = new GameManager(camera, player, flag, map);
		blockReplacer = new BlockReplacer(map, player, flag);
		blockSelector = new BlockSelector(blockReplacer, map);
	}

	@Override
	public void render() {
		ScreenUtils.clear(GameManager.isInEditor() ? new Color(0x333333ff) : new Color(0xaa733cff));
		camera.internal.update();
		batch.setProjectionMatrix(camera.internal.combined);

		batch.begin();
		map.render(batch);
		player.render(batch);
		flag.render(batch);
		blockReplacer.render(batch);
		blockSelector.render(batch);
		batch.end();

		gameManager.update();
		player.update();
		camera.update();
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
