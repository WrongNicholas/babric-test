package io.github.wrongnicholas.wojismod.mixin;

import io.github.wrongnicholas.wojismod.WojisMod;
import io.github.wrongnicholas.wojismod.gen.dim.SpaceDimension;
import io.github.wrongnicholas.wojismod.gen.dim.SpaceTravelAgent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.registry.DimensionRegistry;
import net.modificationstation.stationapi.api.world.dimension.DimensionHelper;
import net.modificationstation.stationapi.api.world.dimension.VanillaDimensions;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.OptionalInt;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "damage", at = @At("HEAD"))
    private void wojismod$damage(Entity damageSource, int amount, CallbackInfoReturnable<Boolean> cir) {
        if ((Object)this instanceof PlayerEntity player) {
            @NotNull OptionalInt overworldId = DimensionRegistry.INSTANCE.getLegacyId(VanillaDimensions.OVERWORLD);
            @NotNull OptionalInt spaceId = DimensionRegistry.INSTANCE.getLegacyId(WojisMod.NAMESPACE.id("space"));

            if (overworldId.isPresent() && spaceId.isPresent()) {
                if (player.dimensionId == overworldId.getAsInt()) {
                    DimensionHelper.switchDimension(player, WojisMod.NAMESPACE.id("space"), 1, new SpaceTravelAgent());
                }
            }
        }
    }

    @ModifyConstant(method = "travel", constant = @Constant(doubleValue = 0.08D))
    private double modifyGravity(double original) {
        World world = ((LivingEntity)(Object)this).world;
        if (world.dimension instanceof SpaceDimension) {
            return 0.02D;
        }
        return original;
    }

    @ModifyVariable(method = "onLanding", at = @At("HEAD"), argsOnly = true)
    private float wojismod$modifyFallDistance(float fallDistance) {
        World world = ((LivingEntity)(Object)this).world;
        if (world.dimension instanceof SpaceDimension) {
            return fallDistance * 0.35F;
        }
        return fallDistance;
    }

}
