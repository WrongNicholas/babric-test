package io.github.wrongnicholas.wojismod.block;

import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class RedCliffRockBlock extends TemplateBlock {
    public RedCliffRockBlock(Identifier identifier) {
        super(identifier, Material.STONE);
    }
}
