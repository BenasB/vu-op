package com.sokoban.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;

public class BlockReplacer extends Entity {

  private static final Color AVAILABLE_COLOR = Color.GREEN;
  private static final Color UNAVAILABLE_COLOR = Color.RED;

  private static BlockData selectedBlock = BlockData.BLOCKS[0];
  private final DynamicMap map;
  private final Player player;
  private final Flag flag;

  public BlockReplacer(DynamicMap map, Player player, Flag flag) {
    super(selectedBlock.localTextureName, new GridPoint2());
    this.map = map;
    this.player = player;
    this.flag = flag;
  }

  public void update() {
    if (!GameManager.isInEditor())
      return;
    trackMouse();
    setBlockOnClick();
  }

  @Override
  public void render(SpriteBatch batch) {
    if (!GameManager.isInEditor())
      return;
    batch.setColor(canPlace() ? AVAILABLE_COLOR : UNAVAILABLE_COLOR);
    super.render(batch);
    batch.setColor(Color.WHITE);
  }

  private boolean canPlace() {
    boolean occupiedByPlayer = player.position.equals(position);
    boolean inMapBounds = map.isPositionInMap(position);
    boolean occupiedByFlag = flag.position.equals(position);

    return !occupiedByPlayer && inMapBounds && !occupiedByFlag;
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
