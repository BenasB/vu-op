package com.sokoban.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class Map implements Disposable {

    static final GridPoint2 BLOCK_SIZE = new GridPoint2(64, 64); // In pixels
    static final GridPoint2 WIDTH = new GridPoint2(10, 10); // In game blocks

    public Array<Entity> entities = new Array<Entity>(WIDTH.x * WIDTH.y);

    public Map() {
        // Populate entities array
        for (int x = 0; x < WIDTH.x; x++) {
            for (int y = 0; y < WIDTH.y; y++) {
                GridPoint2 position = new GridPoint2(x, y);
                GridPoint2 positionInIndexMap = IndexMap.toIndexMapPoint(position);
                BlockData data = BlockData.blocks[IndexMap.blocks[positionInIndexMap.x][positionInIndexMap.y]];
                entities.add(new Entity(data.localTextureName, position));
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Entity entity : entities) {
            entity.render(batch);
        }
    }

    public boolean isValidPosition(GridPoint2 position) {
        if (position.x < 0 || position.x >= WIDTH.x)
            return false;

        if (position.y < 0 || position.y >= WIDTH.y)
            return false;

        GridPoint2 positionInIndexMap = IndexMap.toIndexMapPoint(position);
        return BlockData.blocks[IndexMap.blocks[positionInIndexMap.x][positionInIndexMap.y]].passable;
    }

    @Override
    public void dispose() {
        for (Entity entity : entities) {
            entity.dispose();
        }
    }
}
