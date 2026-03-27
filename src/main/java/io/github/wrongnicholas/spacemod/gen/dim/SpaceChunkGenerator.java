package io.github.wrongnicholas.spacemod.gen.dim;

import io.github.wrongnicholas.spacemod.SpaceMod;
import io.github.wrongnicholas.spacemod.block.SpaceBlocks;
import io.github.wrongnicholas.spacemod.gen.biome.SpaceBiomes;
import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.LoadingDisplay;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSource;
import net.modificationstation.stationapi.impl.world.chunk.FlattenedChunk;

import java.util.Random;

public class SpaceChunkGenerator implements ChunkSource {
    private final World world;
    private final Random random;
    private final OctavePerlinNoiseSampler terrainNoise;
    private final OctavePerlinNoiseSampler noiseNoise;
    private double[] heightNoise;

    public SpaceChunkGenerator(World world, long seed) {
        this.world = world;
        this.random = new Random(seed);
        this.terrainNoise = new OctavePerlinNoiseSampler(this.random, 4);
        this.noiseNoise = new OctavePerlinNoiseSampler(new Random(seed + 1234), 4);
    }

    @Override
    public Chunk loadChunk(int chunkX, int chunkZ) {
        return getChunk(chunkX, chunkZ);
    }

    @Override
    public Chunk getChunk(int chunkX, int chunkZ) {
        this.random.setSeed((long) chunkX * 341873128712L + (long) chunkZ * 132897987541L);

        FlattenedChunk chunk = new FlattenedChunk(this.world, chunkX, chunkZ);

        buildTerrain(chunkX, chunkZ, chunk);
        chunk.populateHeightMap();

        return chunk;
    }

    private void buildTerrain(int chunkX, int chunkZ, FlattenedChunk chunk) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = chunkX * 16 + x;
                int worldZ = chunkZ * 16 + z;

                double lowA = this.terrainNoise.sample(worldX * 0.012D, worldZ * 0.012D);
                double lowB = this.terrainNoise.sample(worldX * 0.032D, worldZ * 0.032D) * 0.35D;
                int baseHeight = 54 + (int) ((lowA + lowB) * 1.8D);

                double mesa = this.terrainNoise.sample(worldX * 0.0075D, worldZ * 0.0075D);
                double mesa_orange = this.noiseNoise.sample(worldX * 0.064D, worldZ * 0.064D);

                int bankRise = 0;
                if (mesa > -0.08D) {
                    double b = (mesa - (-0.08D)) / 0.20D;
                    if (b < 0.0D) b = 0.0D;
                    if (b > 1.0D) b = 1.0D;
                    b = b * b * (3.0D - 2.0D * b);
                    bankRise = (int) (b * 4.0D);
                }

                int mesaRise = 0;

                if (mesa > 0.04D) {
                    double t = (mesa - 0.04D) / 0.96D;
                    if (t < 0.0D) t = 0.0D;
                    if (t > 1.0D) t = 1.0D;
                    t = t * t * t;
                    mesaRise = (int) (t * 12.0D);
                }

                int spireRise = 0;
                boolean spireRiseBoolean = mesa > .16D;
                if (mesa > 0.14D) {
                    double spire = this.terrainNoise.sample(worldX * 0.020D, worldZ * 0.020D);
                    double spire2 = this.terrainNoise.sample(worldX * 0.045D, worldZ * 0.045D) * 0.3D;
                    double combined = spire + spire2;
                    if (combined > 0.40D) {
                        double s = (combined - 0.40D) / 0.60D;
                        if (s < 0.0D) s = 0.0D;
                        if (s > 1.0D) s = 1.0D;
                        s = s * s * s * s;
                        spireRise = (int) (s * 16.0D);
                    }
                }

                int terracedMesa = mesaRise + spireRise;
                if (terracedMesa > 0) {
                    terracedMesa = (terracedMesa / 3) * 3;
                }

                int height = baseHeight + bankRise + terracedMesa;

                if (height < 8) height = 8;
                if (height > 127) height = 127;

                Biome biome = this.world.method_1781().getBiome(worldX, worldZ);

                for (int y = 0; y <= height; y++) {
                    var section = chunk.getOrCreateSection(y, false);
                    if (section == null) continue;

                    if (y < height) {
                        int distanceFromTop = 127 - y;
                        if (biome == SpaceBiomes.CLIFFS)
                            if (distanceFromTop % 5 == 0)
                                section.setBlockState(x, y & 15, z, SpaceBlocks.whiteCliffRockBlock.getDefaultState());
                            else if (distanceFromTop % 7 == 0)
                                section.setBlockState(x, y & 15, z, SpaceBlocks.orangeCliffRockBlock.getDefaultState());
                            else section.setBlockState(x, y & 15, z, SpaceBlocks.redCliffRockBlock.getDefaultState());
                        else
                            section.setBlockState(x, y & 15, z, Block.STONE.getDefaultState());

                    } else {
                        if (biome == SpaceBiomes.WASTELANDS) {
                            section.setBlockState(x, y & 15, z, SpaceBlocks.wastelandSandBlock.getDefaultState());
                        } else if (biome == SpaceBiomes.CLIFFS) {
                            if (spireRiseBoolean)
                                section.setBlockState(x, y & 15, z, SpaceBlocks.redCliffRockBlock.getDefaultState());
                            else if (mesa_orange > .5) section.setBlockState(x, y & 15, z, SpaceBlocks.orangeCliffSandBlock.getDefaultState());
                            else section.setBlockState(x, y & 15, z, SpaceBlocks.redCliffSandBlock.getDefaultState());
                        } else {
                            section.setBlockState(x, y & 15, z, Block.STONE.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void decorate(ChunkSource source, int chunkX, int chunkZ) {
    }

    @Override
    public boolean save(boolean saveEntities, LoadingDisplay display) {
        return true;
    }

    @Override
    public boolean tick() {
        return false;
    }

    @Override
    public boolean canSave() {
        return true;
    }

    @Override
    public boolean isChunkLoaded(int x, int z) {
        return true;
    }

    @Override
    public String getDebugInfo() {
        return "SpaceChunkGenerator";
    }
}