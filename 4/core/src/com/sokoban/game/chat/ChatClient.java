package com.sokoban.game.chat;

import java.net.*;

import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Disposable;

import java.io.*;

public class ChatClient implements Disposable {
    private Socket socket;

    private WriteThread writeThread;

    public ChatClient(String hostname, int port, List<String> messageList) {
        try {
            socket = new Socket(hostname, port);

            System.out.println("Connected to the chat server");

            new ReadThread(socket, this, messageList).start();
            writeThread = new WriteThread(socket, this);
            writeThread.start();

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        }
    }

    public void write(String message) {
        writeThread.setMessage(message);
    }

    @Override
    public void dispose() {
        if (writeThread != null)
            write("disconnect");
    }
}