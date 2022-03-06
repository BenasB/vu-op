package com.sokoban.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class GameManager {
  private static boolean inEditor = true;

  private final GameCamera camera;

  public GameManager(GameCamera camera) {
    this.camera = camera;
    camera.switchToEditor();
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

}
