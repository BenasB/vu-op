package com.sokoban.game;

import com.badlogic.gdx.math.GridPoint2;

public class Map {
    static final GridPoint2 BLOCK_SIZE = new GridPoint2(64, 64); // In pixels
    static final GridPoint2 WIDTH = new GridPoint2(10, 10); // In game blocks

    public Entity[] getMap() {
        // map must be exactly WIDTH.X wide and WIDTH.y tall
        int[][] map = {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        };

        return null;
    }

    public static boolean isValidPosition(GridPoint2 position) {
        if (position.x < 0 || position.x >= WIDTH.x)
            return false;

        if (position.y < 0 || position.y >= WIDTH.y)
            return false;

        // Check if block is passable

        return true;
    }
}
