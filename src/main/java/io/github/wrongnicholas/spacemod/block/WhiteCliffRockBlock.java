package io.github.wrongnicholas.spacemod.block;

import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class WhiteCliffRockBlock extends TemplateBlock {
    public WhiteCliffRockBlock(Identifier identifier) {
        super(identifier, Material.STONE);
    }
}
