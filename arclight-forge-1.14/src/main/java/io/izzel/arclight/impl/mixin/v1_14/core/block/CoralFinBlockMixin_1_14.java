package io.izzel.arclight.impl.mixin.v1_14.core.block;

import net.minecraft.block.AbstractCoralPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CoralFinBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.bukkit.craftbukkit.v.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(CoralFinBlock.class)
public class CoralFinBlockMixin_1_14 {

    // @formatter:off
    @Shadow @Final private Block deadBlock;
    // @formatter:on

    @Inject(method = "tick", cancellable = true, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
    public void arclight$blockFade(BlockState state, World worldIn, BlockPos pos, Random random, CallbackInfo ci) {
        if (CraftEventFactory.callBlockFadeEvent(worldIn, pos, this.deadBlock.getDefaultState().with(AbstractCoralPlantBlock.WATERLOGGED, false)).isCancelled()) {
            ci.cancel();
        }
    }
}
