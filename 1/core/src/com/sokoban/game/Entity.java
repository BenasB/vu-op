package com.sokoban.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Disposable;

// General class for all Entities of the game
// They should be initialized in start() method
public class Entity implements Disposable {

    private Texture texture;
    protected GridPoint2 position;

    public Entity(String localTextureName, GridPoint2 position) {
        texture = new Texture(Gdx.files.internal(localTextureName));
        this.position = position;
    }

    public Entity(Texture texture, GridPoint2 position) {
        this.texture = texture;
        this.position = position;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x * DynamicMap.BLOCK_SIZE.x, position.y * DynamicMap.BLOCK_SIZE.y);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    public void setTexture(String newLocalTextureName) {
        texture = new Texture(Gdx.files.internal(newLocalTextureName));
    }
}
