package com.sokoban.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;

public class Player extends Entity implements IResetableEntity {

    private final DynamicMap map;
    private final Texture[] walkingTextures = {
            new Texture(Gdx.files.internal("player-north.png")),
            new Texture(Gdx.files.internal("player-east.png")),
            new Texture(Gdx.files.internal("player-south.png")),
            new Texture(Gdx.files.internal("player-west.png")),
    };

    private Texture currentWalkingTexture = walkingTextures[2];

    private Integer dx = 0, dy = 0;

    public Player(DynamicMap map) {
        super("player-south.png", new GridPoint2());

        this.map = map;
    }

    public void update() {
        if (dx != 0 || dy != 0) {
            move(dx, dy);
            dx = 0;
            dy = 0;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(currentWalkingTexture, position.x * DynamicMap.BLOCK_SIZE.x, position.y * DynamicMap.BLOCK_SIZE.y);
    }

    @Override
    public void dispose() {
        super.dispose();
        for (Texture texture : walkingTextures) {
            texture.dispose();
        }
    }

    @Override
    public void reset(GridPoint2 newPosition) {
        position.set(newPosition);
        currentWalkingTexture = walkingTextures[2];
    }

    private void move(Integer deltax, Integer deltay) {
        GridPoint2 newPosition = position.cpy();
        Texture newTexture = currentWalkingTexture;
        newPosition.x += deltax;
        newPosition.y += deltay;

        if (deltax > 0)
            newTexture = walkingTextures[1];
        else if (deltax < 0)
            newTexture = walkingTextures[3];
        else if (deltay > 0)
            newTexture = walkingTextures[0];
        else if (deltay < 0)
            newTexture = walkingTextures[2];

        if (map.isValidPosition(newPosition)) {
            position = newPosition;
            currentWalkingTexture = newTexture;
        }
    }

    public void moveUp() {
        dx = 0;
        dy = 1;
    }

    public void moveDown() {
        dx = 0;
        dy = -1;
    }

    public void moveRight() {
        dx = 1;
        dy = 0;
    }

    public void moveLeft() {
        dx = -1;
        dy = 0;
    }
}
