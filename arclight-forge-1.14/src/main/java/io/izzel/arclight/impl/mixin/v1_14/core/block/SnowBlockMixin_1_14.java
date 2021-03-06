package io.izzel.arclight.impl.mixin.v1_14.core.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.bukkit.craftbukkit.v.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(SnowBlock.class)
public class SnowBlockMixin_1_14 {

    @Inject(method = "tick", cancellable = true, at = @At(value = "INVOKE", target = "Lnet/minecraft/block/SnowBlock;spawnDrops(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V"))
    public void arclight$blockFade(BlockState state, World worldIn, BlockPos pos, Random random, CallbackInfo ci) {
        if (CraftEventFactory.callBlockFadeEvent(worldIn, pos, Blocks.AIR.getDefaultState()).isCancelled()) {
            ci.cancel();
        }
    }
}
