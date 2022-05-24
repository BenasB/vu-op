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
import com.badlogic.gdx.utils.Disposable;
import com.sokoban.game.chat.ChatClient;

public class ChatUI implements Disposable {

    private List<String> messageList;
    private ChatClient client;

    public Actor getActor(Skin defaultSkin) {
        Table group = new Table();

        Table sendGroup = new Table();
        final TextField messageField = new TextField("", defaultSkin);
        TextButton sendButton = new TextButton("Send", defaultSkin);

        sendButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                client.write(messageField.getText());
            }
        });

        sendGroup.add(messageField).width(Value.percentWidth(.8F, sendGroup));
        sendGroup.add(sendButton).width(Value.percentWidth(.2F, sendGroup));

        messageList = new List<String>(defaultSkin);
        ScrollPane messageScroll = new ScrollPane(messageList, defaultSkin);
        messageScroll.setScrollingDisabled(true, false);

        group.add(messageScroll).width(Value.percentWidth(1F, group)).height(Value.percentHeight(.8F, group)).row();
        group.add(sendGroup).width(Value.percentWidth(1F, group)).height(Value.percentHeight(.1F, group)).row();

        client = new ChatClient("localhost", 8001, messageList);

        return group;
    }

    @Override
    public void dispose() {
        client.dispose();
    }

}
