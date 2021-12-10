package io.github.hooferdevelops.animals;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;

public class FAKECOW extends Cow {
    private int inLove;

    public FAKECOW(EntityType<? extends Cow> p_28285_, Level p_28286_) {
        super(p_28285_, p_28286_);
    }

    @Override
    public boolean hurt(DamageSource p_27567_, float p_27568_) {
        if (!this.getLevel().isClientSide()){

            this.spawnChildFromBreeding(
                    (ServerLevel) this.getLevel(),
                    this
            );

            if (this.isInvulnerableTo(p_27567_)) {
                return false;
            } else {
                this.inLove = 0;
                return super.hurt(p_27567_, p_27568_);
            }


        }

        return false;

    }


}
