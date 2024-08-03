package net.raia.starcana.block.functional;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.raia.starcana.block.entity.StarLanternEntity;
import org.jetbrains.annotations.Nullable;

public class StarLantern extends BlockWithEntity {
    protected static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 16.0, 13.0)
    );
    public StarLantern(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState());

    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new StarLanternEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
