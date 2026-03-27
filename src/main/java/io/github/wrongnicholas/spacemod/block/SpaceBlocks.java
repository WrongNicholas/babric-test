package io.github.wrongnicholas.spacemod.block;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;

import static io.github.wrongnicholas.spacemod.SpaceMod.NAMESPACE;

public class SpaceBlocks {

    public static Block wastelandSandBlock;
    public static Block orangeCliffSandBlock;
    public static Block redCliffSandBlock;
    public static Block whiteCliffRockBlock;
    public static Block orangeCliffRockBlock;
    public static Block redCliffRockBlock;

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        wastelandSandBlock = new WastelandSandBlock(NAMESPACE.id("wasteland_sand_block"))
                .setHardness(0.3F)
                .setResistance(0.3F)
                .setSoundGroup(Block.SAND_SOUND_GROUP)
                .setTranslationKey(NAMESPACE, "wasteland_sand_block");
        orangeCliffSandBlock = new OrangeCliffSandBlock(NAMESPACE.id("orange_cliff_sand_block"))
                .setHardness(0.3F)
                .setResistance(0.3F)
                .setSoundGroup(Block.SAND_SOUND_GROUP)
                .setTranslationKey(NAMESPACE, "orange_cliff_sand_block");
        redCliffSandBlock = new RedCliffSandBlock(NAMESPACE.id("red_cliff_sand_block"))
                .setHardness(0.3F)
                .setResistance(0.3F)
                .setSoundGroup(Block.SAND_SOUND_GROUP)
                .setTranslationKey(NAMESPACE, "red_cliff_sand_block");
        redCliffRockBlock = new RedCliffRockBlock(NAMESPACE.id("red_cliff_rock_block"))
                .setHardness(0.5F)
                .setResistance(10.0F)
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setTranslationKey(NAMESPACE, "red_cliff_rock_block");
        orangeCliffRockBlock = new OrangeCliffRockBlock(NAMESPACE.id("orange_cliff_rock_block"))
                .setHardness(0.5F)
                .setResistance(10.0F)
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setTranslationKey(NAMESPACE, "orange_cliff_rock_block");
        whiteCliffRockBlock = new WhiteCliffRockBlock(NAMESPACE.id("white_cliff_rock_block"))
                .setHardness(0.5F)
                .setResistance(10.0F)
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setTranslationKey(NAMESPACE, "white_cliff_rock_block");
    }
}
