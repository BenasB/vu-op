package com.sokoban.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class Map implements Disposable {

    static final GridPoint2 BLOCK_SIZE = new GridPoint2(64, 64); // In pixels
    static final GridPoint2 WIDTH = new GridPoint2(10, 10); // In game blocks

    private class BlockData {
        private String localTextureName;
        private boolean passable = true;

        public BlockData(String localTextureName) {
            this.localTextureName = localTextureName;
        }

        public BlockData(String localTextureName, boolean passable) {
            this.localTextureName = localTextureName;
            this.passable = passable;
        }
    }

    private final BlockData[] blockData = {
            new BlockData("ground.png"),
            new BlockData("grass.png"),
            new BlockData("block.png", false),
            new BlockData("crate1.png", false),
            new BlockData("crate2.png", false),
    };

    // indexMap must be exactly WIDTH.X wide and WIDTH.y tall
    private final int[][] indexMap = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 3, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 2, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 4, 0 },
            { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    };

    public Array<Entity> entities = new Array<Entity>(WIDTH.x * WIDTH.y);

    public Map() {
        // Populate entities array
        for (int x = 0; x < WIDTH.x; x++) {
            for (int y = 0; y < WIDTH.y; y++) {
                GridPoint2 position = new GridPoint2(x, y);
                GridPoint2 positionInIndexMap = toIndexMapPoint(position);
                BlockData data = blockData[indexMap[positionInIndexMap.x][positionInIndexMap.y]];
                entities.add(new Entity(data.localTextureName, position));
            }
        }
    }

    private GridPoint2 toIndexMapPoint(GridPoint2 point) {
        return new GridPoint2(WIDTH.y - 1 - point.y, point.x);
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

        GridPoint2 positionInIndexMap = toIndexMapPoint(position);
        return blockData[indexMap[positionInIndexMap.x][positionInIndexMap.y]].passable;
    }

    @Override
    public void dispose() {
        for (Entity entity : entities) {
            entity.dispose();
        }
    }
}
