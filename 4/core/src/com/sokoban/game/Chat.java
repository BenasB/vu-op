package com.sokoban.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Chat {

    private static final String[] messages = { "Hello", "World" };

    public static Actor getActor(Skin defaultSkin) {
        Table group = new Table();

        Table sendGroup = new Table();
        final TextField messageField = new TextField("", defaultSkin);
        TextButton sendButton = new TextButton("Send", defaultSkin);

        sendButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Sending: " + messageField.getText());
            }
        });

        sendGroup.add(messageField).width(Value.percentWidth(.8F, sendGroup));
        sendGroup.add(sendButton).width(Value.percentWidth(.2F, sendGroup));

        List<String> list = new List<String>(defaultSkin);
        list.setItems(messages);
        ScrollPane messageScroll = new ScrollPane(list, defaultSkin);
        messageScroll.setScrollingDisabled(true, false);

        group.add(messageScroll).width(Value.percentWidth(1F, group)).height(Value.percentHeight(.8F, group)).row();
        group.add(sendGroup).width(Value.percentWidth(1F, group)).height(Value.percentHeight(.1F, group)).row();

        return group;
    }

}
