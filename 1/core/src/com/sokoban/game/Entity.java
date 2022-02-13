package com.sokoban.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Disposable;

// General class for all Entities of the game
// They should be initialized in start() method
public class Entity implements Disposable {

    private final Texture texture;
    private GridPoint2 position;

    public Entity(String localTextureName, GridPoint2 position) {
        texture = new Texture(Gdx.files.internal(localTextureName));
        this.position = position;
    }

    public final void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
