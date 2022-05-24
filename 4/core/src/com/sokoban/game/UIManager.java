package com.sokoban.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class UIManager implements Disposable {
    private static UIManager Instance;
    private Stage stage;
    private Skin defaultSkin;
    private ChatUI chat = new ChatUI();
    private CodeField codeField = new CodeField();

    public static UIManager getInstance() {
        return Instance;
    }

    public UIManager() {
        if (Instance == null)
            Instance = this;

        defaultSkin = new Skin(Gdx.files.internal("cloud-form-ui.json"));
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Pixmap bgPixmap = new Pixmap(1, 1, Pixmap.Format.RGB565);
        bgPixmap.setColor(Color.DARK_GRAY);
        bgPixmap.fill();
        Table table = new Table();
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap))));
        table.setBounds(0, 0, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight());

        table.add(codeField.getActor(defaultSkin)).width(Value.percentWidth(.8F, table))
                .height(Value.percentHeight(.7F, table)).row();

        table.add(chat.getActor(defaultSkin)).width(Value.percentWidth(.8F, table))
                .height(Value.percentHeight(.3F, table)).row();

        stage.addActor(table);
    }

    public void draw() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void disposeCodeRunner() {
        codeField.dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        chat.dispose();
        codeField.dispose();
    }
}
