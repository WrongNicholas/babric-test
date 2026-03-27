package io.github.wrongnicholas.spacemod.mixin;

import io.github.wrongnicholas.spacemod.SpaceMod;
import io.github.wrongnicholas.spacemod.gen.dim.SpaceDimension;
import io.github.wrongnicholas.spacemod.gen.dim.SpaceModDimensions;
import io.github.wrongnicholas.spacemod.gen.dim.SpaceTravelAgent;
import io.github.wrongnicholas.spacemod.item.SpaceItems;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.registry.DimensionRegistry;
import net.modificationstation.stationapi.api.world.dimension.DimensionHelper;
import net.modificationstation.stationapi.api.world.dimension.VanillaDimensions;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.OptionalInt;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "damage", at = @At("HEAD"))
    private void wojismod$damage(Entity damageSource, int amount, CallbackInfoReturnable<Boolean> cir) {
        if ((Object)this instanceof PlayerEntity player) {
            @NotNull OptionalInt overworldId = DimensionRegistry.INSTANCE.getLegacyId(VanillaDimensions.OVERWORLD);
            @NotNull OptionalInt spaceId = DimensionRegistry.INSTANCE.getLegacyId(SpaceMod.NAMESPACE.id("space"));

            if (overworldId.isPresent() && spaceId.isPresent()) {
                if (player.dimensionId == overworldId.getAsInt()) {
                    DimensionHelper.switchDimension(player, SpaceMod.NAMESPACE.id("space"), 1, new SpaceTravelAgent());
                }
                else if (player.dimensionId == spaceId.getAsInt()) {
                    player.inventory.addStack(new ItemStack(SpaceItems.spaceHelmet));
                }
            }
        }
    }

    @ModifyConstant(method = "travel", constant = @Constant(doubleValue = 0.08D))
    private double modifyGravity(double original) {
        World world = ((LivingEntity)(Object)this).world;
        if (world.dimension instanceof SpaceDimension) {
            return 0.025D;
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

    @Redirect(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isInFluid(Lnet/minecraft/block/material/Material;)Z"))
    private boolean spacemod$fakeWaterCheck(LivingEntity entity, Material material) {
        if (entity.isInFluid(material)) {
            return true;
        }

        if (!(entity instanceof PlayerEntity player)) {
            return false;
        }

        // helmet slot
        ItemStack helmet = player.inventory.getArmorStack(3);
        boolean hasSpaceHelmet = helmet != null && helmet.itemId == SpaceItems.spaceHelmet.id;

        return isInSpace(player) && !hasSpaceHelmet;
    }

    @Unique
    private boolean isInSpace(PlayerEntity player) {
        SpaceMod.LOGGER.info(player.dimensionId == SpaceModDimensions.SPACE_CONTAINER.getLegacyID());
        return player.dimensionId == SpaceModDimensions.SPACE_CONTAINER.getLegacyID();
    }
}
