package io.github.wrongnicholas.spacemod.mixin;

import io.github.wrongnicholas.spacemod.gen.biome.SpaceBiomes;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public class BiomeMixin {
    @Inject(method = "getSkyColor", at = @At("HEAD"), cancellable = true)
    private void wojismod$getSkyColor(float temperature, CallbackInfoReturnable<Integer> cir) {
        Biome self = (Biome)(Object)this;

        if (self == SpaceBiomes.WASTELANDS)
        {
            cir.setReturnValue(0x211817);
        } else if (self == SpaceBiomes.CLIFFS)
        {
            cir.setReturnValue(0x2e0a07);
        }
    }
}
