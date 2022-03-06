package com.sokoban.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class GameManager {
  private static GameManager instance;
  private static boolean inEditor = true;

  private final GameCamera camera;
  private final IResetableEntity player;
  private final IResetableEntity flag;
  private final DynamicMap dynamicMap;

  private final IndexMap[] indexMaps = {
      IndexMap.MAP_ONE(),
      IndexMap.MAP_TWO()
  };

  private int currentMap = -1;

  public GameManager(GameCamera camera, IResetableEntity player, IResetableEntity flag, DynamicMap map) {
    this.camera = camera;
    this.player = player;
    this.flag = flag;
    this.dynamicMap = map;
    LoadNewLevel();

    instance = this;
  }

  public static GameManager getInstance() {
    return instance;
  }

  public void update() {
    if (Gdx.input.isKeyJustPressed(Keys.E)) {
      inEditor = !inEditor;
      if (inEditor)
        camera.switchToEditor();
      else
        camera.switchToGame();
    }
  }

  public static boolean isInEditor() {
    return inEditor;
  }

  public void LoadNewLevel() {
    currentMap = (currentMap + 1) % indexMaps.length;

    inEditor = true;
    camera.switchToEditor();

    dynamicMap.initialize(indexMaps[currentMap]);
    player.reset(indexMaps[currentMap].playerPosition);
    flag.reset(indexMaps[currentMap].flagPosition);
  }
}
