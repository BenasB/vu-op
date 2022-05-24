package com.sokoban.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sokoban.game.parser.Parser;

public class CodeField {
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

        group.add(codeArea).width(Value.percentWidth(1F, group)).height(Value.percentHeight(0.8F, group)).row();
        group.add(runButton).row();

        return group;
    }
}
