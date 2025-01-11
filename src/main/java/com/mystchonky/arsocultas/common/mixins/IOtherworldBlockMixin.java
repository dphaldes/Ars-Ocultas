package com.mystchonky.arsocultas.common.mixins;

import com.klikli_dev.occultism.common.block.otherworld.IOtherworldBlock;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.FakePlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = IOtherworldBlock.class, remap = false)
public interface IOtherworldBlockMixin {
    @ModifyReceiver(method = "getPlayerHarvestTier", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;hasEffect(Lnet/minecraft/core/Holder;)Z"))
    default Player getPlayerHarvestTier(Player instance, Holder<MobEffect> holder) {
        if (instance instanceof FakePlayer fake) {
            Player real = fake.level().getPlayerByUUID(fake.getUUID());
            if (real != null) {
                return real;
            }
        }
        return instance;
    }
}