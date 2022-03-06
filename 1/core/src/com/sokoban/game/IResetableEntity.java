package com.sokoban.game;

import com.badlogic.gdx.math.GridPoint2;

public interface IResetableEntity {
  public void reset(GridPoint2 newPosition);
}
