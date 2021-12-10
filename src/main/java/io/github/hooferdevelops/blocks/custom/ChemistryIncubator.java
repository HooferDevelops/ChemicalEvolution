package io.github.hooferdevelops.blocks.custom;

import io.github.hooferdevelops.Registration;
import io.github.hooferdevelops.chemicalevolution.ChemicalEvolution;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.BooleanOp;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class ChemistryIncubator extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    private static final VoxelShape SHAPE_N = Stream.of(Block.box(13, 2, 3, 15, 11, 13), Block.box(0, 0, 0, 16, 2, 16), Block.box(1, 2, 13, 15, 11, 15), Block.box(1, 2, 1, 15, 11, 3), Block.box(4, 8, 4, 12, 10, 12), Block.box(5, 10, 5, 11, 11, 11), Block.box(1, 11, 1, 15, 16, 15), Block.box(3, 9, 3, 4, 11, 13), Block.box(4, 9, 12, 12, 11, 13), Block.box(4, 9, 3, 12, 11, 4), Block.box(12, 9, 3, 13, 11, 13), Block.box(1, 2, 3, 3, 11, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(Block.box(3, 2, 13, 13, 11, 15), Block.box(0, 0, 0, 16, 2, 16), Block.box(1, 2, 1, 3, 11, 15), Block.box(13, 2, 1, 15, 11, 15), Block.box(4, 8, 4, 12, 10, 12), Block.box(5, 10, 5, 11, 11, 11), Block.box(1, 11, 1, 15, 16, 15), Block.box(3, 9, 3, 13, 11, 4), Block.box(3, 9, 4, 4, 11, 12), Block.box(12, 9, 4, 13, 11, 12), Block.box(3, 9, 12, 13, 11, 13), Block.box(3, 2, 1, 13, 11, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(Block.box(1, 2, 3, 3, 11, 13), Block.box(0, 0, 0, 16, 2, 16), Block.box(1, 2, 1, 15, 11, 3), Block.box(1, 2, 13, 15, 11, 15), Block.box(4, 8, 4, 12, 10, 12), Block.box(5, 10, 5, 11, 11, 11), Block.box(1, 11, 1, 15, 16, 15), Block.box(12, 9, 3, 13, 11, 13), Block.box(4, 9, 3, 12, 11, 4), Block.box(4, 9, 12, 12, 11, 13), Block.box(3, 9, 3, 4, 11, 13), Block.box(13, 2, 3, 15, 11, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(Block.box(3, 2, 1, 13, 11, 3), Block.box(0, 0, 0, 16, 2, 16), Block.box(13, 2, 1, 15, 11, 15), Block.box(1, 2, 1, 3, 11, 15), Block.box(4, 8, 4, 12, 10, 12), Block.box(5, 10, 5, 11, 11, 11), Block.box(1, 11, 1, 15, 16, 15), Block.box(3, 9, 12, 13, 11, 13), Block.box(12, 9, 4, 13, 11, 12), Block.box(3, 9, 4, 4, 11, 12), Block.box(3, 9, 3, 13, 11, 4), Block.box(3, 2, 13, 13, 11, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public ChemistryIncubator(BlockBehaviour.Properties props) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
        ChemicalEvolution.LOGGER.info("RENDERING BLOCK");
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter wIn, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)){
            case NORTH:
                return SHAPE_N;
            case EAST:
                return SHAPE_E;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
        }
        return SHAPE_N;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(FACING);
    }

    @Override
    public BlockState rotate(BlockState rotate, Rotation rot) {
        return rotate.setValue(FACING, rot.rotate(rotate.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirr) {
        return state.rotate(mirr.getRotation(state.getValue(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ChemistryIncubatorEntity(pos, state);
    }

    // Custom Functions

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof ChemistryIncubatorEntity) {
            ChemistryIncubatorEntity incubatorentity = (ChemistryIncubatorEntity)blockentity;
            ItemStack itemstack = player.getItemInHand(hand);

            if (itemstack.getItem() == Registration.RADIOACTIVE_GLASS_CHEMISTRY_BOTTLE.get()) {
                if (!level.isClientSide && incubatorentity.insertChemicals(itemstack)) {
                    return InteractionResult.SUCCESS;
                }
                return InteractionResult.CONSUME;
            } else {
                if (!level.isClientSide) {
                    Containers.dropContents(level, pos.offset(new Vec3i(0,1,0)), ((ChemistryIncubatorEntity)blockentity).getItems());
                    incubatorentity.markUpdated();
                }
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state2, boolean someboolean) {
        if (!state.is(state2.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof ChemistryIncubatorEntity) {
                Containers.dropContents(level, pos, ((ChemistryIncubatorEntity)blockentity).getItems());
            }

            super.onRemove(state, level, pos, state2, someboolean);
        }
    }
}
