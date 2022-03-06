package com.sokoban.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Disposable;

public class BlockSelector implements Disposable {

  private static final GridPoint2 ORIGIN_POSITION = new GridPoint2(-2, DynamicMap.SIZE.y - 1);
  private final BlockEntity[] selectableBlocks = new BlockEntity[BlockData.BLOCKS.length];
  private final BlockReplacer blockReplacer;
  private final DynamicMap map;

  public BlockSelector(BlockReplacer blockReplacer, DynamicMap map) {
    this.map = map;
    this.blockReplacer = blockReplacer;
    for (int i = 0; i < BlockData.BLOCKS.length; i++) {
      GridPoint2 position = new GridPoint2(ORIGIN_POSITION.x - (i / DynamicMap.SIZE.y),
          ORIGIN_POSITION.y - (i % DynamicMap.SIZE.y));
      selectableBlocks[i] = new BlockEntity(BlockData.BLOCKS[i], position);
    }
  }

  public void render(SpriteBatch batch) {
    for (Entity entity : selectableBlocks) {
      entity.render(batch);
    }
  }

  public void update() {
    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
      GridPoint2 mousePosition = map.getMousePositionInWorldSpace();

      for (int i = 0; i < selectableBlocks.length; i++) {
        if (selectableBlocks[i].position.equals(mousePosition))
          blockReplacer.changeBlockSelection(selectableBlocks[i].getBlockData());
      }
    }
  }

  @Override
  public void dispose() {
    for (Entity entity : selectableBlocks) {
      entity.dispose();
    }
  }
}
