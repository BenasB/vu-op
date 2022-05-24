package com.sokoban.game.parser;

import java.util.concurrent.atomic.AtomicBoolean;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Parser extends Thread {
    private final Runner runner = new Runner();
    private String codeInput;
    private TextButton button;
    private final long timeBetweenLines = 500;

    private final AtomicBoolean running = new AtomicBoolean(false);

    public Parser(String codeInput, TextButton button) {
        this.codeInput = codeInput;
        this.button = button;
    }

    public void stopRunning() {
        System.out.println("Stopping running");
        running.set(false);
    }

    public void run() {
        running.set(true);

        button.setTouchable(Touchable.disabled);
        button.setText("Running");

        String[] lines = codeInput.split("\n");

        for (int linePointer = 0; (linePointer < lines.length) && running.get(); linePointer++) {
            if (runner.getIgnoreLines()) {
                System.out.println("Ignoring line pointer: " + linePointer);
            } else {
                System.out.println("Line pointer: " + linePointer);
            }

            linePointer = handleLine(lines[linePointer].trim(), linePointer);
        }

        button.setText("Run");
        button.setTouchable(Touchable.enabled);
    }

    private int handleLine(String line, int linePointer) {
        if (line.contains("();") && !runner.getIgnoreLines()) {
            String functionName = line.substring(0, line.indexOf("();"));
            runner.performParameterlessFunction(functionName);

            try {
                Thread.sleep(timeBetweenLines);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (line.contains("(") && line.contains("){")) {
            // Block command
            String commandName = line.substring(0, line.indexOf("(")).trim();
            String argument = line.substring(line.indexOf("(") + 1, line.indexOf(")")).trim();
            runner.startBlockCommand(commandName, argument, linePointer);
        } else if (line.equals("}")) {
            return runner.endLatestBlockCommand(linePointer);
        } else if (line.contains("=") && line.contains(";") && !runner.getIgnoreLines()) {
            String variableName = line.substring(0, line.indexOf("=")).trim();
            String argument = line.substring(line.indexOf("=") + 1, line.indexOf(";")).trim();
            runner.assignVariable(variableName, argument);
        }

        return linePointer;
    }
}
