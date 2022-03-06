package com.sokoban.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class GameCamera {
  public OrthographicCamera internal;

  // Game camera values
  private final GridPoint2 gameSize = new GridPoint2(10, 10);

  // Editor camera values
  private final float zoomOutFactor = 1.5f;
  private final float translationFactor = (-1f / 2f) * (zoomOutFactor - 1);

  private final Player player;

  private GridPoint2 position = new GridPoint2();

  public GameCamera(OrthographicCamera internal, Player player) {
    this.internal = internal;
    this.player = player;
  }

  public void switchToEditor() {
    internal.setToOrtho(false, zoomOutFactor * DynamicMap.BLOCK_SIZE.x * DynamicMap.SIZE.x,
        zoomOutFactor * DynamicMap.BLOCK_SIZE.y * DynamicMap.SIZE.y);
    internal.translate(translationFactor * DynamicMap.SIZE.x * DynamicMap.BLOCK_SIZE.x,
        translationFactor * DynamicMap.SIZE.y * DynamicMap.BLOCK_SIZE.y);
  }

  public void switchToGame() {
    internal.translate(-translationFactor * DynamicMap.SIZE.x * DynamicMap.BLOCK_SIZE.x,
        -translationFactor * DynamicMap.SIZE.y * DynamicMap.BLOCK_SIZE.y);
    internal.setToOrtho(false, DynamicMap.BLOCK_SIZE.x * gameSize.x,
        DynamicMap.BLOCK_SIZE.y * gameSize.y);
  }

  public void update() {
    if (GameManager.isInEditor())
      return;

    if (!player.position.equals(position)) {
      Vector2 newPosition = new Vector2(
          DynamicMap.BLOCK_SIZE.x
              * MathUtils.clamp(player.position.x, gameSize.x / 2, DynamicMap.SIZE.x - gameSize.x / 2),
          DynamicMap.BLOCK_SIZE.y
              * MathUtils.clamp(player.position.y, gameSize.y / 2, DynamicMap.SIZE.x - gameSize.y / 2));

      internal.position.set(newPosition, 0);
    }
  }
}
