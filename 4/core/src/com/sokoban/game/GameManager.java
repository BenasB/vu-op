package com.sokoban.game;

public class GameManager {
  private static GameManager instance;

  private final IResetableEntity player;
  private final IResetableEntity flag;
  private final DynamicMap dynamicMap;

  private final IndexMap[] indexMaps = {
      IndexMap.MAP_ONE(),
      IndexMap.MAP_TWO(),
      IndexMap.MAP_THREE(),
  };

  private int currentMap = -1;

  public GameManager(GameCamera camera, IResetableEntity player, IResetableEntity flag, DynamicMap map) {
    this.player = player;
    this.flag = flag;
    this.dynamicMap = map;
    LoadNewLevel();

    instance = this;
  }

  public static GameManager getInstance() {
    return instance;
  }

  public void LoadNewLevel() {
    currentMap = (currentMap + 1) % indexMaps.length;

    dynamicMap.initialize(indexMaps[currentMap]);
    player.reset(indexMaps[currentMap].playerPosition);
    flag.reset(indexMaps[currentMap].flagPosition);
  }
}
