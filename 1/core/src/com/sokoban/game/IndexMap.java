package com.sokoban.game;

import com.badlogic.gdx.math.GridPoint2;

public final class IndexMap {

    private IndexMap() {
    }

    // Must be exactly Map.WIDTH.X wide and Map.WIDTH.y tall
    static final int[][] MAP_ONE = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
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

    static final int[][] MAP_TWO = {
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

    static GridPoint2 toIndexMapPoint(GridPoint2 point) {
        return new GridPoint2(Map.SIZE.y - 1 - point.y, point.x);
    }
}
