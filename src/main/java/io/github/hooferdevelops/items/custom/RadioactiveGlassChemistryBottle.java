package io.github.hooferdevelops.items.custom;

import io.github.hooferdevelops.Registration;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class RadioactiveGlassChemistryBottle extends Item {
    public RadioactiveGlassChemistryBottle(Properties props) {
        super(props);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack p_41348_, Level p_41349_, LivingEntity p_41350_) {
        super.finishUsingItem(p_41348_, p_41349_, p_41350_);

        if (p_41348_.isEmpty()) {
            return new ItemStack(Registration.GLASS_CHEMISTRY_BOTTLE.get());
        } else {
            if (p_41350_ instanceof Player && !((Player)p_41350_).getAbilities().instabuild) {
                ItemStack itemstack = new ItemStack(Registration.GLASS_CHEMISTRY_BOTTLE.get());
                Player player = (Player)p_41350_;
                if (!player.getInventory().add(itemstack)) {
                    player.drop(itemstack, false);
                }
            }

            return p_41348_;
        }
    }

    @Override
    public int getUseDuration(ItemStack p_41360_) {
        return 40;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41358_) {
        return UseAnim.DRINK;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }
}
