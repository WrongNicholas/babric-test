package io.github.wrongnicholas.wojismod.gen.biome;

import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;

public class SpaceBiomeSource extends BiomeSource {
    private final Biome biome;

    public SpaceBiomeSource(World world) {
        super(world);
        this.biome = SpaceBiomes.SPACE;
    }

    @Override
    public Biome[] getBiomesInArea(Biome[] biomes, int x, int z, int width, int depth) {
        if (biomes == null || biomes.length < width * depth) {
            biomes = new Biome[width * depth];
        }

        if (this.temperatureMap == null || this.temperatureMap.length < width * depth) {
            this.temperatureMap = new double[width * depth];
        }

        if (this.downfallMap == null || this.downfallMap.length < width * depth) {
            this.downfallMap = new double[width * depth];
        }

        if (this.weirdnessMap == null || this.weirdnessMap.length < width * depth) {
            this.weirdnessMap = new double[width * depth];
        }

        for (int i = 0; i < width * depth; i++) {
            biomes[i] = biome;
            this.temperatureMap[i] = 0.5D;
            this.downfallMap[i] = 0.0D;
            this.weirdnessMap[i] = 0.0D;
        }

        return biomes;
    }

    @Override
    public double[] create(double[] map, int x, int z, int width, int depth) {
        if (map == null || map.length < width * depth) {
            map = new double[width * depth];
        }

        for (int i = 0; i < width * depth; i++) {
            map[i] = 1.0D;
        }

        this.temperatureMap = map;

        if (this.downfallMap == null || this.downfallMap.length < width * depth) {
            this.downfallMap = new double[width * depth];
        }

        if (this.weirdnessMap == null || this.weirdnessMap.length < width * depth) {
            this.weirdnessMap = new double[width * depth];
        }

        for (int i = 0; i < width * depth; i++) {
            this.downfallMap[i] = 0.0D;
            this.weirdnessMap[i] = 0.0D;
        }

        return map;
    }
}