package io.github.hooferdevelops.items.custom;

import io.github.hooferdevelops.chemicalevolution.ChemicalEvolution;
import io.github.hooferdevelops.utility.IncrementalNumber;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.Random;

public interface DyeableMobEssenceItem {
    @Nullable
    default int getIntFromColor(float Red, float Green, float Blue){
        int R = Math.round(Red);
        int G = Math.round(Green);
        int B = Math.round( Blue);

        R = (R << 16) & 0x00FF0000;
        G = (G << 8) & 0x0000FF00;
        B = B & 0x000000FF;

        return 0xFF000000 | R | G | B;
    }

    default int getColor(ItemStack p_41122_) {
        CompoundTag compoundtag = p_41122_.getTagElement("data");

        MobEssenceItem item = (MobEssenceItem) p_41122_.getItem();
        Color fallbackColor = Color.getHSBColor(item.randomColorDefault.getCurrentValue()/360, 1, 1);
        int fallbackColorInt = getIntFromColor(fallbackColor.getRed(), fallbackColor.getBlue(), fallbackColor.getGreen());

        return compoundtag != null && compoundtag.contains("color", 99) ? compoundtag.getInt("color") : fallbackColorInt;//16109122;
    }

    default void setColor(ItemStack p_41116_, int p_41117_) {
        p_41116_.getOrCreateTagElement("data").putInt("color", p_41117_);
    }

    default void setType(ItemStack stack, String name){
        stack.getOrCreateTagElement("data").putString("type", name);
    }

    static ItemStack modifyEssence(ItemStack stack, int color, Entity entity){
        ItemStack itemstack = ItemStack.EMPTY;
        Item item = stack.getItem();

        itemstack = stack.copy();
        itemstack.setCount(stack.getCount());
        DyeableMobEssenceItem dyeablemobessence = (DyeableMobEssenceItem)item;

        dyeablemobessence.setColor(itemstack, color);
        dyeablemobessence.setType(itemstack, entity.getType().getDescriptionId());

        return itemstack;
    }
}