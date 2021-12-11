package io.github.hooferdevelops.items.custom;

import io.github.hooferdevelops.chemicalevolution.ChemicalEvolution;
import io.github.hooferdevelops.utility.IncrementalNumber;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.*;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.StringUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.awt.*;

public class MobEssenceItem extends Item implements DyeableMobEssenceItem {
    public MobEssenceItem(Properties p_41383_) {
        super(p_41383_);
    }
    IncrementalNumber randomColorDefault = new IncrementalNumber(-1);


    @Override
    public void inventoryTick(ItemStack item, Level p_41405_, Entity p_41406_, int p_41407_, boolean isSelected){
        if (isSelected){
            if (item.getOrCreateTagElement("data").getString("type") == ""){
                randomColorDefault.incrementCurrentValue(.1f);

                if (randomColorDefault.getCurrentValue() > 360) {
                    randomColorDefault.setCurrentValue(0);
                }
            }
        }
    }

    @Override
    public Component getName(ItemStack item) {
        String itemName = I18n.exists(item.getDescriptionId()) == true ? I18n.get(item.getDescriptionId()) : "";
        String entityDescriptionId = item.getOrCreateTagElement("data").getString("type");
        String itemNameCreative = I18n.exists(item.getDescriptionId()+".creative") == true ? I18n.get(item.getDescriptionId()+".creative") : "";

        if (entityDescriptionId != ""){
            String entityName = I18n.exists(entityDescriptionId) == true ? I18n.get(entityDescriptionId) : "";

            if (itemName != "" && entityName != "") {
                return new TextComponent(entityName + " " + itemName);
            }
        } else if (itemNameCreative != "") {
            return new TextComponent(itemNameCreative);
        }

        return super.getName(item);
    }
}
