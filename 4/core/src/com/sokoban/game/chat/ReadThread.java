package com.sokoban.game.chat;

import java.io.*;
import java.net.*;

import com.badlogic.gdx.scenes.scene2d.ui.List;

public class ReadThread extends Thread {
    private BufferedReader reader;
    private List<String> messageList;

    public ReadThread(Socket socket, ChatClient client, List<String> messageList) {
        this.messageList = messageList;

        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                String response = reader.readLine();
                if (response == null || response.isEmpty())
                    continue;

                messageList.getItems().add(response);
            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}