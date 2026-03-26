package io.github.wrongnicholas.wojismod;

import io.github.wrongnicholas.wojismod.block.OrangeCliffSandBlock;
import io.github.wrongnicholas.wojismod.block.RedCliffRockBlock;
import io.github.wrongnicholas.wojismod.block.RedCliffSandBlock;
import io.github.wrongnicholas.wojismod.block.WastelandSandBlock;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.world.dimension.Dimension;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.DimensionRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.DimensionContainer;
import net.modificationstation.stationapi.api.registry.DimensionRegistry;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import org.apache.logging.log4j.Logger;

public class WojisMod {
    @Entrypoint.Namespace
    public static Namespace NAMESPACE;

    @Entrypoint.Logger
    public static Logger LOGGER;

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
    }


}
