package net.raia.starcana.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class StarLanternEntity extends BlockEntity {
    public StarLanternEntity(BlockPos pos, BlockState state) {
        super(StarcanaBlockEntiies.STAR_LANTURN_ENTITY_BLOCK_ENTITY, pos, state);
    }
}
