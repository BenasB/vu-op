package com.sokoban.game;

import com.badlogic.gdx.math.GridPoint2;

public class BlockEntity extends Entity {

  private BlockData blockData;

  public BlockEntity(BlockData blockData, GridPoint2 position) {
    super(blockData.localTextureName, position);
    this.blockData = blockData;
  }

  public BlockData getBlockData() {
    return blockData;
  }
}
