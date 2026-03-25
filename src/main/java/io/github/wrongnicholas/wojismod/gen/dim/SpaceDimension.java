package io.github.wrongnicholas.wojismod.gen.dim;

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
    public float getTimeOfDay(long time, float delta) {
        return 0.0F;
    }

    @Override
    public boolean hasWorldSpawn() {
        return true;
    }
}
