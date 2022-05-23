package com.sokoban.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

public class Chat {

    public static Actor getActor(Skin defaultSkin) {
        VerticalGroup group = new VerticalGroup();
        TextButton button1 = new TextButton("Click me CHAT", defaultSkin);
        TextButton button2 = new TextButton("Click me CHAT", defaultSkin);
        TextButton button3 = new TextButton("Click me CHAT", defaultSkin);

        group.addActor(button1);
        group.addActor(button2);
        group.addActor(button3);

        return group;
    }

}
