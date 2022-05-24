package com.sokoban.game.parser;

import java.util.Stack;

import com.sokoban.game.GameManager;
import com.sokoban.game.Player;

public class Runner {
    private Player player;
    private Stack<BlockCommand> blockCommandStack = new Stack<>();

    private class BlockCommand {
        public boolean repeat = false;
        public Integer times;
        public int startingLine;
    }

    public Runner() {
        player = GameManager.getInstance().getPlayer();
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
            Integer times = Integer.parseInt(argument);
            BlockCommand repeatBlock = new BlockCommand();
            repeatBlock.startingLine = linePointer;
            repeatBlock.times = times;
            repeatBlock.repeat = true;
            blockCommandStack.add(repeatBlock);
        }
    }

    public int endLatestBlockCommand(int linePointer) {
        BlockCommand command = blockCommandStack.peek();
        if (!command.repeat) {
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
}
