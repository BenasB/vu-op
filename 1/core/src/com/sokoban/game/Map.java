package com.sokoban.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Disposable;

public class Map implements Disposable {

    static final GridPoint2 BLOCK_SIZE = new GridPoint2(64, 64); // In pixels
    static final GridPoint2 WIDTH = new GridPoint2(10, 10); // In game blocks

    private BlockEntity[][] entities = new BlockEntity[WIDTH.x][WIDTH.y];

    public Map(int[][] startingIndexMap) {
        // Populate entities array
        for (int x = 0; x < WIDTH.x; x++) {
            for (int y = 0; y < WIDTH.y; y++) {
                GridPoint2 position = new GridPoint2(x, y);
                GridPoint2 positionInIndexMap = IndexMap.toIndexMapPoint(position);
                BlockData data = BlockData.blocks[startingIndexMap[positionInIndexMap.x][positionInIndexMap.y]];
                setBlock(data, position);
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (BlockEntity[] blockEntities : entities) {
            for (BlockEntity blockEntity : blockEntities) {
                blockEntity.render(batch);
            }
        }
    }

    public void setBlock(BlockData blockData, GridPoint2 position) {
        entities[position.x][position.y] = new BlockEntity(blockData, position);
    }

    public boolean isValidPosition(GridPoint2 position) {
        if (position.x < 0 || position.x >= WIDTH.x)
            return false;

        if (position.y < 0 || position.y >= WIDTH.y)
            return false;

        return entities[position.x][position.y].getBlockData().passable;
    }

    @Override
    public void dispose() {
        for (BlockEntity[] blockEntities : entities) {
            for (BlockEntity blockEntity : blockEntities) {
                blockEntity.dispose();
            }
        }
    }
}
