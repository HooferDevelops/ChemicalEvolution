package io.github.hooferdevelops.blocks.custom;

import io.github.hooferdevelops.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Clearable;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ChemistryIncubatorEntity extends BlockEntity implements Clearable {
    public final NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

    public ChemistryIncubatorEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(Registration.CHEMISTRY_INCUBATOR_ENTITY.get(), p_155229_, p_155230_);
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    public boolean insertChemicals(ItemStack item) {
        for(int i = 0; i < this.items.size(); ++i) {
            ItemStack itemstack = this.items.get(i);
            if (itemstack.isEmpty()) {
                this.items.set(i, item.split(1));
                this.markUpdated();
                return true;
            }
        }

        return false;
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void load(CompoundTag p_155312_) {
        super.load(p_155312_);
        this.items.clear();
        ContainerHelper.loadAllItems(p_155312_, this.items);
    }

    @Override
    protected void saveAdditional(CompoundTag p_187486_) {
        super.saveAdditional(p_187486_);
        ContainerHelper.saveAllItems(p_187486_, this.items, true);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compoundtag = new CompoundTag();
        ContainerHelper.saveAllItems(compoundtag, this.items, true);
        return compoundtag;
    }

    public void markUpdated() {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }
}
