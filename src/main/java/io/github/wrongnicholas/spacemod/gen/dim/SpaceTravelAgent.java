package io.github.wrongnicholas.spacemod.gen.dim;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.dimension.PortalForcer;

public class SpaceTravelAgent extends PortalForcer {
    @Override
    public void moveToPortal(World world, Entity entity) {
        int x = 0;
        int y = 100;
        int z = 0;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                world.setBlock(x + dx, y, z + dz, Block.STONE.id);
                world.setBlock(x + dx, y + 1, z + dz, 0);
                world.setBlock(x + dx, y + 2, z + dz, 0);
            }
        }

        entity.setPositionAndAnglesKeepPrevAngles(x + 0.5, y + 1.0, z + 0.5, entity.yaw, entity.pitch);
        entity.velocityX = entity.velocityY = entity.velocityZ = 0.0;
    }
}