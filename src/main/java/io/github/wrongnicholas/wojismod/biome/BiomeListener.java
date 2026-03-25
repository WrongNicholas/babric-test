package io.github.wrongnicholas.wojismod.biome;

import io.github.wrongnicholas.wojismod.WojisMod;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.biome.Biome;
import net.modificationstation.stationapi.api.event.world.biome.BiomeRegisterEvent;
import net.modificationstation.stationapi.api.event.worldgen.biome.BiomeProviderRegisterEvent;
import net.modificationstation.stationapi.api.worldgen.BiomeAPI;
import net.modificationstation.stationapi.api.worldgen.biome.BiomeBuilder;
import net.modificationstation.stationapi.api.worldgen.biome.ClimateBiomeProvider;
import net.modificationstation.stationapi.api.worldgen.surface.SurfaceBuilder;

public class BiomeListener {

    public static Biome wastelandsBiome;

    public static ClimateBiomeProvider climateBiomeProvider;

    //@EventListener
    public void registerBiomes(BiomeRegisterEvent event) {
        BiomeBuilder biomeBuilder;

        biomeBuilder = BiomeBuilder.start("Wastelands");
        biomeBuilder
                .precipitation(false)
                .snow(false)
                .grassAndLeavesColor(0x6B6B6B)
                .fogColor(0xA09078)
                .height(45, 70)
                .noDimensionFeatures()
                .overworldOres()
                .hostileEntity(ZombieEntity.class, 10)
                .surfaceRule(
                        SurfaceBuilder.start(WojisMod.wastelandSandBlock)
                                .ground(3)
                                .replace(Block.STONE)
                                .build()
                );
        wastelandsBiome = biomeBuilder.build();

    }

    //@EventListener
    public void registerBiomeProvider(BiomeProviderRegisterEvent event) {
        climateBiomeProvider = new ClimateBiomeProvider();
        // Add a biome in the temperature range of 0.7 - 1.0 and humidity of 0.4 - 1.0
        // bigger number -> smaller
        climateBiomeProvider.addBiome(wastelandsBiome, 1.0F, 0.0F, 1.0F, 0.0F);
        BiomeAPI.addOverworldBiomeProvider(WojisMod.NAMESPACE.id("climate_biome_provider"), climateBiomeProvider);
    }
}
