package com.sokoban.game.parser;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Parser extends Thread {
    private final Runner runner = new Runner();
    private String codeInput;
    private TextButton button;
    private final long timeBetweenLines = 1000;

    public Parser(String codeInput, TextButton button) {
        this.codeInput = codeInput;
        this.button = button;
    }

    public void run() {
        button.setDisabled(true);

        String[] lines = codeInput.split("\n");
        for (String line : lines) {
            line = line.trim();
        }

        for (String line : lines) {
            handleLine(line);

            try {
                Thread.sleep(timeBetweenLines);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        button.setDisabled(false);
    }

    private void handleLine(String line) {
        if (line.contains("();")) {
            String functionName = line.substring(0, line.indexOf("();"));
            runner.performParameterlessFunction(functionName);
        }
    }
}
