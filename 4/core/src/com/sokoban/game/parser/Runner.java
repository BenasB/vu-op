package com.sokoban.game.parser;

import java.util.HashMap;
import java.util.Stack;

import com.sokoban.game.GameManager;
import com.sokoban.game.Player;

public class Runner {
    private Player player;
    private Stack<BlockCommand> blockCommandStack = new Stack<>();
    private HashMap<String, Integer> variableMap = new HashMap<>();
    private boolean ignoreLines = false;

    private class BlockCommand {
        public boolean repeat = false;
        public Integer times;
        public int startingLine;
    }

    public Runner() {
        player = GameManager.getInstance().getPlayer();
    }

    public boolean getIgnoreLines() {
        return ignoreLines;
    }

    public void performParameterlessFunction(String functionName) {
        switch (functionName) {
            case "moveUp":
                player.moveUp();
                break;
            case "moveDown":
                player.moveDown();
                break;
            case "moveRight":
                player.moveRight();
                break;
            case "moveLeft":
                player.moveLeft();
                break;
            default:
                break;
        }
    }

    public void startBlockCommand(String commandName, String argument, int linePointer) {
        System.out.println("Starting block command: " + commandName + " with argument: " + argument);
        if (commandName.equals("repeat")) {
            if (!ignoreLines) {
                Integer times = tryParseAndSubstituteVariable(argument);
                BlockCommand repeatBlock = new BlockCommand();
                repeatBlock.startingLine = linePointer;
                repeatBlock.times = times;
                repeatBlock.repeat = true;
                blockCommandStack.add(repeatBlock);
            } else {
                BlockCommand repeatBlock = new BlockCommand();
                repeatBlock.times = 1;
                repeatBlock.repeat = true;
                blockCommandStack.add(repeatBlock);
            }
        } else if (commandName.equals("if")) {
            BlockCommand ifBlock = new BlockCommand();
            ifBlock.repeat = false;
            blockCommandStack.add(ifBlock);

            if (ignoreLines)
                return;

            if (argument.contains("==")) {
                String[] operands = argument.split("==");
                int op1 = tryParseAndSubstituteVariable(operands[0].trim());
                int op2 = tryParseAndSubstituteVariable(operands[1].trim());
                ignoreLines = op1 == op2;
            } else if (argument.contains(">=")) {
                String[] operands = argument.split(">=");
                int op1 = tryParseAndSubstituteVariable(operands[0].trim());
                int op2 = tryParseAndSubstituteVariable(operands[1].trim());
                ignoreLines = op1 >= op2;
            } else if (argument.contains(">")) {
                String[] operands = argument.split(">");
                int op1 = tryParseAndSubstituteVariable(operands[0].trim());
                int op2 = tryParseAndSubstituteVariable(operands[1].trim());
                ignoreLines = op1 > op2;
            } else if (argument.contains("<=")) {
                String[] operands = argument.split("<=");
                int op1 = tryParseAndSubstituteVariable(operands[0].trim());
                int op2 = tryParseAndSubstituteVariable(operands[1].trim());
                ignoreLines = op1 <= op2;
            } else if (argument.contains("<")) {
                String[] operands = argument.split("<");
                int op1 = tryParseAndSubstituteVariable(operands[0].trim());
                int op2 = tryParseAndSubstituteVariable(operands[1].trim());
                ignoreLines = op1 < op2;
            } else if (argument.contains("!=")) {
                String[] operands = argument.split("!=");
                int op1 = tryParseAndSubstituteVariable(operands[0].trim());
                int op2 = tryParseAndSubstituteVariable(operands[1].trim());
                ignoreLines = op1 != op2;
            }
            ignoreLines = !ignoreLines;
        }
    }

    public int endLatestBlockCommand(int linePointer) {
        BlockCommand command = blockCommandStack.peek();
        if (!command.repeat) {
            ignoreLines = false;
            blockCommandStack.pop();
            return linePointer;
        }

        command.times--;

        if (command.times == 0) {
            blockCommandStack.pop();
            return linePointer;
        }

        return command.startingLine;
    }

    public void assignVariable(String variableName, String argument) {
        Integer intValue;
        try {
            intValue = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            if (argument.contains("+")) {
                String[] operands = argument.split("\\+");
                int op1 = tryParseAndSubstituteVariable(operands[0].trim());
                int op2 = tryParseAndSubstituteVariable(operands[1].trim());
                intValue = op1 + op2;
            } else if (argument.contains("-")) {
                String[] operands = argument.split("-");
                int op1 = tryParseAndSubstituteVariable(operands[0].trim());
                int op2 = tryParseAndSubstituteVariable(operands[1].trim());
                intValue = op1 - op2;
            } else if (argument.contains("*")) {
                String[] operands = argument.split("\\*");
                int op1 = tryParseAndSubstituteVariable(operands[0].trim());
                int op2 = tryParseAndSubstituteVariable(operands[1].trim());
                intValue = op1 * op2;
            } else if (argument.contains("/")) {
                String[] operands = argument.split("/");
                int op1 = tryParseAndSubstituteVariable(operands[0].trim());
                int op2 = tryParseAndSubstituteVariable(operands[1].trim());
                intValue = op1 / op2;
            } else {
                intValue = variableMap.get(argument);
            }
        }
        variableMap.put(variableName, intValue);
        System.out.println("Assigned to variable: " + variableName + " value: " + intValue);
    };

    private Integer tryParseAndSubstituteVariable(String expression) {
        try {
            return Integer.parseInt(expression);
        } catch (NumberFormatException e) {
            return variableMap.get(expression);
        }
    }
}
