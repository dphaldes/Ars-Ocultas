package com.mystchonky.arsocultas.common.blockentity;

import com.hollingsworth.arsnouveau.api.util.SourceUtil;
import com.hollingsworth.arsnouveau.common.block.tile.MobJarTile;
import com.klikli_dev.occultism.common.blockentity.GoldenSacrificialBowlBlockEntity;
import com.mystchonky.arsocultas.common.registrar.BlockEntityRegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AltarBlockEntity extends BlockEntity implements TickingBlockEntity {
    private static final int COST = 5000;

    public AltarBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegistrar.ALTAR.get(), pos, blockState);
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.getGameTime() % 300 != 0)
            return;

        if (level.getBlockEntity(pos.above()) instanceof GoldenSacrificialBowlBlockEntity bowl) {
            var recipe = bowl.getCurrentRitualRecipe();
            if (recipe == null || bowl.sacrificeFulfilled())
                return;

            for (BlockPos b : BlockPos.withinManhattan(getBlockPos().above(3), 10, 10, 6)) {
                if (level.getBlockEntity(b) instanceof MobJarTile mobJarTile && mobJarTile.getEntity() instanceof LivingEntity entity) {
                    if (recipe.value().getRitual().isValidSacrifice(entity)) {
                        if (SourceUtil.hasSourceNearby(worldPosition, level, 10, COST)) {
                            SourceUtil.takeSourceWithParticles(worldPosition, level, 10, COST);
                            bowl.notifySacrifice(null);
                        }

                        return;
                    }
                }
            }
        }

    }
}
