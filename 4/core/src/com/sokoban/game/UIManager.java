package com.sokoban.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class UIManager implements Disposable {
    private Stage stage;
    private Skin defaultSkin;

    public UIManager() {
        defaultSkin = new Skin(Gdx.files.internal("cloud-form-ui.json"));
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setSize(640, 640);

        table.add(CodeField.getActor(defaultSkin)).row();
        table.add(Chat.getActor(defaultSkin)).row();

        stage.addActor(table);

        table.setDebug(true); // This is optional, but enables debug lines for tables.
    }

    public void render() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
