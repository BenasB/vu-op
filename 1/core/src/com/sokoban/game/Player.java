package com.sokoban.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.GridPoint2;

public class Player extends Entity {

    private final Map map;

    public Player(GridPoint2 startingPosition, Map map) {
        super("player.png", startingPosition);
        this.map = map;
    }

    public void update() {
        GridPoint2 newPosition = position.cpy();

        if (Gdx.input.isKeyJustPressed(Keys.LEFT))
            newPosition.x--;
        if (Gdx.input.isKeyJustPressed(Keys.RIGHT))
            newPosition.x++;

        if (Gdx.input.isKeyJustPressed(Keys.DOWN))
            newPosition.y--;
        if (Gdx.input.isKeyJustPressed(Keys.UP))
            newPosition.y++;

        if (map.isValidPosition(newPosition))
            position = newPosition;
    }
}
