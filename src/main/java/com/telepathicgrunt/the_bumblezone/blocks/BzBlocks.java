package com.telepathicgrunt.the_bumblezone.blocks;

import com.telepathicgrunt.the_bumblezone.Bumblezone;
import com.telepathicgrunt.the_bumblezone.mixin.items.MaterialInvoker;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;


public class BzBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Bumblezone.MODID);
    public static Material RESIDUE = ((MaterialInvoker) ((MaterialInvoker) new Material.Builder(MaterialColor.ADOBE))
            .bz_getNotOpaque()).bz_getPushDestroys().doesNotBlockMovement().replaceable().notSolid().build();

    //Blocks
    public static final RegistryObject<Block> POROUS_HONEYCOMB = createBlock("porous_honeycomb_block", PorousHoneycomb::new);
    public static final RegistryObject<Block> FILLED_POROUS_HONEYCOMB = createBlock("filled_porous_honeycomb_block", FilledPorousHoneycomb::new);
    public static final RegistryObject<Block> EMPTY_HONEYCOMB_BROOD = createBlock("dead_honeycomb_larva_block", EmptyHoneycombBrood::new);
    public static final RegistryObject<Block> HONEYCOMB_BROOD = createBlock("honeycomb_larva_block", HoneycombBrood::new);
    public static final RegistryObject<Block> SUGAR_INFUSED_STONE = createBlock("sugar_infused_stone", SugarInfusedStone::new);
    public static final RegistryObject<Block> SUGAR_INFUSED_COBBLESTONE = createBlock("sugar_infused_cobblestone", SugarInfusedCobblestone::new);
    public static final RegistryObject<Block> HONEY_CRYSTAL = createBlock("honey_crystal", HoneyCrystal::new);
    public static final RegistryObject<Block> STICKY_HONEY_RESIDUE = createBlock("sticky_honey_residue", StickyHoneyResidue::new);
    public static final RegistryObject<Block> STICKY_HONEY_REDSTONE = createBlock("sticky_honey_redstone", StickyHoneyRedstone::new);
    public static final RegistryObject<Block> BEESWAX_PLANKS = createBlock("beeswax_planks", BeeswaxPlanks::new);

    public static <B extends Block> RegistryObject<B> createBlock(String name, Supplier<B> block) {
        return BLOCKS.register(name, block);
    }
}