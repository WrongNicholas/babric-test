package io.github.wrongnicholas.spacemod.gen.biome;

import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;

import java.util.Random;

public class SpaceBiomeSource extends BiomeSource {
    private final OctavePerlinNoiseSampler biomeNoise;

    public SpaceBiomeSource(World world) {
        super(world);
        this.biomeNoise = new OctavePerlinNoiseSampler(new Random(world.getSeed()), 4);
    }

    private Biome getBiomeAt(int x, int z) {
        double mesa = this.biomeNoise.sample(x * 0.0075D, z * 0.0075D);
        return mesa > 0.04D ? SpaceBiomes.CLIFFS : SpaceBiomes.WASTELANDS;
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

        int i = 0;
        for (int dz = 0; dz < depth; dz++) {
            for (int dx = 0; dx < width; dx++) {
                int worldX = x + dx;
                int worldZ = z + dz;

                biomes[i] = getBiomeAt(worldX, worldZ);
                this.temperatureMap[i] = 0.5D;
                this.downfallMap[i] = 0.0D;
                this.weirdnessMap[i] = 0.0D;
                i++;
            }
        }

        return biomes;
    }

    @Override
    public double[] create(double[] map, int x, int z, int width, int depth) {
        if (map == null || map.length < width * depth) {
            map = new double[width * depth];
        }

        if (this.downfallMap == null || this.downfallMap.length < width * depth) {
            this.downfallMap = new double[width * depth];
        }

        if (this.weirdnessMap == null || this.weirdnessMap.length < width * depth) {
            this.weirdnessMap = new double[width * depth];
        }

        for (int i = 0; i < width * depth; i++) {
            map[i] = 0.5D;
            this.downfallMap[i] = 0.0D;
            this.weirdnessMap[i] = 0.0D;
        }

        this.temperatureMap = map;
        return map;
    }
}