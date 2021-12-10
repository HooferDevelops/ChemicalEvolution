package io.github.hooferdevelops.chemicalevolution;

import io.github.hooferdevelops.Registration;
import net.minecraft.client.renderer.entity.CowRenderer;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ChemicalEvolution.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class EntityEvents {
    @SubscribeEvent
    public static void EntityAttributeCreationEvent(EntityAttributeCreationEvent event) {
        event.put(Registration.COW_FAKE.get(),
                Mob.createMobAttributes()
                        .add(Attributes.MAX_HEALTH, 5d)
                        .add(Attributes.JUMP_STRENGTH, 100d)
                        .build());
    }

    @SubscribeEvent
    public static void EntityClientRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(Registration.COW_FAKE.get(), CowRenderer::new);
    }
}
