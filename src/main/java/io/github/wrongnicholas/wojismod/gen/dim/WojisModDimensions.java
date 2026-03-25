package io.github.wrongnicholas.wojismod.gen.dim;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.world.dimension.Dimension;
import net.modificationstation.stationapi.api.event.registry.DimensionRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;
import net.modificationstation.stationapi.api.registry.DimensionContainer;
import net.modificationstation.stationapi.api.registry.DimensionRegistry;
import net.modificationstation.stationapi.api.util.Identifier;

import static io.github.wrongnicholas.wojismod.WojisMod.NAMESPACE;

@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false))
public class WojisModDimensions {
    public static Identifier SPACE;
    public static DimensionContainer<Dimension> SPACE_CONTAINER;

    @EventListener
    public static void registerDimensions(DimensionRegistryEvent event) {
        DimensionRegistry r = event.registry;

        SPACE = Identifier.of(NAMESPACE, "space");
        SPACE_CONTAINER = new DimensionContainer<>(SpaceDimension::new);

        r.register(SPACE, SPACE_CONTAINER);
    }
}
