package com.sokoban.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.MathUtils;

public class Map implements Disposable {

    static final GridPoint2 BLOCK_SIZE = new GridPoint2(64, 64); // In pixels
    static final GridPoint2 SIZE = new GridPoint2(10, 10); // In game blocks

    private final OrthographicCamera camera;
    private final Vector3 mouseInWorld3D = new Vector3();
    private BlockEntity[][] entities = new BlockEntity[SIZE.x][SIZE.y];

    public Map(int[][] startingIndexMap, OrthographicCamera camera) {
        this.camera = camera;

        // Populate entities array
        for (int x = 0; x < SIZE.x; x++) {
            for (int y = 0; y < SIZE.y; y++) {
                GridPoint2 position = new GridPoint2(x, y);
                GridPoint2 positionInIndexMap = IndexMap.toIndexMapPoint(position);
                BlockData data = BlockData.BLOCKS[startingIndexMap[positionInIndexMap.x][positionInIndexMap.y]];
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

    public boolean isPositionInMap(GridPoint2 position) {
        if (position.x < 0 || position.x >= SIZE.x)
            return false;

        if (position.y < 0 || position.y >= SIZE.y)
            return false;

        return true;
    }

    public boolean isValidPosition(GridPoint2 position) {
        return isPositionInMap(position) && entities[position.x][position.y].getBlockData().passable;
    }

    public GridPoint2 getMousePositionInWorldSpace() {
        mouseInWorld3D.x = Gdx.input.getX();
        mouseInWorld3D.y = Gdx.input.getY();
        mouseInWorld3D.z = 0;
        camera.unproject(mouseInWorld3D);

        return new GridPoint2(MathUtils.floor(mouseInWorld3D.x / Map.BLOCK_SIZE.x),
                MathUtils.floor(mouseInWorld3D.y / Map.BLOCK_SIZE.y));
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
