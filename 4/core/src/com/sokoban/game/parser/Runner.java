package com.sokoban.game.parser;

import com.sokoban.game.GameManager;
import com.sokoban.game.Player;

public class Runner {
    private Player player;

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
}
