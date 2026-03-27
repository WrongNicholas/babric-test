package io.github.wrongnicholas.spacemod.mixin;

import net.minecraft.client.render.entity.PlayerEntityRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {

    @Shadow @Final @Mutable private static String[] armorTextureNames;

    @Inject(method = "<clinit>", at =@At("TAIL"))
    private static void spacemod$addArmorTextureName(CallbackInfo ci) {
        String[] old = armorTextureNames;
        String[] extended = Arrays.copyOf(old, old.length + 1);
        extended[old.length] = "space";
        armorTextureNames = extended;
    }
}
