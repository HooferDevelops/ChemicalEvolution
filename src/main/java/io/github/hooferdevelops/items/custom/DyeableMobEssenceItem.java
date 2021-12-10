package io.github.hooferdevelops.items.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Random;

public interface DyeableMobEssenceItem {
    default int getColor(ItemStack p_41122_) {
        CompoundTag compoundtag = p_41122_.getTagElement("display");
        Random rand = new Random();
        return compoundtag != null && compoundtag.contains("color", 99) ? compoundtag.getInt("color") : rand.nextInt(16777215);//16109122;
    }

    default void setColor(ItemStack p_41116_, int p_41117_) {
        p_41116_.getOrCreateTagElement("display").putInt("color", p_41117_);
    }

    default void setName(ItemStack stack, String name){

    }

    static ItemStack modifyEssence(ItemStack stack, int color, String name){
        ItemStack itemstack = ItemStack.EMPTY;
        Item item = stack.getItem();

        itemstack = stack.copy();
        itemstack.setCount(stack.getCount());
        DyeableMobEssenceItem dyeablemobessence = (DyeableMobEssenceItem)item;

        dyeablemobessence.setColor(itemstack, color);
        dyeablemobessence.setName(itemstack, name);

        return itemstack;
    }
}