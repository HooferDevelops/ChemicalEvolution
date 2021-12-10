package io.github.hooferdevelops.items.custom;

import io.github.hooferdevelops.chemicalevolution.ChemicalEvolution;
import net.minecraft.world.item.Item;

public class MobEssenceItem extends Item implements DyeableMobEssenceItem {
    public MobEssenceItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public String getDescriptionId() {
        ChemicalEvolution.LOGGER.info("REEE");
        ChemicalEvolution.LOGGER.info(this.getOrCreateDescriptionId());
        return this.getOrCreateDescriptionId();
    }
}
