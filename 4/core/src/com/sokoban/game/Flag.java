package com.sokoban.game;

import com.badlogic.gdx.math.GridPoint2;

public class Flag extends Entity implements IResetableEntity {

  private final Player player;

  public Flag(Player player) {
    super("flag.png", new GridPoint2());
    this.player = player;
  }

  public void update() {
    if (player.position.equals(position)) {
      GameManager.getInstance().LoadNewLevel();
    }
  }

  @Override
  public void reset(GridPoint2 newPosition) {
    position.set(newPosition);
  }
}
