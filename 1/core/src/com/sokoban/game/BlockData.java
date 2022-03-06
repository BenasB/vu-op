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

    public static final BlockData[] BLOCKS = {
            new BlockData("ground.png"),
            new BlockData("grass.png"),
            new BlockData("block1.png", false),
            new BlockData("block2.png", false),
            new BlockData("block3.png", false),
            new BlockData("crate1.png", false),
            new BlockData("crate2.png", false),
            new BlockData("crate3.png", false),
            new BlockData("crate4.png", false),
            new BlockData("crate5.png", false),
            new BlockData("crate6.png", false),
            new BlockData("crate7.png", false),
            new BlockData("crate8.png", false),
            new BlockData("crate9.png", false),
            new BlockData("crate10.png", false),
    };
}