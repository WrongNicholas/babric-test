package io.github.wrongnicholas.wojismod.gen.biome;

import io.github.wrongnicholas.wojismod.WojisMod;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.modificationstation.stationapi.api.event.world.biome.BiomeRegisterEvent;
import net.modificationstation.stationapi.api.worldgen.biome.BiomeBuilder;
import net.modificationstation.stationapi.api.worldgen.surface.SurfaceBuilder;

public class SpaceBiomes {
    public static Biome WASTELANDS;
    public static Biome CLIFFS;

    @EventListener
    public void registerBiomes(BiomeRegisterEvent event) {
        WASTELANDS = BiomeBuilder.start("wastelands")
                .fogColor(0x3A1A14)
                .build();
        CLIFFS = BiomeBuilder.start("cliffs")
                .fogColor(0x3A1A14)
                .build();
    }
}
