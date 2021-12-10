package io.github.hooferdevelops.items.custom;

import io.github.hooferdevelops.chemicalevolution.ChemicalEvolution;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.*;
import net.minecraft.util.StringUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MobEssenceItem extends Item implements DyeableMobEssenceItem {
    public MobEssenceItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public Component getName(ItemStack item) {
        String itemName = I18n.exists(item.getDescriptionId()) == true ? I18n.get(item.getDescriptionId()) : "";
        String entityDescriptionId = item.getOrCreateTagElement("data").getString("type");

        if (entityDescriptionId != null){
            String entityName = I18n.exists(entityDescriptionId) == true ? I18n.get(entityDescriptionId) : null;

            if (itemName != null && entityName != null) {
                return new TextComponent(entityName + " " + itemName);
            }
        }

        return super.getName(item);
    }
}
