package io.github.wrongnicholas.wojismod;

import io.github.wrongnicholas.wojismod.block.MoonrockBlock;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import org.apache.logging.log4j.Logger;

public class WojisMod {
    @Entrypoint.Namespace
    public static Namespace NAMESPACE;

    @Entrypoint.Logger
    public static Logger LOGGER;

    public static Block moonrockBlock;

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        moonrockBlock = new MoonrockBlock(NAMESPACE.id("moonrock_block"))
                .setHardness(0.8F)
                .setResistance(20.0F)
                .setOpacity(3)
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setTranslationKey(NAMESPACE, "moonrock_block");
    }
}
