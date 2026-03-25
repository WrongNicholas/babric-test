package io.github.wrongnicholas.wojismod.mixin;

import io.github.wrongnicholas.wojismod.gen.dim.SpaceDimension;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.OverworldChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

// Could not figure this out, so this is some straight vibe-code
@Mixin(OverworldChunkGenerator.class)
public class OverworldChunkGeneratorMixin {

    @Shadow private World world;

    @Inject(
            method = "getChunk",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/chunk/Chunk;populateHeightMap()V"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void wojismod$stripBaseWater(
            int chunkX,
            int chunkZ,
            CallbackInfoReturnable<Chunk> cir,
            byte[] blocks,
            Chunk chunk,
            double[] temperatures
    ) {
        if (!(this.world.dimension instanceof SpaceDimension)) {
            return;
        }

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 64; y++) {
                    int index = (z * 16 + x) * 128 + y;
                    int id = blocks[index] & 255;

                    if (id == Block.WATER.id || id == Block.ICE.id) {
                        blocks[index] = 0;
                    }
                }
            }
        }
    }
}