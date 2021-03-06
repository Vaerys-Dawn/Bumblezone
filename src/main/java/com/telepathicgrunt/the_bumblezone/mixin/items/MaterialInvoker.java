package com.telepathicgrunt.the_bumblezone.mixin.items;

import net.minecraft.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Material.Builder.class)
public interface MaterialInvoker {

    @Invoker("notOpaque")
    Material.Builder bz_getNotOpaque();

    @Invoker("pushDestroys")
    Material.Builder bz_getPushDestroys();

}
