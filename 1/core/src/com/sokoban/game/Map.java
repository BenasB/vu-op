package com.sokoban.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
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
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 1, 2, 3, 4, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    };

    public Entity[] entities = new Entity[WIDTH.x * WIDTH.y];

    public Map() {
        // Populate entities array
        for (int i = 0; i < WIDTH.x; i++) {
            for (int j = 0; j < WIDTH.y; j++) {
                BlockData data = blockData[indexMap[i][j]];
                entities[i * WIDTH.x + j] = new Entity(data.localTextureName, new GridPoint2(i, j));
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

        return blockData[indexMap[position.x][position.y]].passable;
    }

    @Override
    public void dispose() {
        for (Entity entity : entities) {
            entity.dispose();
        }
    }
}
