package io.github.wrongnicholas.wojismod.gen.dim;

import io.github.wrongnicholas.wojismod.gen.biome.SpaceBiomeSource;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.EnvironmentInterface;
import net.minecraft.util.math.Vec3d;
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

    @Override
    public void initBiomeSource() {
        biomeSource = new SpaceBiomeSource(this.world);
    }
}
