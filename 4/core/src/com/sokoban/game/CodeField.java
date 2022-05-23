package com.sokoban.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class CodeField {
    public static Actor getActor(Skin defaultSkin) {
        Table group = new Table();
        TextArea codeArea = new TextArea("", defaultSkin);
        TextButton button1 = new TextButton("Run", defaultSkin);
        button1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("HELO FROM CODE");
            }
        });

        group.add(codeArea).width(Value.percentWidth(1F, group)).height(Value.percentHeight(0.8F, group)).row();
        group.add(button1).row();

        return group;
    }
}
