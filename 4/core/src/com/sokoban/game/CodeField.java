package com.sokoban.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class CodeField {
    public static Actor getActor(Skin defaultSkin) {
        VerticalGroup group = new VerticalGroup();
        TextButton button1 = new TextButton("Click me CODE", defaultSkin);
        button1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("HELO FROM CODE");
            }
        });

        group.addActor(button1);

        return button1;
    }
}
