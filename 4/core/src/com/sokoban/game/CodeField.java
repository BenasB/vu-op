package com.sokoban.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.sokoban.game.parser.Parser;

public class CodeField implements Disposable {
    private TextArea codeArea;
    private TextButton runButton;
    private Parser parser;

    public Actor getActor(Skin defaultSkin) {
        Table group = new Table();
        codeArea = new TextArea("", defaultSkin);
        runButton = new TextButton("Run", defaultSkin);
        runButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parser = new Parser(codeArea.getText(), runButton);
                parser.start();
            }
        });

        TextButton resetPlayerButton = new TextButton("Reset Player", defaultSkin);
        resetPlayerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (parser != null)
                    parser.stopRunning();
                GameManager.getInstance().resetPlayer();
            }
        });

        group.add(codeArea).width(Value.percentWidth(1F, group)).height(Value.percentHeight(0.8F, group)).row();
        group.add(runButton).row();
        group.add(resetPlayerButton).row();

        return group;
    }

    @Override
    public void dispose() {
        if (parser != null)
            parser.stopRunning();
    }
}
