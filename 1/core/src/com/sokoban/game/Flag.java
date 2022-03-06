package com.sokoban.game;

import com.badlogic.gdx.math.GridPoint2;

public class Flag extends Entity {

  private final Player player;

  public Flag(GridPoint2 startingPosition, Player player) {
    super("flag.png", startingPosition);
    this.player = player;
  }

  public void update() {
    if (player.position.equals(position)) {
      System.out.println("Reached the goal");
    }
  }
}
