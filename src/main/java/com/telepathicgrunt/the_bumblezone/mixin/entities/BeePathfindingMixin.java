package com.telepathicgrunt.the_bumblezone.mixin.entities;

import com.telepathicgrunt.the_bumblezone.dimension.BzDimension;
import com.telepathicgrunt.the_bumblezone.entities.BeeAI;
import net.minecraft.entity.passive.BeeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeeEntity.WanderGoal.class)
public class BeePathfindingMixin {

    @Unique
    private BeeEntity beeEntity;

    @Unique
    private BeeAI.CachedPathHolder cachedPathHolder;

    @Inject(method = "<init>(Lnet/minecraft/entity/passive/BeeEntity;)V", at = @At(value = "RETURN"))
    private void init(BeeEntity beeEntity, CallbackInfo ci){
        this.beeEntity = beeEntity;
    }

    /**
     * @author TelepathicGrunt
     * @reason Make bees not get stuck on ceiling anymore and lag people as a result. (Only applies in Bumblezone dimension)
     */
    @Inject(method = "startExecuting()V",
            at = @At(value = "HEAD"),
            cancellable = true)
    private void newWander(CallbackInfo ci){
        // Do our own bee AI in the Bumblezone. Makes bees wander more and should be slightly better performance. Maybe...
        if(beeEntity.world.getDimensionKey().equals(BzDimension.BZ_WORLD_KEY)){
            cachedPathHolder = BeeAI.smartBeesTM(beeEntity, cachedPathHolder);
            ci.cancel();
        }
    }
}