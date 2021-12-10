package io.github.hooferdevelops.chemicalevolution;

import io.github.hooferdevelops.Registration;
import io.github.hooferdevelops.items.custom.DyeableMobEssenceItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Mod.EventBusSubscriber(modid = ChemicalEvolution.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MobEvents {

    // Directly from : https://www.codegrepper.com/code-examples/java/convert+a+rgb+colour+value+to+decimal+java
    @Nullable
    private static int getIntFromColor(float Red, float Green, float Blue){
        int R = Math.round(Red);
        int G = Math.round(Green);
        int B = Math.round( Blue);

        R = (R << 16) & 0x00FF0000;
        G = (G << 8) & 0x0000FF00;
        B = B & 0x000000FF;

        return 0xFF000000 | R | G | B;
    }

    // Help from : https://stackoverflow.com/questions/28162488/get-average-color-on-bufferedimage-and-bufferedimage-portion-as-fast-as-possible
    @Nullable
    private static int getColorFromResource(ResourceLocation resource){
        InputStream is;
        BufferedImage image;

        try {
            String itemID;
            is = Minecraft.getInstance().getResourceManager().getResource(resource).getInputStream();
            image = ImageIO.read(is);
        }catch(IOException e){
            e.printStackTrace();
            return 0;
        }

        int w = image.getWidth();
        int h = image.getHeight();

        long r=0,g=0,b=0;

        for (int x=0; x<w;x++){
            for (int y=0; y<h;y++){
                Color pixel = new Color(image.getRGB(x, y));
                if (pixel.getAlpha() != 0) {
                    r += pixel.getRed();
                    g += pixel.getGreen();
                    b += pixel.getBlue();
                }
            }
        }

        int avg = w*h;
        ChemicalEvolution.LOGGER.info(r);
        ChemicalEvolution.LOGGER.info(g);
        ChemicalEvolution.LOGGER.info(b);
        ChemicalEvolution.LOGGER.info("----");
        ChemicalEvolution.LOGGER.info(r/avg);
        ChemicalEvolution.LOGGER.info(g/avg);
        ChemicalEvolution.LOGGER.info(b/avg);

        //Color res = new Color(r/avg/255,g/avg/255,b/avg/255);

        avg = getIntFromColor(r/avg,g/avg,b/avg);
        ChemicalEvolution.LOGGER.info("AVERAGE COLOR--");
        ChemicalEvolution.LOGGER.info(avg);
        return avg;
    }

    @SubscribeEvent
    public static void LivingDropsEvent(LivingDropsEvent event){
        Entity entity = event.getEntity();

        @Nullable
        SpawnEggItem egg = ForgeSpawnEggItem.fromEntityType(entity.getType());

        if (egg != null){
            ItemStack essence = new ItemStack(Registration.MOB_ESSENCE.get(), 1);
            EntityRenderer renderer = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entity);
            ResourceLocation resource = renderer.getTextureLocation(entity);


            event.getDrops().add(
                    new ItemEntity(
                            entity.getLevel(),
                            entity.getX(), entity.getY(), entity.getZ(),
                            DyeableMobEssenceItem.modifyEssence(essence, egg.getColor(0), entity.getType().getDescription().getString())
                    )
            );
        }
    }
}
