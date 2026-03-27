package io.github.wrongnicholas.spacemod.mixin;

import io.github.wrongnicholas.spacemod.SpaceMod;
import io.github.wrongnicholas.spacemod.item.SpaceItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.ScreenScaler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.registry.DimensionRegistry;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.OptionalInt;

// Completely vibe-coded. I was not about to figure ts out
@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawContext {

    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "render", at = @At("TAIL"))
    private void spacemod$renderSpaceBubbles(float tickDelta, boolean screenOpen, int mouseX, int mouseY, CallbackInfo ci) {
        PlayerEntity player = this.minecraft.player;
        if (player == null) return;
        if (!isInSpace(player)) return;

        ItemStack helmet = player.inventory.armor[3];
        boolean hasSpaceHelmet = helmet != null && helmet.itemId == SpaceItems.spaceHelmet.id;
        if (hasSpaceHelmet) return;

        ScreenScaler scaler = new ScreenScaler(this.minecraft.options, this.minecraft.displayWidth, this.minecraft.displayHeight);
        int scaledWidth = scaler.getScaledWidth();
        int scaledHeight = scaler.getScaledHeight();

        int full = (int)Math.ceil((double)(player.air - 2) * 10.0D / 300.0D);
        int partial = (int)Math.ceil((double)player.air * 10.0D / 300.0D) - full;

        GL11.glBindTexture(3553, this.minecraft.textureManager.getTextureId("/gui/icons.png"));

        for (int i = 0; i < full + partial; ++i) {
            if (i < full) {
                this.drawTexture(scaledWidth / 2 - 91 + i * 8, scaledHeight - 32 - 9, 16, 18, 9, 9);
            } else {
                this.drawTexture(scaledWidth / 2 - 91 + i * 8, scaledHeight - 32 - 9, 25, 18, 9, 9);
            }
        }
    }

    @Unique
    private boolean isInSpace(PlayerEntity player) {
        OptionalInt spaceId = DimensionRegistry.INSTANCE.getLegacyId(SpaceMod.NAMESPACE.id("space"));
        return spaceId.isPresent() && player.dimensionId == spaceId.getAsInt();
    }
}