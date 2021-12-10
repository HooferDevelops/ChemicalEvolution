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

    @SubscribeEvent
    public static InteractionResult EntityInteract(PlayerInteractEvent.EntityInteract event){
        if (event.getHand() == InteractionHand.MAIN_HAND && event.getTarget() instanceof Cow){
            ItemStack item = event.getItemStack();
            if (item.getItem() == Registration.GLASS_CHEMISTRY_BOTTLE.get()) {
                if (!event.getWorld().isClientSide()){
                    item.interactLivingEntity(event.getPlayer(), (LivingEntity) event.getTarget(), event.getHand());

                    //event.getTarget().playSound(SoundEvents.COW_HURT, 1.0F, 1.0F);
                    event.getTarget().playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
                    event.getTarget().hurt(DamageSource.playerAttack(event.getPlayer()), 0);
                    ((Cow) event.getTarget()).addEffect(new MobEffectInstance(MobEffects.POISON, 5*20, 1));


                    event.getPlayer().getInventory().add(new ItemStack(Registration.RADIOACTIVE_GLASS_CHEMISTRY_BOTTLE.get(), 1));

                    if (!event.getPlayer().getAbilities().instabuild) {
                        item.shrink(1);
                    }
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    public void setup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(Registration.CHEMISTRY_INCUBATOR.get(), RenderType.translucent());
        LOGGER.info("Initialized TestMod");
    }
}