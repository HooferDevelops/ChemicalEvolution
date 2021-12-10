package io.github.hooferdevelops.chemicalevolution;

import io.github.hooferdevelops.Registration;
import io.github.hooferdevelops.blocks.custom.ChemistryIncubatorRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ChemicalEvolution.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RenderEvents {
    @SubscribeEvent
    static void EntityRendersEvent(EntityRenderersEvent.RegisterRenderers renders) {
        renders.registerBlockEntityRenderer(Registration.CHEMISTRY_INCUBATOR_ENTITY.get(), ChemistryIncubatorRenderer::new);
    }
}
