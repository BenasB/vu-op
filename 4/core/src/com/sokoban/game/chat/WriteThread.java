package com.sokoban.game.chat;

import java.io.*;
import java.net.*;

public class WriteThread extends Thread {
    private PrintWriter writer;
    private String message;

    public WriteThread(Socket socket, ChatClient client) {
        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public synchronized void run() {
        do {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            writer.println(message);
        } while (true);
    }

    public synchronized void setMessage(String message) {
        this.message = message;
        notifyAll();
    }
}