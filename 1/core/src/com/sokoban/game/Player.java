package com.sokoban.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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

    public Player(DynamicMap map) {
        super("player-south.png", new GridPoint2());

        this.map = map;
    }

    public void update() {
        if (GameManager.isInEditor())
            return;

        GridPoint2 newPosition = position.cpy();
        Texture newTexture = currentWalkingTexture;

        if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
            newPosition.x--;
            newTexture = walkingTextures[3];
        }
        if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
            newPosition.x++;
            newTexture = walkingTextures[1];
        }

        if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
            newPosition.y--;
            newTexture = walkingTextures[2];
        }
        if (Gdx.input.isKeyJustPressed(Keys.UP)) {
            newPosition.y++;
            newTexture = walkingTextures[0];
        }

        if (map.isValidPosition(newPosition)) {
            position = newPosition;
            currentWalkingTexture = newTexture;
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
}
