package com.sokoban.game;

import com.badlogic.gdx.math.GridPoint2;

public final class IndexMap {
    public final GridPoint2 playerPosition;
    public final GridPoint2 flagPosition;
    public final int[][] indexMap = new int[DynamicMap.SIZE.y][DynamicMap.SIZE.x]; // Coords must be reversed

    private IndexMap(GridPoint2 playerPosition, GridPoint2 flagPosition, int[][] indexMap) {
        this.playerPosition = playerPosition;
        this.flagPosition = flagPosition;
        for (int i = 0; i < DynamicMap.SIZE.y; i++) {
            for (int j = 0; j < DynamicMap.SIZE.x; j++) {
                if (i < indexMap.length && j < indexMap[i].length)
                    this.indexMap[i][j] = indexMap[i][j];
                else
                    this.indexMap[i][j] = 0;
            }
        }
    }

    public static final IndexMap MAP_ONE() {
        int[][] indexMap = {
                { 99, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
                { 4, 0, 0, 0, 0, 4, 0, 3, 4, 3 },
                { 0, 0, 3, 0, 0, 3, 0, 4, 0, 0 },
                { 3, 0, 2, 0, 0, 2, 0, 2, 0, 0 },
                { 2, 0, 0, 0, 1, 1, 0, 0, 0, 1 },
                { 0, 0, 1, 1, 1, 0, 2, 0, 0, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 4, 1 },
                { 0, 2, 3, 0, 0, 1, 0, 0, 0, 0 },
                { 0, 4, 0, 1, 1, 0, 0, 3, 2, 0 },
                { 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
        };

        return new IndexMap(new GridPoint2(0, 0), new GridPoint2(5, 5), indexMap);
    }

    public static final IndexMap MAP_TWO() {
        int[][] indexMap = {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 1, 1, 1, 0, 0, 3, 0, 0 },
                { 0, 1, 0, 1, 0, 0, 3, 0, 0, 0 },
                { 0, 0, 0, 2, 0, 3, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 4, 0, 0, 0 },
                { 0, 0, 0, 4, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        };

        return new IndexMap(new GridPoint2(4, 2), new GridPoint2(1, 5), indexMap);
    }

    static GridPoint2 toIndexMapPoint(GridPoint2 point) {
        return new GridPoint2(DynamicMap.SIZE.y - 1 - point.y, point.x);
    }
}
