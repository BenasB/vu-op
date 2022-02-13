package com.sokoban.game;

public class BlockData {
    public String localTextureName;
    public boolean passable = true;

    public BlockData(String localTextureName) {
        this.localTextureName = localTextureName;
    }

    public BlockData(String localTextureName, boolean passable) {
        this.localTextureName = localTextureName;
        this.passable = passable;
    }

    public static final BlockData[] blocks = {
            new BlockData("ground.png"),
            new BlockData("grass.png"),
            new BlockData("block.png", false),
            new BlockData("crate1.png", false),
            new BlockData("crate2.png", false),
    };
}