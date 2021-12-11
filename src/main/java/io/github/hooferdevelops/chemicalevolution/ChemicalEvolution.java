package io.github.hooferdevelops.chemicalevolution;

import io.github.hooferdevelops.Registration;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ChemicalEvolution.MODID)
public class ChemicalEvolution {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "chemicalevolution";

    public ChemicalEvolution() {
        Registration.init();
        // Register the setup method for mod loading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        //MinecraftForge.EVENT_BUS.register(new RenderEvents());
    }

    public void setup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(Registration.CHEMISTRY_INCUBATOR.get(), RenderType.translucent());
        LOGGER.info("Initialized TestMod");
    }
}