package io.github.hooferdevelops.blocks.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class ChemistryIncubatorRenderer implements BlockEntityRenderer<ChemistryIncubatorEntity> {

    public ChemistryIncubatorRenderer(BlockEntityRendererProvider.Context p_173602_) {
    }

    @Override
    public void render(ChemistryIncubatorEntity entity, float dist, PoseStack pose, MultiBufferSource buffer, int idk1, int idk2) {
        Direction direction = entity.getBlockState().getValue(ChemistryIncubator.FACING);

        NonNullList<ItemStack> nonnulllist = entity.getItems();
        int i = (int)entity.getBlockPos().asLong();

        for(int j = 0; j < nonnulllist.size(); ++j) {
            ItemStack itemstack = nonnulllist.get(j);
            if (itemstack != ItemStack.EMPTY) {
                pose.pushPose();
                pose.translate(0.5D, 0.7D, 0.5D);
                Direction direction1 = Direction.from2DDataValue(direction.get2DDataValue());
                float f = -direction1.toYRot();
                pose.mulPose(Vector3f.YP.rotationDegrees(f));
                pose.mulPose(Vector3f.XP.rotationDegrees(90.0F+180.0F));
                pose.scale(0.375F, 0.375F, 0.375F);
                Minecraft.getInstance().getItemRenderer().renderStatic(itemstack, ItemTransforms.TransformType.FIXED, idk1, idk2, pose, buffer, i + 1);
                pose.popPose();
            }
        }
    }
}
