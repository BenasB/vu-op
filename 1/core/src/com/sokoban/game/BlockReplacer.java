package com.sokoban.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;

public class BlockReplacer extends Entity {

  private static final Color AVAILABLE_COLOR = Color.GREEN;
  private static final Color UNAVAILABLE_COLOR = Color.RED;

  private static BlockData selectedBlock = BlockData.BLOCKS[4];
  private final Map map;
  private final Player player;

  public BlockReplacer(Map map, Player player) {
    super(selectedBlock.localTextureName, new GridPoint2());
    this.map = map;
    this.player = player;
  }

  public void update() {
    trackMouse();
    setBlockOnClick();
  }

  @Override
  public void render(SpriteBatch batch) {
    batch.setColor(canPlace() ? AVAILABLE_COLOR : UNAVAILABLE_COLOR);
    super.render(batch);
    batch.setColor(Color.WHITE);
  }

  private boolean canPlace() {
    boolean occupiedByPlayer = player.position.equals(position);
    boolean inMapBounds = map.isPositionInMap(position);

    return !occupiedByPlayer && inMapBounds;
  }

  private void trackMouse() {
    position = map.getMousePositionInWorldSpace();
  }

  private void setBlockOnClick() {
    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && canPlace()) {
      map.setBlock(selectedBlock, position);
    }
  }

  public void changeBlockSelection(BlockData data) {
    selectedBlock = data;
    setTexture(selectedBlock.localTextureName);
  }
}
