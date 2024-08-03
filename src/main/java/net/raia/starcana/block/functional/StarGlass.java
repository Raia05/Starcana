package net.raia.starcana.block.functional;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.GlassBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.tick.TickPriority;
import net.raia.starcana.sounds.StarcanaSounds;

public class StarGlass extends GlassBlock {

    public static final BooleanProperty COVERED = BooleanProperty.of("covered");
    private static final int DELAY_TICKS = 2;

    public StarGlass(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(COVERED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(COVERED);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getStackInHand(hand).isEmpty()) {
            if (!world.isClient) {
                boolean newCoveredState = !state.get(COVERED);
                world.playSound(null, pos, StarcanaSounds.STAR_GLASS_INTERACT, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.setBlockState(pos, state.with(COVERED, newCoveredState), 3);
                updateNeighborStates(world, pos, newCoveredState);
                world.scheduleBlockTick(pos, this, DELAY_TICKS, TickPriority.NORMAL);
            }
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.PASS;
        }
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClient) {
            world.scheduleBlockTick(pos, this, DELAY_TICKS, TickPriority.NORMAL);
            world.playSound(null, pos, StarcanaSounds.STAR_GLASS_CHANGE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        boolean coveredState = state.get(COVERED);
        this.updateNeighborStates(world, pos, coveredState);
    }

    @Override
    public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return (state.get(COVERED)) ? 15 : 0;
    }

    private void updateNeighborStates(World world, BlockPos pos, boolean covered) {
        for (Direction direction : Direction.values()) {
            BlockPos neighborPos = pos.offset(direction);
            BlockState neighborState = world.getBlockState(neighborPos);
            if (neighborState.getBlock() instanceof StarGlass && neighborState.get(COVERED) != covered) {
                world.setBlockState(neighborPos, neighborState.with(COVERED, covered), 3);
            }
        }
    }
}
