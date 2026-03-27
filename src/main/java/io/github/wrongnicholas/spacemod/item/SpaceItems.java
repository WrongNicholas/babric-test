package io.github.wrongnicholas.spacemod.item;

import io.github.wrongnicholas.spacemod.SpaceMod;
import io.github.wrongnicholas.spacemod.item.armor.SpaceHelmet;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;

public class SpaceItems {
    public static Item spaceHelmet;
    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        spaceHelmet = new SpaceHelmet(SpaceMod.NAMESPACE.id("space_helmet"))
                .setTranslationKey(SpaceMod.NAMESPACE, "space_helmet");
    }
}
