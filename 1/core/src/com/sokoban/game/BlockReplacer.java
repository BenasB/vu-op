package com.sokoban.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.MathUtils;

public class BlockReplacer extends Entity {

  private static BlockData selectedBlock = BlockData.blocks[4];
  private final Vector3 mouseInWorld3D = new Vector3();
  private final OrthographicCamera camera;
  private final Map map;
  private final Player player;

  public BlockReplacer(OrthographicCamera camera, Map map, Player player) {
    super(selectedBlock.localTextureName, new GridPoint2());
    this.camera = camera;
    this.map = map;
    this.player = player;
  }

  public void update() {
    trackMouse();
    setBlockOnClick();
  }

  @Override
  public void render(SpriteBatch batch) {
    batch.setColor(canPlace() ? Color.GREEN : Color.RED);
    super.render(batch);
    batch.setColor(Color.WHITE);
  }

  private boolean canPlace() {
    return !player.position.equals(position);
  }

  private void trackMouse() {
    mouseInWorld3D.x = Gdx.input.getX();
    mouseInWorld3D.y = Gdx.input.getY();
    mouseInWorld3D.z = 0;
    camera.unproject(mouseInWorld3D);

    position = snapToGrid(mouseInWorld3D);
  }

  private void setBlockOnClick() {
    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && canPlace()) {
      map.setBlock(selectedBlock, position);
    }
  }

  private GridPoint2 snapToGrid(Vector3 vectorPosition) {
    return new GridPoint2(MathUtils.floor(vectorPosition.x / Map.BLOCK_SIZE.x),
        MathUtils.floor(vectorPosition.y / Map.BLOCK_SIZE.y));
  }
}
