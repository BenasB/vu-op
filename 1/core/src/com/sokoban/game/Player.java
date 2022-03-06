package com.sokoban.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.GridPoint2;

public class Player extends Entity {

    static final GridPoint2 startingPosition = new GridPoint2(4, 4);

    public Player() {
        super("player.png", startingPosition);
    }

    public void update(Map map) {
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
