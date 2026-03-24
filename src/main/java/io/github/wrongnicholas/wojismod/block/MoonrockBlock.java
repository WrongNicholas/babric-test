package io.github.wrongnicholas.wojismod.block;

import io.github.wrongnicholas.wojismod.WojisMod;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class MoonrockBlock extends TemplateBlock {
    public MoonrockBlock(Identifier identifier) {
        super(identifier, Material.STONE);
    }
}
