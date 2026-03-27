package io.github.wrongnicholas.spacemod.gen.dim;

import io.github.wrongnicholas.spacemod.gen.biome.SpaceBiomeSource;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.EnvironmentInterface;
import net.minecraft.world.chunk.ChunkSource;
import net.minecraft.world.dimension.Dimension;
import net.modificationstation.stationapi.api.client.world.dimension.TravelMessageProvider;

@EnvironmentInterface(value = EnvType.CLIENT, itf = TravelMessageProvider.class)
public class SpaceDimension extends Dimension {
    public SpaceDimension(int serialId) {
        id = serialId;
    }

    @Override
    public float getTimeOfDay(long time, float tickDelta) {
        int var4 = (int)(time % 12000L);
        float var5 = ((float)var4 + tickDelta) / 12000.0F - 0.25F;
        if (var5 < 0.0F) {
            ++var5;
        }

        if (var5 > 1.0F) {
            --var5;
        }

        float var7 = 1.0F - (float)((Math.cos((double)var5 * Math.PI) + (double)1.0F) / (double)2.0F);
        var5 += (var7 - var5) / 3.0F;
        return var5;
    }

    @Override
    public boolean hasWorldSpawn() {
        return true;
    }

    @Override
    public void initBiomeSource() {
        biomeSource = new SpaceBiomeSource(this.world);
    }

    @Override
    public ChunkSource createChunkGenerator() {
        return new SpaceChunkGenerator(this.world, this.world.getSeed());
    }
}
