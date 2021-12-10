package io.github.hooferdevelops.chemicalevolution;

import io.github.hooferdevelops.Registration;
import io.github.hooferdevelops.items.custom.DyeableMobEssenceItem;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ChemicalEvolution.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ColorEvents {
    @SubscribeEvent
    public static void ColorHandlerEvent(ColorHandlerEvent.Item event){
        event.getItemColors().register((stack, color) -> {
            return color > 0 ? -1 : ((DyeableMobEssenceItem)stack.getItem()).getColor(stack);
        }, Registration.MOB_ESSENCE.get());
    }
}
